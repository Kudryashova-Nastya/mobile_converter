package com.example.myapplication.fragments.exchange

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.data.RoomInitRepository
import com.example.myapplication.data.room.Currency
import com.example.myapplication.data.room.History
import com.example.myapplication.databinding.FragmentExchangeBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


class ExchangeFragment : Fragment() {


    private lateinit var binding: FragmentExchangeBinding
    private lateinit var viewModel: ExchangeViewModel
    private lateinit var currentCurrency: Currency
    private var exchangeCurrency: Currency =
        Currency(name = "EUR", value = 1.0, is_favorite = false)
    private var exchangedCurrencyValue: Double? = null
    private var inputCurrencyValue: Double = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory =
            ExchangeViewModelFactory(RoomInitRepository.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)[ExchangeViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentExchangeBinding.inflate(inflater, container, false)
        if (arguments != null) {
            currentCurrency = arguments?.getSerializable("currency") as Currency
            binding.firstCurrencyName.text = currentCurrency.name
        }
        if (binding.valueInput.text?.isEmpty() == true) {
            binding.valueInput.setText("1")
        }

        binding.valueInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                calculateExchangeValue(
                    exchangeCurrency, binding.valueInput.text.toString()
                )
            }
        })

        binding.buttonBack.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_exchangeFragment_to_listFragment)
        }

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var exchangeCurrencyFound = false

        // Если валюта для обмена ещё не определена
        if (!exchangeCurrencyFound && viewModel.getFavoriteCurrencyList()?.size!! > 0) {
            // ищем среди любимых первую несовпадающую с текущей валюту
            viewModel.getFavoriteCurrencyList().let { favoriteCurrencyList ->
                favoriteCurrencyList?.forEach { favoriteCurrency ->
                    if (!exchangeCurrencyFound && favoriteCurrency.name != currentCurrency.name) {
                        exchangeCurrency = favoriteCurrency
                        exchangeCurrencyFound = true
                    }
                }
            }
        }

        // если среди любимых нет подходящей валюты, делаем проверку на рубль
        if (!exchangeCurrencyFound && currentCurrency.name != "RUB") {
            exchangeCurrency = viewModel.getRUB()
        }

        // если текущая валюта и есть рубль, берём дефолтную EUR
        calculateExchangeValue(exchangeCurrency, "1.0")


        // добавляем сохранение на кнопку только в случае, если конвертированные данные корректны
        try {
            binding.exchangeButton.setOnClickListener {
                if (exchangedCurrencyValue != null) {

                    val historyData = History(
                        null,
                        currentCurrency.name,
                        inputCurrencyValue,
                        exchangeCurrency.name,
                        exchangedCurrencyValue!!,
                        getCurrentDate()
                    )

                    viewModel.addHistory(historyData) {}

                    Navigation.findNavController(requireView())
                        .navigate(R.id.action_exchangeFragment_to_listFragment)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("MY_TAG_ERROR", e.localizedMessage)
        }
    }


    private fun calculateExchangeValue(exchangeCur: Currency, inputValue: String) {
        val conversionOneValue = exchangeCur.value / currentCurrency.value
        try {
            binding.secondCurrencyName.text = exchangeCur.name
            inputCurrencyValue = inputValue.toDouble()
            // округляем до 5 знаков после запятой
            exchangedCurrencyValue =
                (((conversionOneValue * inputCurrencyValue * 100000.0).roundToInt() / 100000.0))
            binding.secondValue.text = exchangedCurrencyValue.toString()

        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("MY_TAG_ERROR", e.localizedMessage)
            binding.secondValue.text = "0"
            exchangedCurrencyValue = null
            inputCurrencyValue = 1.00
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentDate(): String {

        // текущая дата (+3 часа к той что в системе)
        val sdf = SimpleDateFormat("dd.MM.yyyy, HH:mm")
        val c = Calendar.getInstance()

        c.add(
            Calendar.HOUR,
            3
        )
        return sdf.format(c.time)
    }
}


package com.example.myapplication.fragments.exchange

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.data.RoomInitRepository
import com.example.myapplication.data.room.Currency
import com.example.myapplication.databinding.FragmentExchangeBinding
import kotlin.math.roundToInt


class ExchangeFragment : Fragment() {


    private lateinit var binding: FragmentExchangeBinding
    private lateinit var viewModel: ExchangeViewModel
    private lateinit var currentCurrency: Currency
    private var exchangeCurrency: Currency = Currency(name = "EUR", value = 1.0, is_favorite = false)

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
                        .toDouble()
                )
            }
        })

        binding.buttonBack.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_exchangeFragment_to_listFragment)
        }
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
                    if (!exchangeCurrencyFound && favoriteCurrency.name !== currentCurrency.name) {
                            exchangeCurrency = favoriteCurrency
                            exchangeCurrencyFound = true
                            Log.d("MY_TAG_INFO", favoriteCurrency.name + currentCurrency.name)
                    }
                }
            }
        }

        // если среди любимых нет подходящей валюты, делаем проверку на рубль
        if (!exchangeCurrencyFound && currentCurrency.name !== "RUB") {
            exchangeCurrency = viewModel.getRUB()
        }

        // если текущая валюта и есть рубль, берём дефолтную EUR
        calculateExchangeValue(exchangeCurrency, 1.0)

    }


    private fun calculateExchangeValue(exchangeCur: Currency, inputValue: Double) {
        val conversionOneValue = exchangeCur.value / currentCurrency.value
        try {
            binding.secondCurrencyName.text = exchangeCur.name
            // округляем до 5 знаков после запятой
            binding.secondValue.text =
                (((conversionOneValue * 100000.0).roundToInt() * inputValue / 100000.0)).toString()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("MY_TAG_ERROR", e.localizedMessage)
            binding.secondValue.text = "0"
        }
    }
}


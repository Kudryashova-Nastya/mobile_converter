package com.example.myapplication.fragments.exchange

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
    private lateinit var exchangeCurrency: Currency

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
        val currentValue = 1 / currentCurrency.value
        // округляем до 5 знаков после запятой
        binding.secondValue.text = ((currentValue * 100000.0).roundToInt() / 100000.0).toString()
        if (binding.valueInput.text?.isEmpty() == true) {
            binding.valueInput.setText("1")
        }

        binding.valueInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                binding.secondValue.text =
                    (((currentValue * 100000.0).roundToInt() * binding.valueInput.text.toString()
                        .toDouble() / 100000.0)).toString()
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

        var secondName = ""
        var secondValue = 1.0000
        var num1 = 1.0000
        var valueSecondCurrency = 1.0000

    }

}

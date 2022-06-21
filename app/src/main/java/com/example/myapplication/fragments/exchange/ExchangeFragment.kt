package com.example.myapplication.fragments.exchange

import android.os.Bundle
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


class ExchangeFragment() : Fragment() {


    private lateinit var binding: FragmentExchangeBinding
    private lateinit var viewModel: ExchangeViewModel
    private lateinit var currentCurrency: Currency
    lateinit var firstCurrency: Currency
    private lateinit var secondCurrency: Currency
    var firstName = ""
    var firstValue = 1.000
    private var secondNameIs = false

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
//        currentCurrency = arguments?.getSerializable("currency") as Currency
//        firstCurrency = currentCurrency
//        firstName = firstCurrency.name
        binding.secondValue.text = "1"

        binding.buttonBack.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_exchangeFragment_to_listFragment)
        }
//
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

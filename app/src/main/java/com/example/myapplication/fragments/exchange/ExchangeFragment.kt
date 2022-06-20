package com.example.myapplication.fragments.exchange

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = ExchangeViewModelFactory(RoomInitRepository.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)[ExchangeViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentExchangeBinding.inflate(inflater, container, false)

        currentCurrency = arguments?.getSerializable("currency") as Currency
        firstCurrency = currentCurrency
        firstName = firstCurrency.name
        binding.secondValue.text = "1"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.firstCurrencyName.text = firstName

    }

}

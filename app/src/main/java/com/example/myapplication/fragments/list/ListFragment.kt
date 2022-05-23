package com.example.myapplication.fragments.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DependencyInjection
import com.example.myapplication.data.RoomInitRepository
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.domain.model.Currency
import com.example.myapplication.ui.main.MainViewModel
import com.example.myapplication.ui.main.MainViewModelFactory


class ListFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ListAdapter
    private lateinit var binding: FragmentListBinding
    private lateinit var recyclerView: RecyclerView

    private var currencyItems: MutableList<Currency> = mutableListOf()


    private fun getRoomCurrencyToLocalList() {
        viewModel.getLocalCurrencyList().let { newCurrency ->
            currencyItems.clear()
            newCurrency.forEach { currency ->
                currencyItems.add(currency)
            }
            adapter.setData(currencyItems)
        }
    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        val viewModelFactory =
            MainViewModelFactory(RoomInitRepository.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.init() // получить данные

        adapter = ListAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        mCurrencyViewModel = ViewModelProvider(this)[CurrencyViewModel::class.java]
//        mCurrencyViewModel = ViewModelProvider(this)[MainViewModel::class.java]

//        mCurrencyViewModel.readAllData.observe(
//            viewLifecycleOwner
//        ) { currency ->
//            adapter.setData(currency as List<Currency>)
//        }

        // проверяем есть ли в бд данные, если нет, кладём их туда, если есть, то обновляем
        val currentListRoom = viewModel.getLocalCurrencyList()
        Log.d("MY_TAG_DB", currentListRoom.toString())
        if (currentListRoom.isEmpty()) {
            Log.d("MY_TAG_DB", "insert currencyes")
            viewModel.liveData.observe(viewLifecycleOwner) { it ->
                binding.date.text = it.date.toString()
                val rates = it.rates
                for (item in rates) {
                    viewModel.insertCurrency(item) {
                    }
                }
                getRoomCurrencyToLocalList()
            }
        } else {
            Log.d("MY_TAG_DB", "update currencyes")
            viewModel.liveData.observe(viewLifecycleOwner) { it ->
                binding.date.text = it.date.toString()
                val rates = it.rates
                for (item in rates) {
                    viewModel.updateListCurrency(item) {
                    }
                }
                getRoomCurrencyToLocalList()
            }
        }

        binding.updateList.setOnClickListener {
            Log.d("MY_TAG_DB", "button update currencyes")
            viewModel.getRetrofitCurrency()
            viewModel.liveData.observe(viewLifecycleOwner) {
                binding.date.text = it.date.toString()
                val rates = it.rates
                for (item in rates) {
                    viewModel.updateListCurrency(item) {
                    }
                }
                getRoomCurrencyToLocalList()
            }
        }

//        viewModel.liveData.observe(viewLifecycleOwner) { cur ->
//            binding.recyclerview.layoutManager =
//                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
////            adapter.currencyList = cur.currencyList.rates
//            adapter.setData(cur.currencyList)
//            binding.recyclerview.adapter = adapter
//        }

    }
}

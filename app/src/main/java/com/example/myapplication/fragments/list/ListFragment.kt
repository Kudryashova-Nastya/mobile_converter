package com.example.myapplication.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DependencyInjection
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.ui.main.MainViewModel
import com.example.myapplication.ui.main.MainViewModelFactory


class ListFragment : Fragment() {

//    private lateinit var mCurrencyViewModel: CurrencyViewModel
//    private lateinit var mCurrencyViewModel: MainViewModel
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ListAdapter
    private lateinit var binding: FragmentListBinding

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        val viewModelFactory =
            MainViewModelFactory(DependencyInjection.repository) // пока нет внутреннего хранилища - идёт в сеть
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.init() // получил данные

        adapter = ListAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListBinding.inflate(inflater, container, false)
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

        viewModel.liveData.observe(viewLifecycleOwner) { cur ->
            binding.recyclerview.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//            adapter.currencyList = cur.currencyList.rates
            adapter.setData(cur.currencyList)
            binding.recyclerview.adapter = adapter
        }

    }
}

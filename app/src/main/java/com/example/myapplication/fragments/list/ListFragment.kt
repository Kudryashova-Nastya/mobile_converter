package com.example.myapplication.fragments.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.RoomInitRepository
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.data.room.Currency
import com.example.myapplication.fragments.exchange.ExchangeFragment
import com.example.myapplication.ui.main.MainViewModel
import com.example.myapplication.ui.main.MainViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class ListFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ListAdapter
    private lateinit var binding: FragmentListBinding
    private lateinit var recyclerView: RecyclerView



    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        val viewModelFactory =
            MainViewModelFactory(RoomInitRepository.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.init() // получить данные

        val fragment = ExchangeFragment()
        val bundle = Bundle()
        adapter = ListAdapter(
            object: CurrencyActionListener {
                override fun onCurrencyFavorite(currency: Currency) {
                    viewModel.updateListFavoriteCurrency(currency){}
                }

                override fun currencyExchange(currency: Currency) {
                    bundle.putSerializable("currency", currency)
                    fragment.arguments = bundle
                    // добавить перемещение
                }
            }
        )

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

        binding.updateList.setOnClickListener {
            Log.d("MY_TAG_DB", "button update currencies")
            viewModel.getRetrofitCurrency()
        }


        viewModel.liveData.observe(viewLifecycleOwner) {
                list ->
            adapter.setData(list)
            binding.date.text = viewModel.updateDate
            binding.currentDate.text = getCurrentDate()
        }

    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentDate(): String {
        val c = Calendar.getInstance()
        val df = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss")
        return df.format(c.time)
    }
}

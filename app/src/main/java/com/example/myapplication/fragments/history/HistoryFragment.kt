package com.example.myapplication.fragments.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.data.RoomInitRepository
import com.example.myapplication.data.room.History
import com.example.myapplication.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val viewModel: HistoryViewModel
        get() = (activity as MainActivity).viewModel
    private lateinit var adapter: HistoryAdapter
    private lateinit var recyclerView: RecyclerView
    var historyItems: MutableList<History> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = HistoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        binding.buttonFilters.setOnClickListener {
            // переход на экран фильтров
            Navigation.findNavController(requireView())
                .navigate(R.id.action_historyFragment_to_filterFragment)
        }

        recyclerView = binding.recyclerviewHistory
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

        viewModel.filter.observe(viewLifecycleOwner) {
            binding.filterDate.text = it
        }
        getHistory()
        return binding.root
    }

    private fun getHistory() {
        // определяем за какой срок брать данные
        val historyList = when (viewModel.filter.value) {
            "За последнюю неделю" -> viewModel.getHistory()
            "В этом месяце" -> viewModel.getMonthHistory()
            else -> viewModel.getHistory()
        }

        historyList.let { newHistory ->
            historyItems = emptyList<History>().toMutableList()
            newHistory.forEach { item ->
                historyItems.add(item)
            }
            adapter.setData(historyItems)
        }
    }

}
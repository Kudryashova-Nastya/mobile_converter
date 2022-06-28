package com.example.myapplication.fragments.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.RoomInitRepository
import com.example.myapplication.data.room.History
import com.example.myapplication.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var viewModel: HistoryViewModel
    private lateinit var adapter: HistoryAdapter
    private lateinit var recyclerView: RecyclerView
    var historyItems: MutableList<History> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = HistoryViewModelFactory(RoomInitRepository.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]

        adapter = HistoryAdapter()
        getHistory()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        binding.buttonFilters.setOnClickListener{
            // переход на экран фильтров
        }

        recyclerView = binding.recyclerviewHistory
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

        return binding.root
    }

    private fun getHistory() {

        viewModel.getHistory().let { newHistory ->
            historyItems = emptyList<History>().toMutableList()
            newHistory?.forEach { item ->
                historyItems.add(item)
            }
            adapter.setData(historyItems)
        }
    }

}
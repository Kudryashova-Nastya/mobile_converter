package com.example.myapplication.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw6.fragments.list.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.data.CurrencyDb
import com.example.myapplication.data.CurrencyViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*



class ListFragment : Fragment() {

    private lateinit var mNodeViewModel: CurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

//        mRelationshipViewModel = ViewModelProvider(this)[RelationshipViewModel::class.java]

        val adapter = ListAdapter()


        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mNodeViewModel = ViewModelProvider(this)[CurrencyViewModel::class.java]

        mNodeViewModel.readAllData.observe(
            viewLifecycleOwner
        ) { node ->
            adapter.setData(node as List<CurrencyDb>)
        }

        return view
    }
}

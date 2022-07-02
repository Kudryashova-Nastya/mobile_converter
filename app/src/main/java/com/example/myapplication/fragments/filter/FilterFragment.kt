package com.example.myapplication.fragments.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFilterBinding
import com.example.myapplication.fragments.history.HistoryViewModel


class FilterFragment : Fragment() {

    private lateinit var binding: FragmentFilterBinding

    private val viewModel: HistoryViewModel
        get() = (activity as MainActivity).viewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)

        binding.filterButton.setOnClickListener {
            viewModel.filter.postValue("All")
        }

        binding.buttonBack.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_filterFragment_to_historyFragment)
        }

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        return binding.root
    }

}
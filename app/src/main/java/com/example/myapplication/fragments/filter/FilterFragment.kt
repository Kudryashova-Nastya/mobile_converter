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

        when (viewModel.filter.value) {
            "За последнюю неделю" -> binding.radioButtonWeek.isChecked = true
            "За последний месяц" -> binding.radioButtonMonth.isChecked = true
            else -> binding.radioButtonAll.isChecked = true
        }

        binding.filterButton.setOnClickListener {
            val selectedFilter = binding.radioButtonGroup.checkedRadioButtonId
            val filterText = when (selectedFilter) {
                R.id.radioButtonAll -> "За всё время"
                R.id.radioButtonMonth -> "За последний месяц"
                else -> "За последнюю неделю"
            }
            viewModel.filter.postValue(filterText)
            Navigation.findNavController(requireView())
                .navigate(R.id.action_filterFragment_to_historyFragment)
        }

        binding.buttonBack.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_filterFragment_to_historyFragment)
        }

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        return binding.root
    }

}
package com.example.myapplication.fragments.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.room.History
import com.example.myapplication.databinding.HistoryItemBinding

class HistoryAdapter() :
    RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {

    private var historyList: MutableList<History> = mutableListOf()
    private lateinit var itemBinding: HistoryItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        itemBinding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(historyList[position])
    }


    fun setData(data: MutableList<History>) {
        historyList = data
        notifyDataSetChanged()
    }


    inner class MyViewHolder(private val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(history: History) {
//            Log.d("MY_TAG_HOLDER", history.toString())

            binding.firstCurrency.text = history.value_currency1.toString() + ' ' + history.name_currency1
            binding.secondCurrency.text = history.value_currency2.toString() + ' ' + history.name_currency2
            binding.historyDate.text = history.exchange_date

        }

    }

}

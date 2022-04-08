package com.example.myapplication.fragments.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.CurrencyDb
import com.example.myapplication.domain.model.Currency
import kotlinx.android.synthetic.main.currency.view.*

class ListAdapter() :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    //    private var currencyList = emptyList<CurrencyDb>()
//    var currencyList: MutableList<Currency> = emptyList<Currency>().toMutableList()
    var currencyList: List<Currency> = emptyList<Currency>()


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.currency, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("currencyList", currencyList.toString())
        val currentItem = currencyList[position]

        holder.itemView.textCurrency.text = currentItem.name
        holder.itemView.photoImageView.setImageResource(R.drawable.ic_star)

    }

    fun setData(cur: List<Currency>) {
        this.currencyList = cur
        notifyDataSetChanged()
    }

}

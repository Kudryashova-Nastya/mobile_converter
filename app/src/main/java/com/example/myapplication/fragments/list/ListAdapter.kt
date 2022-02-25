package com.example.hw6.fragments.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.CurrencyDb
import kotlinx.android.synthetic.main.currency.view.*

class ListAdapter() :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    private var nodeList = emptyList<CurrencyDb>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.currency, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return nodeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("nodeList", nodeList.toString())
        val currentItem = nodeList[position]
        var hasParents = false
        var hasChildren = false

        holder.itemView.textCurrency.text =
            "id: " + currentItem.id.toString() + " | date: " + currentItem.date.toString()

    }

    fun setData(node: List<CurrencyDb>) {
        this.nodeList = node
        notifyDataSetChanged()
    }

}

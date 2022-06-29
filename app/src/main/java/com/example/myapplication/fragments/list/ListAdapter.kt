package com.example.myapplication.fragments.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.CurrencyBinding
import com.example.myapplication.data.room.Currency

class ListAdapter(private val actionListener: CurrencyActionListener) :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    //    private var currencyList = emptyList<CurrencyDb>()
//    var currencyList: MutableList<Currency> = emptyList<Currency>().toMutableList()
    private var currencyList: List<Currency> = emptyList()
    private lateinit var itemBinding: CurrencyBinding

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        binding = CurrencyBinding.inflate(inflater, container, false)
//        return binding.root
//
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        itemBinding = CurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(currencyList[position])
    }


    fun setData(data: List<Currency>) {
        this.currencyList = data
        notifyDataSetChanged()
    }


    inner class MyViewHolder(private val binding: CurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency) {
//            Log.d("MY_TAG_HOLDER", currency.toString())

            binding.textCurrency.text = currency.name

            if (currency.is_favorite) {
                binding.starImageView.setImageResource(R.drawable.ic_star_ok)
                binding.starImageView.setOnClickListener {
                    Log.d("MY_TAG_STAR", "Тык по закрашенной звёздочке")
                    binding.starImageView.setImageResource(R.drawable.ic_star)
                    actionListener.onCurrencyFavorite(currency.copy(is_favorite = !currency.is_favorite))
                }
            } else {
                binding.starImageView.setImageResource(R.drawable.ic_star)
                binding.starImageView.setOnClickListener {
                    Log.d("MY_TAG_STAR", "Тык по звёздочке")
                    binding.starImageView.setImageResource(R.drawable.ic_star_ok)
                    actionListener.onCurrencyFavorite(currency.copy(is_favorite = !currency.is_favorite))
                }
            }

            binding.root.setOnClickListener {
                Log.d("MY_TAG_CURRENCY", "Тык по валюте")
                actionListener.toCurrencyExchange(currency)
            }

        }

    }

}

package com.example.myapplication.fragments.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.CurrencyBinding
import com.example.myapplication.domain.model.Currency
import kotlinx.android.synthetic.main.currency.view.*

class ListAdapter() :
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


    fun setData(cur: List<Currency>) {
        this.currencyList = cur
        notifyDataSetChanged()
    }


    class MyViewHolder(val binding: CurrencyBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency) {
            Log.d("inside bind in view holder", currency.toString())

            binding.textCurrency.text = currency.name

            if (currency.is_favorite) {
                binding.starImageView.setImageResource(R.drawable.ic_star_ok)
                binding.starImageView.setOnClickListener {
//                Toast.makeText(this, "Тык по звёздочке", Toast.LENGTH_SHORT).show()
                    Log.d("MY_STAR", "Тык по закрашенной звёздочке")
                    binding.starImageView.setImageResource(R.drawable.ic_star)
                }
            } else {
                binding.starImageView.setImageResource(R.drawable.ic_star)
                binding.starImageView.setOnClickListener {
//                Toast.makeText(this, "Тык по звёздочке", Toast.LENGTH_SHORT).show()
                    Log.d("MY_STAR", "Тык по звёздочке")
                    binding.starImageView.setImageResource(R.drawable.ic_star_ok)
//                    notifyDataSetChanged()

                }
            }

        }

    }

}

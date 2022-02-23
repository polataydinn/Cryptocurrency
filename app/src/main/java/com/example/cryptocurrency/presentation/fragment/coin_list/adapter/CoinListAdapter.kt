package com.example.cryptocurrency.presentation.fragment.coin_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.databinding.CustomCoinListItemBinding
import com.example.cryptocurrency.domain.model.Coin

class CoinListAdapter : RecyclerView.Adapter<CoinListViewHolder>() {

    private var coinList = emptyList<Coin>()
    var onItemClick: ((Coin) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        val binding =
            CustomCoinListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        holder.bind(coinList[position], onItemClick)
    }

    override fun getItemCount() = coinList.size

    fun setCoinList(coinList: List<Coin>){
        this.coinList = coinList
    }
}
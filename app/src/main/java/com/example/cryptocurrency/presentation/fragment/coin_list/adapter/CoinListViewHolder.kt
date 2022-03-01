package com.example.cryptocurrency.presentation.fragment.coin_list.adapter

import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.R
import com.example.cryptocurrency.common.Constants
import com.example.cryptocurrency.databinding.CustomCoinListItemBinding
import com.example.cryptocurrency.domain.model.Coin

class CoinListViewHolder(private val binding: CustomCoinListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(coin: Coin, onItemClick: ((Coin) -> Unit)?) {
        val coinName = "${coin.rank}. ${coin.name}"
        binding.coinNameTextView.text = coinName

        if (!coin.isActive) {
            binding.isActiveTextView.text = Constants.INACTIVE
            binding.isActiveTextView.setTextColor(
                getColor(
                    binding.isActiveTextView.context,
                    R.color.red
                )
            )
        }

        binding.root.setOnClickListener{
            onItemClick?.invoke(coin)
        }
    }
}

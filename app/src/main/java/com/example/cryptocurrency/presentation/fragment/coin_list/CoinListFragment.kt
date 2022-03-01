package com.example.cryptocurrency.presentation.fragment.coin_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.cryptocurrency.databinding.FragmentCoinListBinding
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.presentation.bindingadapter.BindingFragment
import com.example.cryptocurrency.presentation.fragment.coin_list.adapter.CoinListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinListFragment : BindingFragment<FragmentCoinListBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentCoinListBinding::inflate

    private val viewModel: CoinListViewModel by viewModels()
    private lateinit var adapter: CoinListAdapter
    private lateinit var savedStateHandle: SavedStateHandle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CoinListAdapter()
        savedStateHandle = SavedStateHandle()
        adapter.onItemClick = { coin ->
            findNavController().navigate(
                CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment2(
                    coin.id
                )
            )
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { coinListState ->
                if (!coinListState.coins.isNullOrEmpty()) {
                    setCoinList(coinListState.coins)
                }
                if (coinListState.error != "") {
                    binding.apply {
                        coinListErrorMessage.visibility = View.VISIBLE
                        coinListErrorMessage.text = coinListState.error
                    }
                }
                binding.coinListProgressBar.isVisible = coinListState.isLoading
            }
        }
    }

    private fun setCoinList(coins: List<Coin>) {
        binding.coinListRecyclerview.visibility = View.VISIBLE
        adapter.setCoinList(coins)
        binding.coinListRecyclerview.adapter = adapter
    }
}
package com.example.cryptocurrency.presentation.fragment.coin_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cryptocurrency.databinding.FragmentCoinListBinding
import com.example.cryptocurrency.presentation.fragment.coin_list.adapter.CoinListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment : Fragment() {
    private lateinit var _binding: FragmentCoinListBinding
    private val binding get() = _binding
    private val viewModel: CoinListViewModel by viewModels()
    private lateinit var adapter: CoinListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CoinListAdapter()
        adapter.onItemClick = {

        }
        val state = viewModel.state.value
        adapter.setCoinList(state.coins)
        binding.coinListRecyclerview.adapter = adapter
    }
}
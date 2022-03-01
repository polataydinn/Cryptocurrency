package com.example.cryptocurrency.presentation.fragment.coin_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.cryptocurrency.R
import com.example.cryptocurrency.addChip
import com.example.cryptocurrency.common.Constants
import com.example.cryptocurrency.databinding.FragmentCoinDetailBinding
import com.example.cryptocurrency.domain.model.CoinDetail
import com.example.cryptocurrency.presentation.bindingadapter.BindingFragment
import com.example.cryptocurrency.presentation.fragment.coin_detail.adapter.TeamMemberAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinDetailFragment : BindingFragment<FragmentCoinDetailBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentCoinDetailBinding::inflate

    private val viewModel: CoinDetailViewModel by viewModels()
    private lateinit var teamMemberAdapter: TeamMemberAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val state = viewModel.state

        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { coinDetailState ->
                if (coinDetailState.coin != null) {
                    setCoinToView(coinDetailState.coin)
                }
                if (coinDetailState.error != "") {
                    binding.apply {
                        detailErrorMessage.visibility = View.VISIBLE
                        detailErrorMessage.text = coinDetailState.error
                    }
                }
                binding.detailProgressBar.isVisible = coinDetailState.isLoading
            }
        }

    }

    private fun setCoinToView(coin: CoinDetail) {
        val coinTitle = coin.rank.toString() + ". " + coin.name + " (${coin.symbol})"
        binding.apply {
            detailCoinDescriptionText.text = coin.description
            detailCoinNameText.text = coinTitle
            detailCenterLayout.visibility = View.VISIBLE
        }
        setIsActive(coin)
        setTeamMemberList(coin)
        addTags(coin.tags)
    }


    private fun setIsActive(coin: CoinDetail) {
        if (!coin.isActive) {
            binding.detailIsActiveText.text = Constants.INACTIVE
            binding.detailIsActiveText.setTextColor(
                ContextCompat.getColor(
                    binding.detailIsActiveText.context,
                    R.color.red
                )
            )
        }
    }

    private fun setTeamMemberList(coin: CoinDetail) {
        if (!coin.team.isNullOrEmpty()) {
            teamMemberAdapter = TeamMemberAdapter()
            teamMemberAdapter.setList(coin.team)
            binding.detailTeamMembersRecyclerView.adapter = teamMemberAdapter
        } else {
            binding.detailTeamMembersRecyclerView.visibility = View.GONE
            binding.detailTeamMembersText.visibility = View.GONE
        }

    }

    private fun addTags(tags: List<String>?) {
        if (!tags.isNullOrEmpty()) {
            tags.forEach { tag ->
                binding.detailCoinTagsChipsGroup.addChip(
                    tag,
                    layoutInflater,
                    binding.detailCoinTagsChipsGroup
                )
            }
        } else {
            binding.detailCoinTagsChipsGroup.visibility = View.GONE
            binding.detailCoinTagsText.visibility = View.GONE
        }

    }
}
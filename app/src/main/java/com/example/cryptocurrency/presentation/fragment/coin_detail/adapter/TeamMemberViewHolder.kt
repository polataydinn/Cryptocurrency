package com.example.cryptocurrency.presentation.fragment.coin_detail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.data.remote.dto.TeamMember
import com.example.cryptocurrency.databinding.CustomMembersItemBinding

class TeamMemberViewHolder(private val binding: CustomMembersItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(teamMember: TeamMember, size: Int) {
        binding.memberName.text = teamMember.name
        binding.memberDepartment.text = teamMember.position
        if (absoluteAdapterPosition == size - 1){
            binding.breakLine.visibility = View.GONE
        }
    }
}

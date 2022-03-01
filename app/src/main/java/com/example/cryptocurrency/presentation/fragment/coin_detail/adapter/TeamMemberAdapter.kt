package com.example.cryptocurrency.presentation.fragment.coin_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.data.remote.dto.TeamMember
import com.example.cryptocurrency.databinding.CustomMembersItemBinding

class TeamMemberAdapter: RecyclerView.Adapter<TeamMemberViewHolder>() {
    var listOfTeamMembers = listOf<TeamMember>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamMemberViewHolder {
        val binding = CustomMembersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamMemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamMemberViewHolder, position: Int) {
        holder.bind(listOfTeamMembers[position], listOfTeamMembers.size)
    }

    override fun getItemCount() = listOfTeamMembers.size

    fun setList(listOfTeamMembers: List<TeamMember>){
        this.listOfTeamMembers = listOfTeamMembers
    }
}
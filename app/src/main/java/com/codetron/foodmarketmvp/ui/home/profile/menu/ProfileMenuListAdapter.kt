package com.codetron.foodmarketmvp.ui.home.profile.menu

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codetron.foodmarketmvp.model.view.profile.ProfileMenu
import com.codetron.foodmarketmvp.databinding.ItemProfileMenuBinding

class ProfileMenuListAdapter(private val clickListener: ProfileMenuClickListener) :
    RecyclerView.Adapter<ProfileMenuListAdapter.ProfileMenuViewHolder>() {

    private val menus = arrayListOf<ProfileMenu>()

    @SuppressLint("NotifyDataSetChanged")
    fun setMenuItems(menus: List<ProfileMenu>) {
        this.menus.addAll(emptyList())
        this.menus.addAll(menus)
        notifyDataSetChanged()
    }

    inner class ProfileMenuViewHolder(private val binding: ItemProfileMenuBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(data: ProfileMenu) {
            binding.menu = data
            binding.root.setOnClickListener(this)
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            val menu = binding.menu
            menu?.id?.let(clickListener::setOnClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileMenuViewHolder {
        val binding = ItemProfileMenuBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProfileMenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileMenuViewHolder, position: Int) {
        holder.bind(menus[position])
    }

    override fun getItemCount(): Int {
        return menus.size
    }
}

interface ProfileMenuClickListener {
    fun setOnClickListener(id: Long)
}
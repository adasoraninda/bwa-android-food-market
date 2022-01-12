package com.codetron.foodmarketmvp.ui.home.profile.menu

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codetron.foodmarketmvp.databinding.ItemProfileMenuBinding
import com.codetron.foodmarketmvp.model.view.profile.ProfileMenu
import com.codetron.foodmarketmvp.util.setTextResources

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
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ProfileMenu) {

            binding.txtProfileMenu.setTextResources(data.title)
            binding.root.setOnClickListener {
                clickListener.setOnClickListener(data.id)
            }
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
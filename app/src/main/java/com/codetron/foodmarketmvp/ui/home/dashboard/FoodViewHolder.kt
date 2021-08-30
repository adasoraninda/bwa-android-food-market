package com.codetron.foodmarketmvp.ui.home.dashboard

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.codetron.foodmarketmvp.model.domain.food.Food
import com.codetron.foodmarketmvp.databinding.ItemDashboardFoodVerticalBinding
import com.codetron.foodmarketmvp.databinding.ItemDashobardFoodHorizontalBinding

abstract class FoodViewHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    abstract fun bind(data: Food)
}

class FoodVerticalViewHolder(
    val binding: ItemDashboardFoodVerticalBinding,
    private val onItemClick: (id: Long?) -> Unit
) :
    FoodViewHolder(binding) {
    override fun bind(data: Food) {
        binding.food = data
        binding.executePendingBindings()
        binding.root.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        binding.food?.id?.let(onItemClick::invoke)
    }
}

class FoodHorizontalViewHolder(
    val binding: ItemDashobardFoodHorizontalBinding,
    private val onItemClick: (id: Long?) -> Unit
) :
    FoodViewHolder(binding) {
    override fun bind(data: Food) {
        binding.food = data
        binding.executePendingBindings()
        binding.root.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        binding.food?.id?.let(onItemClick::invoke)
    }
}
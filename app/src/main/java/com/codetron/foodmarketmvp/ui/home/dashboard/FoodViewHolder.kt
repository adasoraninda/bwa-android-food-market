package com.codetron.foodmarketmvp.ui.home.dashboard

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.codetron.foodmarketmvp.data.model.Food
import com.codetron.foodmarketmvp.databinding.ItemDashboardFoodVerticalBinding
import com.codetron.foodmarketmvp.databinding.ItemDashobardFoodHorizontalBinding

abstract class FoodViewHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(data: Food)
}

class FoodVerticalViewHolder(val binding: ItemDashboardFoodVerticalBinding) :
    FoodViewHolder(binding) {
    override fun bind(data: Food) {
        binding.food = data
        binding.executePendingBindings()
    }
}

class FoodHorizontalViewHolder(val binding: ItemDashobardFoodHorizontalBinding) :
    FoodViewHolder(binding) {
    override fun bind(data: Food) {
        binding.food = data
        binding.executePendingBindings()
    }
}
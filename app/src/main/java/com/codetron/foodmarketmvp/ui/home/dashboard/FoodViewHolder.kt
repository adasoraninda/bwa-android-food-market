package com.codetron.foodmarketmvp.ui.home.dashboard

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.codetron.foodmarketmvp.data.model.Food
import com.codetron.foodmarketmvp.databinding.ItemDashboardFoodVerticalBinding
import com.codetron.foodmarketmvp.databinding.ItemDashobardFoodHorizontalBinding

abstract class FoodViewHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Food, holder: RecyclerView.ViewHolder) {
        when (holder) {
            is FoodVerticalViewHolder -> {
                holder.binding.food = data
                holder.binding.executePendingBindings()
            }
            is FoodHorizontalViewHolder -> {
                holder.binding.food = data
                holder.binding.executePendingBindings()
            }
        }
    }
}

class FoodVerticalViewHolder(val binding: ItemDashboardFoodVerticalBinding) :
    FoodViewHolder(binding)

class FoodHorizontalViewHolder(val binding: ItemDashobardFoodHorizontalBinding) :
    FoodViewHolder(binding)
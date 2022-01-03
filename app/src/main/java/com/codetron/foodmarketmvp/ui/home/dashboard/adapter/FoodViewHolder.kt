package com.codetron.foodmarketmvp.ui.home.dashboard.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.codetron.foodmarketmvp.model.domain.food.FoodItem
import com.codetron.foodmarketmvp.databinding.ItemDashboardFoodVerticalBinding
import com.codetron.foodmarketmvp.databinding.ItemDashobardFoodHorizontalBinding

abstract class FoodViewHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    abstract fun bind(data: FoodItem)
}

class FoodVerticalViewHolder(
    val binding: ItemDashboardFoodVerticalBinding,
    private val onItemClick: (id: Int?) -> Unit
) :
    FoodViewHolder(binding) {
    override fun bind(data: FoodItem) {
        binding.root.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
    }
}

class FoodHorizontalViewHolder(
    val binding: ItemDashobardFoodHorizontalBinding,
    private val onItemClick: (id: Int?) -> Unit
) :
    FoodViewHolder(binding) {
    override fun bind(data: FoodItem) {

        binding.root.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

    }
}
package com.codetron.foodmarketmvp.ui.home.dashboard.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.codetron.foodmarketmvp.databinding.ItemDashboardFoodVerticalBinding
import com.codetron.foodmarketmvp.databinding.ItemDashobardFoodHorizontalBinding
import com.codetron.foodmarketmvp.model.domain.food.FoodItem
import com.codetron.foodmarketmvp.util.setImageResource
import com.codetron.foodmarketmvp.util.setTextPrice

abstract class FoodViewHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(data: FoodItem)
}

class FoodVerticalViewHolder(
    val binding: ItemDashboardFoodVerticalBinding,
    private val onItemClick: (id: Int?) -> Unit
) :
    FoodViewHolder(binding) {
    override fun bind(data: FoodItem) {
        binding.txtFoodName.text = data.name
        binding.txtRating.text = data.rate.toString()
        binding.rtbFood.numStars = data.rate
        binding.txtFoodPrice.setTextPrice(data.price)
        binding.imgFood.setImageResource(data.picturePath)

        binding.root.setOnClickListener {
            onItemClick.invoke(data.id)
        }
    }
}

class FoodHorizontalViewHolder(
    val binding: ItemDashobardFoodHorizontalBinding,
    private val onItemClick: (id: Int?) -> Unit
) : FoodViewHolder(binding) {
    override fun bind(data: FoodItem) {
        binding.txtFoodName.text = data.name
        binding.txtRating.text = data.rate.toString()
        binding.rtbFood.numStars = data.rate
        binding.imgFood.setImageResource(data.picturePath)

        binding.root.setOnClickListener {
            onItemClick.invoke(data.id)
        }
    }

}
package com.codetron.foodmarketmvp.ui.home.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.codetron.foodmarketmvp.databinding.ItemDashboardFoodVerticalBinding
import com.codetron.foodmarketmvp.databinding.ItemDashobardFoodHorizontalBinding

class ListFoodViewHolderFactory(
    private val inflater: LayoutInflater,
    private val parent: ViewGroup,
    private var type: ListType = ListType.VERTICAL,
    private val onItemClick: (id: Int?) -> Unit
) {

    fun create(): FoodViewHolder {
        return when (type) {
            ListType.VERTICAL -> FoodVerticalViewHolder(
                ItemDashboardFoodVerticalBinding.inflate(
                    inflater,
                    parent,
                    false
                ),
                onItemClick
            )
            ListType.HORIZONTAL -> FoodHorizontalViewHolder(
                ItemDashobardFoodHorizontalBinding.inflate(
                    inflater,
                    parent,
                    false
                ),
                onItemClick
            )
        }
    }
}
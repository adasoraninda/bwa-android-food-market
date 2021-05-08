package com.codetron.foodmarketmvp.ui.home.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.codetron.foodmarketmvp.data.model.Food
import com.codetron.foodmarketmvp.databinding.ItemDashboardFoodVerticalBinding
import com.codetron.foodmarketmvp.databinding.ItemDashobardFoodHorizontalBinding

class FoodListAdapter(private val type: ListType) :
    RecyclerView.Adapter<FoodViewHolder>() {

    var foods = emptyList<Food>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return ListFoodViewHolderFactory(LayoutInflater.from(parent.context), parent)
            .setType(type)
            .create()
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foods[position], holder)
    }

    override fun getItemCount(): Int {
        return foods.size
    }
}

enum class ListType {
    VERTICAL, HORIZONTAL
}

class ListFoodViewHolderFactory(
    private val inflater: LayoutInflater,
    private val parent: ViewGroup
) {
    private var type: ListType = ListType.VERTICAL

    fun setType(type: ListType) = apply {
        this.type = type
    }

    @Suppress("UNCHECKED_CAST")
    fun create(): FoodViewHolder {
        return when (type) {
            ListType.VERTICAL -> FoodVerticalViewHolder(
                ItemDashboardFoodVerticalBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            ListType.HORIZONTAL -> FoodHorizontalViewHolder(
                ItemDashobardFoodHorizontalBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
        }
    }
}

abstract class FoodViewHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Food, holder: RecyclerView.ViewHolder) {
        when (holder) {
            is FoodVerticalViewHolder -> {
                holder.binding.food = data
            }
            is FoodHorizontalViewHolder -> {
                holder.binding.food = data
            }
        }
    }
}

class FoodVerticalViewHolder(val binding: ItemDashboardFoodVerticalBinding) :
    FoodViewHolder(binding)

class FoodHorizontalViewHolder(val binding: ItemDashobardFoodHorizontalBinding) :
    FoodViewHolder(binding)

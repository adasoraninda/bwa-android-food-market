package com.codetron.foodmarketmvp.ui.home.dashboard.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codetron.foodmarketmvp.model.domain.food.FoodItem

class FoodListAdapter(
    private val type: ListType,
    private val onItemClick: (id: Int?) -> Unit
) : RecyclerView.Adapter<FoodViewHolder>() {

    private val foods = arrayListOf<FoodItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItemFoods(foods: List<FoodItem>) {
        this.foods.addAll(emptyList())
        this.foods.addAll(foods)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return ListFoodViewHolderFactory(
            LayoutInflater.from(parent.context),
            parent,
            type,
            onItemClick
        ).create()
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foods[position])
    }

    override fun getItemCount(): Int {
        return foods.size
    }
}

enum class ListType {
    VERTICAL, HORIZONTAL
}




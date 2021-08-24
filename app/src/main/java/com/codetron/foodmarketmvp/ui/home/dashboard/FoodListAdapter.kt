package com.codetron.foodmarketmvp.ui.home.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codetron.foodmarketmvp.data.model.Food

class FoodListAdapter(private val type: ListType) :
    RecyclerView.Adapter<FoodViewHolder>() {

    private val foods = arrayListOf<Food>()

    fun setItemFoods(foods: List<Food>) {
        this.foods.addAll(emptyList())
        this.foods.addAll(foods)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return ListFoodViewHolderFactory(LayoutInflater.from(parent.context), parent)
            .setType(type)
            .create()
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




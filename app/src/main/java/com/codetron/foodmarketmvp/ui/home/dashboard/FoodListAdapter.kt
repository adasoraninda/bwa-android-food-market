package com.codetron.foodmarketmvp.ui.home.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codetron.foodmarketmvp.data.model.Food

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




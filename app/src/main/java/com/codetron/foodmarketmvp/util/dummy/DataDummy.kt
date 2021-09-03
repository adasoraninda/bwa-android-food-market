package com.codetron.foodmarketmvp.util.dummy

import com.codetron.foodmarketmvp.model.domain.food.FoodItem

object DataDummy {

    fun getFoods(): List<FoodItem> {
        val foods = arrayListOf<FoodItem>()

        repeat(10) {
            foods.add(
                FoodItem(
                    id = it,
                    picturePath = "",
                    name = "Cireng",
                    price = it.times(1000),
                    rate = 4,
                )
            )
        }

        return foods
    }
}
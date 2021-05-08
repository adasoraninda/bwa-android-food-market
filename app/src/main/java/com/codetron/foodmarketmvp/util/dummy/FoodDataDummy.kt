package com.codetron.foodmarketmvp.util.dummy

import com.codetron.foodmarketmvp.data.model.Food

object FoodDataDummy {

    fun getFoods(): List<Food> {
        val foods = arrayListOf<Food>()
        repeat(10) {
            foods.add(
                Food(
                    id = it.toLong(),
                    image = "",
                    name = "Cireng",
                    price = it.toLong().times(1000),
                    rate = 4.5F,
                )
            )
        }
        return foods
    }

}
package com.codetron.foodmarketmvp.util.dummy

import com.codetron.foodmarketmvp.model.domain.food.Food

object DataDummy {

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
                    description = "Makanan khas Bandung yang cukup sering\n" +
                            "dipesan oleh anak muda dengan pola makan\n" +
                            "yang cukup tinggi dengan mengutamakan\n" +
                            "diet yang sehat dan teratur.",
                    ingredients = "Seledri, telur, blueberry, madu."
                )
            )
        }

        return foods
    }
}
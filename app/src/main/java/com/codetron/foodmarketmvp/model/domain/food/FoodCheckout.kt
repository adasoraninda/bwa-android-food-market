package com.codetron.foodmarketmvp.model.domain.food

import android.os.Parcelable
import com.codetron.foodmarketmvp.model.domain.prices.PricesCalculator
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodCheckout(
    val food: Food,
    val totalFood: Int,
    val pricesCalculator: PricesCalculator = PricesCalculator(),
) : Parcelable {

    fun calculateTotal(): Int {
        val totalPrice = food.price.times(totalFood)
        val totalWithTax = totalPrice.plus(calculateTax())

        return totalWithTax.plus(pricesCalculator.driverPrice)
    }

    fun calculateTax(): Int {
        val percent = 100
        val taxPercent = pricesCalculator.tax.div(percent)

        return food.price.times(taxPercent).toInt()
    }

}
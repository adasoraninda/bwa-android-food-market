package com.codetron.foodmarketmvp.model.domain.prices

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PricesCalculator(
    val driverPrice: Int = 10000,
    val tax: Double = 5.0
) : Parcelable
package com.codetron.foodmarketmvp.util

import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.codetron.foodmarketmvp.R

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:showBackButton")
    fun Toolbar.showBackButton(isShow: Boolean?) {
        navigationIcon = if (isShow == true) {
            ContextCompat.getDrawable(this.context, R.drawable.ic_arrow_back)
        } else {
            null
        }
    }

}
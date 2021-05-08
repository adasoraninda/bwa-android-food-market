package com.codetron.foodmarketmvp.util

import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codetron.foodmarketmvp.R

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:showBackButton")
    fun Toolbar.showBackButton(isShow: Boolean? = false) {
        navigationIcon = if (isShow == true) {
            ContextCompat.getDrawable(this.context, R.drawable.ic_arrow_back)
        } else {
            null
        }
    }

    @JvmStatic
    @BindingAdapter("app:imageRes")
    fun ImageView.setImageResource(res: String?) {
        Glide.with(this)
            .applyDefaultRequestOptions(
                RequestOptions()
                    .placeholder(R.color.yellow_light)
                    .error(R.drawable.ic_error_image)
            )
            .load(res)
            .into(this)
    }

}
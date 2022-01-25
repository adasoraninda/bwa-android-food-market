package com.codetron.foodmarketmvp.util

import android.content.res.Resources
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codetron.foodmarketmvp.R
import java.text.NumberFormat
import java.util.*

fun Toolbar.showBackButton(isShow: Boolean? = false) {
    navigationIcon = if (isShow == true) {
        ContextCompat.getDrawable(this.context, R.drawable.ic_arrow_back_black)
    } else {
        null
    }
}

fun ImageView.setImageResource(res: String?) {
    Glide.with(this)
        .load(res)
        .apply(
            RequestOptions()
                .placeholder(R.color.yellow_light)
                .error(R.drawable.ic_error_image)
        )
        .into(this)
}

fun TextView.setTextResources(res: Int?) {
    text = res?.let { context.getString(it) }
}

fun TextView.setTextPrice(price: Int?) {
    val localeID = Locale("in", "ID")
    val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
    text = formatRupiah.format(price?.toDouble())
        .replace("Rp", "IDR ", ignoreCase = true)
}

fun TextView.setTextTotalItems(total: Int?) {
    text = String.format(context.getString(R.string.format_total_item), total)
}

val Int.px: Int
    get() = this * Resources.getSystem().displayMetrics.densityDpi

val Int.dp: Int
    get() = this / Resources.getSystem().displayMetrics.densityDpi

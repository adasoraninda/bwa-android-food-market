package com.codetron.foodmarketmvp.util

import android.content.Context
import android.util.DisplayMetrics

// extension function to convert pixels to dp
// this method return dp perfect float value
fun Int.pixelsToDp(context: Context): Float {
    return this /
            (context.resources.displayMetrics.densityDpi /
                    DisplayMetrics.DENSITY_DEFAULT).toFloat()
}


// extension function to convert pixels value to dp value
// this method return integer dp value
fun Int.pixelsToDpInt(context: Context): Int {
    return this /
            (context.resources.displayMetrics.densityDpi /
                    DisplayMetrics.DENSITY_DEFAULT)
}

// extension function to convert dp value to pixels value
// this method return integer px value
fun Int.dpToPixels(context: Context): Int {
    return this *
            (context.resources.displayMetrics.densityDpi /
                    DisplayMetrics.DENSITY_DEFAULT)
}
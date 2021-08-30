package com.codetron.foodmarketmvp.ui.customview

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.util.dpToPixels
import com.codetron.foodmarketmvp.util.pixelsToDpInt
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout


class SnackBarError(val viewGroup: ViewGroup) {

    private val snackBar by lazy { Snackbar.make(viewGroup.rootView, "", Snackbar.LENGTH_LONG) }
    private var textError: TextView

    init {
        val layout = (snackBar.view as SnackbarLayout).apply {
            setBackgroundColor(Color.TRANSPARENT)
            setPadding(0, 0, 0, 0)
            layoutParams = (this.layoutParams as FrameLayout.LayoutParams).apply {
                gravity = Gravity.TOP
                setMargins(0, 48.dpToPixels(context), 0, 0)
            }
        }

        val customSnackView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_snack_bar_error, viewGroup, false)

        textError = customSnackView.findViewById(R.id.txt_snack_error)

        layout.addView(customSnackView)
    }

    fun setMessage(message: String) {
        textError.text = message
    }

    fun show() {
        snackBar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
        snackBar.show()
    }

}
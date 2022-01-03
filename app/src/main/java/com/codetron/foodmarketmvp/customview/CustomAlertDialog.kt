package com.codetron.foodmarketmvp.customview

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import com.codetron.foodmarketmvp.R


class CustomAlertDialog(
    @StringRes private val titleRes: Int,
    @StringRes private val descriptionRes: Int,
    private val buttonOkClickListener: () -> Unit
) : DialogFragment() {

    private var txtTitle: TextView? = null
    private var txtDesc: TextView? = null
    private var btnOk: Button? = null
    private var btnCancel: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_alert_dialog, container, false)
        txtTitle = view?.findViewById(R.id.txt_alert_title)
        txtDesc = view?.findViewById(R.id.txt_alert_desc)
        btnOk = view?.findViewById(R.id.btn_ok)
        btnCancel = view?.findViewById(R.id.btn_cancel)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtTitle?.text = getString(titleRes)
        txtDesc?.text = getString(descriptionRes)

        btnOk?.setOnClickListener {
            buttonOkClickListener.invoke()
            dismiss()
        }

        btnCancel?.setOnClickListener { dismiss() }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

}
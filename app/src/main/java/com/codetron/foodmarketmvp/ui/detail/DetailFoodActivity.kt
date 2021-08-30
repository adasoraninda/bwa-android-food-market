package com.codetron.foodmarketmvp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.codetron.foodmarketmvp.databinding.ActivityDetailFoodBinding
import com.codetron.foodmarketmvp.databinding.LayoutBottomSheetDetailFoodBinding
import com.codetron.foodmarketmvp.databinding.LayoutDetailTotalFoodBinding
import com.codetron.foodmarketmvp.util.dummy.DataDummy

class DetailFoodActivity : AppCompatActivity() {

    private var _binding: ActivityDetailFoodBinding? = null
    private val binding get() = _binding

    private val lytBottomSheetDetail: LayoutBottomSheetDetailFoodBinding?
            by lazy { binding?.lytBottomSheet }

    private val lytTotalFood: LayoutDetailTotalFoodBinding?
            by lazy { lytBottomSheetDetail?.lytTotalFood }

    private val detailFoodArgs: DetailFoodActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailFoodBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.food = DataDummy.getFoods().first { food ->
            food.id == detailFoodArgs.fooId
        }
        lytTotalFood?.btnMin?.isEnabled = false
        lytTotalFood?.btnMin?.setOnClickListener {
            try {
                val total = lytTotalFood?.txtTotalProduct?.text?.toString()?.trim()?.toInt()

                total?.let { totalNumber ->
                    if (totalNumber > 1) {
                        lytTotalFood?.txtTotalProduct?.text = total.minus(1).toString()
                    }
                }

                if (lytTotalFood?.txtTotalProduct?.text?.toString()?.toInt()?.compareTo(1) == 0) {
                    lytTotalFood?.btnMin?.isEnabled = false
                }

            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
        }
        lytTotalFood?.btnAdd?.setOnClickListener {
            try {
                val total = lytTotalFood?.txtTotalProduct?.text?.toString()?.trim()?.toInt()
                lytTotalFood?.txtTotalProduct?.text = total?.plus(1).toString()
                lytTotalFood?.btnMin?.isEnabled = true
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
        }

        binding?.tlbDetailFood?.setNavigationOnClickListener { finish() }
    }

}
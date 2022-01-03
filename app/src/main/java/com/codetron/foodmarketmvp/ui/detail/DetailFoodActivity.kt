package com.codetron.foodmarketmvp.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.codetron.foodmarketmvp.databinding.ActivityDetailFoodBinding
import com.codetron.foodmarketmvp.databinding.LayoutBottomSheetDetailFoodBinding
import com.codetron.foodmarketmvp.databinding.LayoutDetailTotalFoodBinding
import com.codetron.foodmarketmvp.model.domain.food.Food
import com.codetron.foodmarketmvp.model.domain.food.FoodCheckout
import com.codetron.foodmarketmvp.ui.payment.PaymentActivity
import com.codetron.foodmarketmvp.util.setTextPrice
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DetailFoodActivity : AppCompatActivity(), DetailFoodContract.View {

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

        if (savedInstanceState == null) {

        }

       binding?.tlbDetailFood?.setNavigationOnClickListener { finish() }
    }

    override fun initState() {
        lytTotalFood?.btnMin?.isEnabled = false
        lytTotalFood?.btnAdd?.isEnabled = false
        lytBottomSheetDetail?.btnOrderNow?.isEnabled = false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun setTotalProduct(total: Int) {
        lytTotalFood?.txtTotalProduct?.text = total.toString()
    }

    override fun showLoading() {
        binding?.pbrDetail?.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        binding?.pbrDetail?.visibility = View.GONE
    }

    override fun onGetDataSuccess(food: Food) {
        lytBottomSheetDetail?.btnOrderNow?.isEnabled = true
        lytTotalFood?.btnAdd?.isEnabled = true
    }

    override fun toggleMinButton(isEnabled: Boolean) {
        lytTotalFood?.btnMin?.isEnabled = isEnabled
    }

    override fun onGetDataFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun submitCheckout(food: FoodCheckout) {
        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra(PaymentActivity.KEY_FOOD_CHECKOUT, food)
        startActivity(intent)
    }

    override fun updatePrice(price: Int) {
        lytBottomSheetDetail?.txtPrice?.setTextPrice(price)
    }
}
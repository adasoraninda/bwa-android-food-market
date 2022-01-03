package com.codetron.foodmarketmvp.ui.payment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codetron.foodmarketmvp.databinding.ActivityPaymentBinding
import com.codetron.foodmarketmvp.model.domain.food.FoodCheckout
import com.codetron.foodmarketmvp.model.domain.user.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PaymentActivity : AppCompatActivity(), PaymentContract.View {

    private var _binding: ActivityPaymentBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.tlbPayment?.setNavigationOnClickListener { finish() }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun initState() {
        binding?.btnCheckoutNow?.isEnabled = false
    }

    override fun showLoading() {
        binding?.pbrPayment?.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        binding?.pbrPayment?.visibility = View.GONE
    }

    override fun onGetFoodDataSuccess(foodCheckout: FoodCheckout) {
    }

    override fun onGetFoodDataFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onGetUserDataSuccess(user: User) {
        binding?.btnCheckoutNow?.isEnabled = true
    }

    override fun onGetUserDataFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val KEY_FOOD_CHECKOUT = "KEY_FOOD_CHECKOUT"
    }

}
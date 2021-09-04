package com.codetron.foodmarketmvp.ui.payment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codetron.foodmarketmvp.databinding.ActivityPaymentBinding
import com.codetron.foodmarketmvp.model.domain.food.FoodCheckout
import com.codetron.foodmarketmvp.model.domain.user.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class PaymentActivity : AppCompatActivity(), PaymentContract.View {

    private var _binding: ActivityPaymentBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var presenterFactory: PaymentPresenter.Factory

    private val presenter by lazy {
        presenterFactory.create(intent.getParcelableExtra(KEY_FOOD_CHECKOUT))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        presenter.subscribe()

        binding?.tlbPayment?.setNavigationOnClickListener { finish() }
        binding?.btnCheckoutNow?.setOnClickListener { presenter.submitCheckout() }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        presenter.unSubscribe()
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
        binding?.food = foodCheckout
        binding?.executePendingBindings()
    }

    override fun onGetFoodDataFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onGetUserDataSuccess(user: User) {
        binding?.user = user
        binding?.executePendingBindings()
        binding?.btnCheckoutNow?.isEnabled = true
    }

    override fun onGetUserDataFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val KEY_FOOD_CHECKOUT = "KEY_FOOD_CHECKOUT"

        fun navigate(context: Context, data: FoodCheckout) {
            val intent = Intent(context, PaymentActivity::class.java)
            intent.putExtra(KEY_FOOD_CHECKOUT, data)
            context.startActivity(intent)
        }
    }

}
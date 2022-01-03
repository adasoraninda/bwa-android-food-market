package com.codetron.foodmarketmvp.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.customview.CustomAlertDialog
import com.codetron.foodmarketmvp.databinding.ActivityAuthBinding
import com.codetron.foodmarketmvp.util.showBackButton

private const val ALERT_AUTH_TAG = "ALERT_AUTH_TAG"

class AuthActivity : AppCompatActivity() {

    private var _binding: ActivityAuthBinding? = null
    private val binding get() = _binding

    private lateinit var customAlertDialog: CustomAlertDialog
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nhf_auth) as NavHostFragment

        navController = navHostFragment.findNavController()

        customAlertDialog = CustomAlertDialog(R.string.alert_title_exit, R.string.alert_desc_exit) {
            navController.popBackStack()
        }

        binding?.tlbAuth?.setNavigationOnClickListener {
            onBackPressed()
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.sign_in_fragment -> {
                    binding?.tlbAuth?.title = getString(R.string.sign_in)
                    binding?.tlbAuth?.subtitle = getString(R.string.sign_in_subtitle)
                    binding?.tlbAuth?.showBackButton(false)
                }
                R.id.sign_up_fragment -> {
                    binding?.tlbAuth?.title = getString(R.string.sign_up)
                    binding?.tlbAuth?.subtitle = getString(R.string.sign_up_subtitle)
                    binding?.tlbAuth?.showBackButton(true)
                }
                R.id.sign_up_address_fragment -> {
                    binding?.tlbAuth?.title = getString(R.string.address)
                    binding?.tlbAuth?.subtitle = getString(R.string.sign_up_address_subtitle)
                    binding?.tlbAuth?.showBackButton(true)
                }
                else -> throw IllegalArgumentException("No destination")
            }
        }
    }

    override fun onBackPressed() {
        val id = navController.currentDestination?.id
        if (id == R.id.sign_in_fragment || id == R.id.sign_up_success_activity) {
            finish()
            return
        }

        customAlertDialog.show(supportFragmentManager, ALERT_AUTH_TAG)
    }

}
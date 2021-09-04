package com.codetron.foodmarketmvp.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.databinding.ActivityAuthBinding
import com.codetron.foodmarketmvp.model.domain.view.ToolbarData
import com.codetron.foodmarketmvp.ui.customview.CustomAlertDialog
import dagger.hilt.android.AndroidEntryPoint

private const val ALERT_AUTH_TAG = "ALERT_AUTH_TAG"

@AndroidEntryPoint
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
            binding?.toolbarData = when (destination.id) {
                R.id.sign_in_fragment -> {
                    ToolbarData(
                        title = getString(R.string.sign_in),
                        subtitle = getString(R.string.sign_in_subtitle),
                        showBackButton = false
                    )
                }
                R.id.sign_up_fragment -> {
                    ToolbarData(
                        title = getString(R.string.sign_up),
                        subtitle = getString(R.string.sign_up_subtitle),
                        showBackButton = true
                    )
                }
                R.id.sign_up_address_fragment -> {
                    ToolbarData(
                        title = getString(R.string.address),
                        subtitle = getString(R.string.sign_up_address_subtitle),
                        showBackButton = true
                    )
                }
                else -> throw IllegalArgumentException("No destination")
            }
        }
    }

    companion object {
        fun navigate(context: Context) {
            context.startActivity(Intent(context, AuthActivity::class.java))
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
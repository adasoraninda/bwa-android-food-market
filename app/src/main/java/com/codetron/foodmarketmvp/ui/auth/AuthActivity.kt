package com.codetron.foodmarketmvp.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.data.model.ToolbarData
import com.codetron.foodmarketmvp.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private var _binding: ActivityAuthBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nhf_auth) as NavHostFragment

        val navController = navHostFragment.findNavController()

        binding?.tlbAuth?.setNavigationOnClickListener {
            navController.popBackStack()
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


}
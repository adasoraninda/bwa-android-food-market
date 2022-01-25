package com.codetron.foodmarketmvp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.databinding.ActivityHomeBinding
import com.codetron.foodmarketmvp.di.component.ActivityComponent
import com.codetron.foodmarketmvp.util.activityComponent
import com.codetron.foodmarketmvp.util.appComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = application.appComponent().activityComponent(this)
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nhf_home) as NavHostFragment

        val navController = navHostFragment.findNavController()

        binding?.bnvHome?.setupWithNavController(navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
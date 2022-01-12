package com.codetron.foodmarketmvp.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codetron.foodmarketmvp.FoodMarketApplication
import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.di.module.ui.activity.ActivityModule
import com.codetron.foodmarketmvp.ui.auth.AuthActivity
import com.codetron.foodmarketmvp.ui.home.HomeActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as FoodMarketApplication)
            .appComponent
            .newActivityComponentBuilder()
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (savedInstanceState == null) {
            presenter.subscribe()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unSubscribe()
    }

    override fun navigate(isLoggedIn: Boolean) {
        val destination = if (isLoggedIn) {
            HomeActivity::class.java
        } else {
            AuthActivity::class.java
        }
        val intent = Intent(this, destination)
        startActivity(intent)
        finishAffinity()
    }


}
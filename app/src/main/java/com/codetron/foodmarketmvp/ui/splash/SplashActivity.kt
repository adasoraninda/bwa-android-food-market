package com.codetron.foodmarketmvp.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.ui.auth.AuthActivity
import com.codetron.foodmarketmvp.ui.home.HomeActivity
import com.codetron.foodmarketmvp.util.activityComponent
import com.codetron.foodmarketmvp.util.appComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDagger()
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

    private fun injectDagger() {
        application.appComponent()
            .activityComponent(this)
            .inject(this)
    }

}
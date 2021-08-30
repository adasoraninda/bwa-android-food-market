package com.codetron.foodmarketmvp.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.ui.auth.AuthActivity
import com.codetron.foodmarketmvp.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalCoroutinesApi
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(), SplashContract.View {

    companion object {
        const val KEY_HOME = "HOME"
        const val KEY_LOGIN = "LOGIN"
    }

    @Inject
    lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.subscribe()
    }

    override fun navigate(key: String) {
        if (key == KEY_HOME) HomeActivity.navigate(this)
        if (key == KEY_LOGIN) AuthActivity.navigate(this)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unSubscribe()
    }

}
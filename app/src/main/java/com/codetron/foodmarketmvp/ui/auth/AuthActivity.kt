package com.codetron.foodmarketmvp.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codetron.foodmarketmvp.R

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

    }

    companion object {
        fun navigate(context: Context) {
            context.startActivity(Intent(context, AuthActivity::class.java))
        }
    }

}
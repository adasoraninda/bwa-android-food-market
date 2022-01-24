package com.codetron.foodmarketmvp.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codetron.foodmarketmvp.databinding.ActivitySignUpSuccessBinding
import com.codetron.foodmarketmvp.ui.home.HomeActivity

class SignUpSuccessActivity : AppCompatActivity() {

    private var _binding: ActivitySignUpSuccessBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpSuccessBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnFindFoods?.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
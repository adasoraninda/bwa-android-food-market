package com.codetron.foodmarketmvp.ui.auth.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codetron.foodmarketmvp.databinding.ActivitySignUpSuccessBinding

class SignUpSuccessActivity : AppCompatActivity() {

    private var _binding: ActivitySignUpSuccessBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpSuccessBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnFindFoods?.setOnClickListener {
            // TODO(MOVE TO HOME)
            finishAffinity()
        }

    }

}
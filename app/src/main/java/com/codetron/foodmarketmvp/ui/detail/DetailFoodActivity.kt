package com.codetron.foodmarketmvp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codetron.foodmarketmvp.databinding.ActivityDetailFoodBinding

class DetailFoodActivity : AppCompatActivity() {

    private var _binding: ActivityDetailFoodBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailFoodBinding.inflate(layoutInflater)
        setContentView(binding?.root)

    }

}
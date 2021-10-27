package com.example.doggyphotos

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.example.doggyphotos.api.ApiRequest
import com.example.doggyphotos.api.DogApiResponse
import com.example.doggyphotos.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        backgroundAnimation()
        callDogPhoto()






        binding.fab.setOnClickListener {
            binding.fab.animate().apply {
                rotationBy(360f)
                duration = 1000
            }.start()
            viewModel.getNewDog()
            binding.imageView.visibility = View.GONE

        }

    }

    private fun callDogPhoto() {

        viewModel.apiResponse.observe(this,
            {
                Glide.with(this)
                    .load(it.message)
                    .circleCrop()
                    .into(binding.imageView)
                binding.imageView.visibility = View.VISIBLE
            })
    }




    private fun backgroundAnimation() {
        val animationDrawable: AnimationDrawable = binding.mainLayout.background as AnimationDrawable

        animationDrawable.apply {
            setEnterFadeDuration(1000)
            setExitFadeDuration(3000)
            start()
        }

    }
}
package com.example.doggyphotos

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.doggyphotos.databinding.FragmentMainBinding
import java.util.Optional.of
import java.util.Set.of


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        backgroundAnimation()
        callDogPhoto()
        val action =

        binding.fab.setOnClickListener {
            binding.fab.animate().apply {
                rotationBy(360f)
                duration = 1000
            }.start()
            viewModel.getNewDog()
            binding.imageView.visibility = View.GONE

        }

        binding.fabSearch.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToSearchFragment())
        }

    }

    private fun callDogPhoto() {

        viewModel.apiResponse.observe(viewLifecycleOwner,
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
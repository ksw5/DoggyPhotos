package com.example.doggyphotos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.doggyphotos.R
import com.example.doggyphotos.databinding.FragmentMainBinding
import com.example.doggyphotos.databinding.FragmentSearchBinding
import com.example.doggyphotos.models.MainViewModel


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        breedImage()




    }

    private fun breedImage() {
        binding.searchBreedBtn.setOnClickListener {
            val userSearch = binding.userSearch.text.toString()
            viewModel.getUserBreed(userSearch.lowercase())
            viewModel.apiResponse.observe(viewLifecycleOwner,
                {
                    Glide.with(this)
                        .load(it.message)
                        .circleCrop()
                        .into(binding.imageViewSearch)
            })
        }
    }



}


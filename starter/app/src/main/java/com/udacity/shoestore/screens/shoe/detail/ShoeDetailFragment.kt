package com.udacity.shoestore.screens.shoe.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import timber.log.Timber

class ShoeDetailFragment : Fragment() {

    private lateinit var viewModel: ShoeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = getBinding()

        Timber.i("Called ViewModelProvider")
        viewModel = ViewModelProvider(this).get(ShoeDetailViewModel::class.java)
        binding.shoeDetailViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    private fun goToInstructionsScreen() {
        //val action =
        //NavHostFragment.findNavController(this).navigate(action)
    }

    private fun getBinding() = ShoeDetailFragmentBinding.inflate(layoutInflater)

}
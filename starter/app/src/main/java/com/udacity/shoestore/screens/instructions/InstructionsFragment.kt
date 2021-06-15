package com.udacity.shoestore.screens.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.databinding.InstructionsFragmentBinding
import timber.log.Timber

class InstructionsFragment : Fragment() {

    private lateinit var viewModel: InstructionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = getBinding()

        Timber.i("Called ViewModelProvider")
        viewModel = ViewModelProvider(this).get(InstructionsViewModel::class.java)
        binding.instructionsViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    private fun goToInstructionsScreen() {
        //val action =
        //NavHostFragment.findNavController(this).navigate(action)
    }

    private fun getBinding() = InstructionsFragmentBinding.inflate(layoutInflater)

}
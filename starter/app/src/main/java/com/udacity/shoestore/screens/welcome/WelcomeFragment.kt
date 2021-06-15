package com.udacity.shoestore.screens.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.databinding.WelcomeFragmentBinding
import com.udacity.shoestore.screens.instructions.InstructionsFragmentDirections
import timber.log.Timber

class WelcomeFragment : Fragment() {

    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = getBinding()

        Timber.i("Called ViewModelProvider")
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        binding.welcomeViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    private fun goToInstructionsScreen() {
        InstructionsFragmentDirections.actionInstructionToShoeList().let {
            NavHostFragment.findNavController(this).navigate(it)
        }
    }

    private fun getBinding() = WelcomeFragmentBinding.inflate(layoutInflater)

}
package com.udacity.shoestore.screens.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.databinding.WelcomeFragmentBinding

class WelcomeFragment : Fragment() {

    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = getBinding()

        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        binding.welcomeViewModel = viewModel
        binding.lifecycleOwner = this
        binding.welcome = viewModel.welcome

        viewModel.eventCompleteInstructions.observe(viewLifecycleOwner,
            Observer { hasSkip ->
                if (hasSkip) {
                    goToInstructionsScreen()
                    viewModel.onSkipComplete()
                }
            }
        )
        return binding.root
    }

    private fun goToInstructionsScreen() {
        WelcomeFragmentDirections.actionWelcomeToInstruction().let {
            NavHostFragment.findNavController(this).navigate(it)
        }
    }

    private fun getBinding() = WelcomeFragmentBinding.inflate(layoutInflater)

}
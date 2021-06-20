package com.udacity.shoestore.screens.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.WelcomeFragmentBinding

class WelcomeFragment : Fragment() {

    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = getBinding().let {

        viewModel = providerViewModel()

        it.welcomeViewModel = viewModel
        it.welcome = viewModel.welcome

        it.lifecycleOwner = this

        createLiveData()

        return it.root
    }

    private fun createLiveData() {
        viewModel.eventCompleteInstructions.observe(viewLifecycleOwner,
            Observer { hasSkip ->
                if (hasSkip) {
                    goToInstructionsScreen()
                    viewModel.onSkipComplete()
                }
            }
        )
    }

    private fun providerViewModel() =
        ViewModelProvider(this).get(WelcomeViewModel::class.java)

    private fun goToInstructionsScreen() {
        WelcomeFragmentDirections.actionWelcomeToInstruction().let {
            findNavController().navigate(it)
        }
    }

    private fun getBinding() = WelcomeFragmentBinding.inflate(layoutInflater)

}
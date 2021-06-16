package com.udacity.shoestore.screens.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.databinding.InstructionsFragmentBinding

class InstructionsFragment : Fragment() {

    private lateinit var viewModel: InstructionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = getBinding().let {

        viewModel = providerViewModel()
        it.instructionsViewModel = viewModel
        it.instruction = viewModel.instruction
        it.lifecycleOwner = this

        createLiveData()

        return it.root
    }

    private fun createLiveData() {

        viewModel.eventCompleteInstructions.observe(viewLifecycleOwner,
            Observer { hasSkip ->
                if (hasSkip) {
                    goToShoeListScreen()
                    viewModel.onSkipComplete()
                }
            }
        )
    }

    private fun providerViewModel() =
        ViewModelProvider(this).get(InstructionsViewModel::class.java)

    private fun goToShoeListScreen() {
        InstructionsFragmentDirections.actionInstructionToShoeList().let {
            NavHostFragment.findNavController(this).navigate(it)
        }
    }

    private fun getBinding() = InstructionsFragmentBinding.inflate(layoutInflater)

}
package com.udacity.shoestore.screens.shoe.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = getBinding()

        Timber.i("Called ViewModelProvider")
        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
        binding.shoeListViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    private fun goToInstructionsScreen() {
        //val action =
        //NavHostFragment.findNavController(this).navigate(action)
    }

    private fun getBinding() = ShoeListFragmentBinding.inflate(layoutInflater)

}
package com.udacity.shoestore.screens.shoe.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.screens.instructions.InstructionsFragmentDirections
import com.udacity.shoestore.screens.instructions.InstructionsViewModel
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = getBinding().let {

        viewModel = providerViewModel()

        it.shoeListViewModel = viewModel
        it.lifecycleOwner = this

        createLiveData()

        return it.root
    }

    private fun createLiveData() {

    }

    private fun providerViewModel() =
        ViewModelProvider(this).get(ShoeListViewModel::class.java)

    private fun goToShoeDetailScreen() {
        ShoeListFragmentDirections.actionShoeListToShoeDetail().let {
            NavHostFragment.findNavController(this).navigate(it)
        }
    }

    private fun getBinding() = ShoeListFragmentBinding.inflate(layoutInflater)

}
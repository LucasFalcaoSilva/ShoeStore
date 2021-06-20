package com.udacity.shoestore.screens.shoe.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListFragmentBinding

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        NavigationUI.onNavDestinationSelected(item, findNavController())

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
            findNavController().navigate(it)
        }
    }

    private fun getBinding() = ShoeListFragmentBinding.inflate(layoutInflater)

}
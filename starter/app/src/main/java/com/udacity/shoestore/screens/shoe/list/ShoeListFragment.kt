package com.udacity.shoestore.screens.shoe.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.data.PreferenceManager
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var mainLinear: LinearLayout

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.loginFragment) {
            viewModel.onLogout()
            goToLoginScreen()
        }
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = getBinding().let {

        setHasOptionsMenu(true)

        viewModel = providerViewModel()

        it.shoeListViewModel = viewModel
        it.lifecycleOwner = this

        mainLinear = it.mainShoeListLinear

        createLiveData()

        return it.root
    }

    private fun createLiveData() {
        viewModel.eventAddShoe.observe(viewLifecycleOwner,
            Observer { addShoe ->
                if (addShoe) {
                    goToShoeDetailScreen()
                    viewModel.addShoeComplete()
                }
            }
        )
        mainViewModel.shoeList.observe(viewLifecycleOwner,
            Observer { shoeList ->
                shoeList?.map {
                    addItem(it)
                }
            }
        )
    }

    private fun addItem(itemShoe : Shoe) {
        Timber.i("New Shoe in Layout")
        ItemShoeBinding.inflate(
            layoutInflater,
            null,
            false
        ).apply {
            shoe = itemShoe
            mainLinear.addView(root)
        }

    }

    private fun providerViewModel() =
        ViewModelProvider(
            this,
            ShoeListViewModelFactory(
                PreferenceManager(
                    activity?.applicationContext
                )
            )
        ).get(ShoeListViewModel::class.java)

    private fun goToShoeDetailScreen() {
        ShoeListFragmentDirections.actionShoeListToShoeDetail().let {
            findNavController().navigate(it)
        }
    }

    private fun goToLoginScreen() {
        ShoeListFragmentDirections.actionShoeListToLogin().let {
            findNavController().navigate(it)
        }
    }

    private fun getBinding() = ShoeListFragmentBinding.inflate(layoutInflater)

}
package com.udacity.shoestore.screens.shoe.list

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.data.PreferenceManager
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var mainLinear: LinearLayout

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.loginFragment) {
            viewModel.onLogout()
        }
        return NavigationUI.onNavDestinationSelected(item, findNavController())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = getBinding().let {

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

    private fun addItem(shoe: Shoe) {
        Timber.i("New Shoe in Layout")
        TextView(context, null, 0, R.style.NameStyle).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            text = "Name: ${shoe.name} - Company: " +
                    "${shoe.company}\nSize: ${shoe.size}\nDescription: ${shoe.description}"

            mainLinear.addView(this)
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

    private fun getBinding() = ShoeListFragmentBinding.inflate(layoutInflater)

}
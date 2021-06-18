package com.udacity.shoestore.screens.shoe.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.login.LoginFragmentDirections

class ShoeDetailFragment : Fragment() {

    private lateinit var viewModel: ShoeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = getBinding().let {

        viewModel = providerViewModel()
        it.shoeDetailViewModel = viewModel
        it.lifecycleOwner = this

        createLiveData()

        return it.root
    }

    private fun createLiveData() {
        viewModel.eventHideKeyboard.observe(viewLifecycleOwner,
            Observer {
                if (it) {
                    hideKeyboard(getBinding().loginLayout)
                    viewModel.hideKeyboardComplete()
                }
            }
        )
        viewModel.shoe.observe(viewLifecycleOwner,
            Observer { shoe ->
                if (shoe != null) {
                    goToShoeListScreen(shoe)
                    viewModel.closeShoeDetail()
                }
            }
        )

    }

    private fun goToShoeListScreen(shoe: Shoe) {

    }

    private fun providerViewModel() =
        ViewModelProvider(this).get(ShoeDetailViewModel::class.java)

    private fun getBinding() = ShoeDetailFragmentBinding.inflate(layoutInflater)

    private fun hideKeyboard(view: View) {
        (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
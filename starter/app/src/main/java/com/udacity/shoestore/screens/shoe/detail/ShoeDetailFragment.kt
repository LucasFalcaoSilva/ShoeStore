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

class ShoeDetailFragment : Fragment() {

    private lateinit var viewModel: ShoeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = getBinding().let { binding ->

        viewModel = providerViewModel()
        binding.shoeDetailViewModel = viewModel
        binding.lifecycleOwner = this

        binding.saveButton.setOnClickListener {
            viewModel.onSaveShoe(
                shoeName = binding.shoeNameText.text.toString(),
                shoeSize = binding.shoeSizeText.text.toString(),
                shoeCompany = binding.companyText.text.toString(),
                shoeDescription = binding.descriptionText.text.toString()
            )
        }

        createLiveData()

        return binding.root
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

        viewModel.eventCancel.observe(viewLifecycleOwner,
            Observer {
                if (it) {
                    goToShoeListScreen()
                    viewModel.onCancelComplete()
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

    private fun goToShoeListScreen(shoe: Shoe? = null) {
        ShoeDetailFragmentDirections.actionShoeDetailToShoeList(shoe).let {
            NavHostFragment.findNavController(this).navigate(it)
        }
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
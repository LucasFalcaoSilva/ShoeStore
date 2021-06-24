package com.udacity.shoestore.screens.shoe.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeDetailFragment : Fragment() {

    private lateinit var viewModel: ShoeDetailViewModel

    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = getBinding().let { binding ->

        viewModel = providerViewModel()
        binding.shoeDetailViewModel = viewModel
        binding.lifecycleOwner = this
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

        viewModel.eventSave.observe(viewLifecycleOwner,
            Observer { save ->
                if (save) {
                    Timber.i("Tentando salvar Shoe")
                    model.addShoe(viewModel.shoe.value ?: Shoe())
                    goToShoeListScreen()
                    viewModel.onSaveComplete()
                }
            }
        )
    }

    private fun goToShoeListScreen() {
        findNavController().popBackStack()
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
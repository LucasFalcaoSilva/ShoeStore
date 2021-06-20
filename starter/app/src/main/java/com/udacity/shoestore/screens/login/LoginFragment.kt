package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.data.PreferenceManager
import com.udacity.shoestore.databinding.LoginFragmentBinding
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = getBinding().let {

        Timber.i("Called ViewModelProvider")
        viewModel = providerViewModel()

        it.loginViewModel = viewModel
        it.lifecycleOwner = this

        createLiveData()

        return it.root
    }

    private fun createLiveData() {
        viewModel.eventSignIn.observe(viewLifecycleOwner,
            Observer { hasSignIn ->
                if (hasSignIn) {
                    goToWelcomeScreen()
                    viewModel.onSignInComplete()
                }
            }
        )

        viewModel.eventSignUp.observe(viewLifecycleOwner,
            Observer { hasSignUp ->
                if (hasSignUp) {
                    goToWelcomeScreen()
                    viewModel.onSignUpComplete()
                }
            }
        )

        viewModel.eventLogged.observe(viewLifecycleOwner,
            Observer { hasLogged ->
                if (hasLogged) {
                    goToShoeListScreen()
                    viewModel.onLoggedComplete()
                }
            }
        )
    }

    private fun providerViewModel() =
        ViewModelProvider(
            this,
            LoginViewModelFactory(
                PreferenceManager(
                    activity?.applicationContext
                )
            )
        ).get(LoginViewModel::class.java)

    private fun goToWelcomeScreen() {
        LoginFragmentDirections.actionLoginToWelcome().let {
            findNavController().navigate(it)
        }
    }

    private fun goToShoeListScreen() {
        LoginFragmentDirections.actionLoginToShoeList().let {
            findNavController().navigate(it)
        }
    }

    private fun getBinding() =
        LoginFragmentBinding.inflate(layoutInflater)

}
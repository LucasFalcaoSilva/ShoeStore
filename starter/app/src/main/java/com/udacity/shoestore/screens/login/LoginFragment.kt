package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.databinding.LoginFragmentBinding
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        getBinding().let {

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
    }

    private fun providerViewModel() =
        ViewModelProvider(this).get(LoginViewModel::class.java)

    private fun goToWelcomeScreen() {
        LoginFragmentDirections.actionLoginToWelcome().let {
            NavHostFragment.findNavController(this).navigate(it)
        }
    }

    private fun getBinding() =
        LoginFragmentBinding.inflate(layoutInflater)

}
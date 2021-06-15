package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.databinding.LoginFragmentBinding
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = getBinding()

        Timber.i("Called ViewModelProvider")
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    private fun goToWelcomeScreen() {
        val action = LoginFragmentDirections.actionLoginToWelcome()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun getBinding() = LoginFragmentBinding.inflate(layoutInflater)

}
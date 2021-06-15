package com.udacity.shoestore.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel : ViewModel() {

    private var _eventSignIn = MutableLiveData<Boolean>()
    val eventSignIn: LiveData<Boolean>
        get() = _eventSignIn

    private var _eventSignUp = MutableLiveData<Boolean>()
    val eventSignUp: LiveData<Boolean>
        get() = _eventSignUp

    init {
        Timber.i("LoginViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("LoginViewModel destroyed!")
    }

    fun onSignIn() {
        Timber.i("do sign in")
        _eventSignIn.value = true
    }

    fun onSignUp() {
        Timber.i("do sign up")
        _eventSignUp.value = true
    }

    fun onSignInComplete() {
        _eventSignIn.value = false
    }

    fun onSignUpComplete() {
        _eventSignUp.value = false
    }


}
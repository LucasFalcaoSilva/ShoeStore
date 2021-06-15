package com.udacity.shoestore.screens.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class WelcomeViewModel : ViewModel() {

    private var _eventCompleteInstructions = MutableLiveData<Boolean>()
    val eventCompleteInstructions: LiveData<Boolean>
        get() = _eventCompleteInstructions

    init {
        Timber.i("WelcomeViewModel created!")

        _eventCompleteInstructions.value = false
    }

    fun onLogged() {
        Timber.i("do sign up")
        _eventCompleteInstructions.value = true
    }

    fun onSignInComplete() {
        _eventCompleteInstructions.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("WelcomeViewModel destroyed!")
    }
}
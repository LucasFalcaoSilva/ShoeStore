package com.udacity.shoestore.screens.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Welcome
import timber.log.Timber

class WelcomeViewModel : ViewModel() {

    val welcome = Welcome(
        title = "Welcome To my Shoe Store",
        description = "This Shoe Store is a project app for Udacity program where some skills will are demonstrate"
    )

    private var _eventSkipWelcome = MutableLiveData<Boolean>()
    val eventCompleteInstructions: LiveData<Boolean>
        get() = _eventSkipWelcome

    init {
        Timber.i("WelcomeViewModel created!")
        _eventSkipWelcome.value = false
    }

    fun onSkip() {
        Timber.i("do sign up")
        _eventSkipWelcome.value = true
    }

    fun onSkipComplete() {
        _eventSkipWelcome.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("WelcomeViewModel destroyed!")
    }
}
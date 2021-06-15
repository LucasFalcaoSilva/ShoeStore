package com.udacity.shoestore.screens.welcome

import androidx.lifecycle.ViewModel
import timber.log.Timber

class WelcomeViewModel : ViewModel() {
    init {
        Timber.i("WelcomeViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("WelcomeViewModel destroyed!")
    }
}
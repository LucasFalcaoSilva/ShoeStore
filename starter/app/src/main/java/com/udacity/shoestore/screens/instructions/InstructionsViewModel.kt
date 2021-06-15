package com.udacity.shoestore.screens.instructions

import androidx.lifecycle.ViewModel
import timber.log.Timber

class InstructionsViewModel : ViewModel() {
    init {
        Timber.i("InstructionsViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("InstructionsViewModel destroyed!")
    }
}
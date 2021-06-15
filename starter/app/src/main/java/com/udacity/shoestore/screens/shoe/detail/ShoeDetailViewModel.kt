package com.udacity.shoestore.screens.shoe.detail

import androidx.lifecycle.ViewModel
import timber.log.Timber

class ShoeDetailViewModel : ViewModel() {
    init {
        Timber.i("ShoeDetailViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ShoeDetailViewModel destroyed!")
    }
}
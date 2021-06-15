package com.udacity.shoestore.screens.shoe.list

import androidx.lifecycle.ViewModel
import timber.log.Timber

class ShoeListViewModel : ViewModel() {
    init {
        Timber.i("ShoeListViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ShoeListViewModel destroyed!")
    }
}
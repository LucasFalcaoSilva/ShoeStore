package com.udacity.shoestore.screens.shoe.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.data.PreferenceManager
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListViewModel(
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private var _eventAddShoe = MutableLiveData<Boolean>()
    val eventAddShoe: LiveData<Boolean>
        get() = _eventAddShoe

    init {
        Timber.i("ShoeListViewModel created!")
        _eventAddShoe.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ShoeListViewModel destroyed!")
    }

    fun addShoe() {
        _eventAddShoe.value = true
    }

    fun addShoeComplete() {
        _eventAddShoe.value = false
    }

    fun onLogout() {
        preferenceManager.resetLogin()
    }

}
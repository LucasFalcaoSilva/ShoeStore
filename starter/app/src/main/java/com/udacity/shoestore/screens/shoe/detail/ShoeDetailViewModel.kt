package com.udacity.shoestore.screens.shoe.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeDetailViewModel : ViewModel() {

    private var _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe

    private var _eventHideKeyboard = MutableLiveData<Boolean>()
    val eventHideKeyboard: LiveData<Boolean>
        get() = _eventHideKeyboard

    init {
        Timber.i("ShoeDetailViewModel created!")
        _eventHideKeyboard.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ShoeDetailViewModel destroyed!")
    }

    fun onCancel() {

    }

    fun onSaveShoe(
        shoeName: String,
        shoeSize: Double,
        shoeCompany: String,
        shoeDescription: String
    ) {
        hideKeyboard()


        _shoe.postValue(
            Shoe(
                name = shoeName,
                size = shoeSize,
                company = shoeCompany,
                description = shoeDescription

            )
        )
    }

    private fun hideKeyboard() {
        _eventHideKeyboard.value = true
    }

    fun hideKeyboardComplete() {
        _eventHideKeyboard.value = false
    }

    fun closeShoeDetail() {
        _shoe.postValue(null)
    }

}
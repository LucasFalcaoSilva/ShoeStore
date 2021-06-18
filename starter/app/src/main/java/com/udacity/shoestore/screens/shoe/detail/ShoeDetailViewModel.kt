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

    private var _eventCancel = MutableLiveData<Boolean>()
    val eventCancel: LiveData<Boolean>
        get() = _eventCancel

    init {
        Timber.i("ShoeDetailViewModel created!")
        _eventHideKeyboard.value = false
        _eventCancel.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ShoeDetailViewModel destroyed!")
    }

    fun onCancel() {
        _eventCancel.value = true
    }

    fun onSaveShoe(
        shoeName: String,
        shoeSize: String,
        shoeCompany: String,
        shoeDescription: String
    ) {
        hideKeyboard()

        _shoe.postValue(
            Shoe(
                name = shoeName.trim(),
                company = shoeCompany.trim(),
                description = shoeDescription.trim(),
                size = if (shoeSize.trim() == "")
                    0.0
                else
                    shoeSize.toDouble()
            )
        )
    }

    private fun hideKeyboard() {
        _eventHideKeyboard.value = true
    }

    fun hideKeyboardComplete() {
        _eventHideKeyboard.value = false
    }

    fun onCancelComplete() {
        _eventCancel.value = false
    }

    fun closeShoeDetail() {
        _shoe.postValue(null)
    }

}
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

    private var _eventSave = MutableLiveData<Boolean>()
    val eventSave: LiveData<Boolean>
        get() = _eventSave

    init {
        Timber.i("ShoeDetailViewModel created!")
        _eventHideKeyboard.value = false
        _eventCancel.value = false
        _eventSave.value = false
        _shoe.value = Shoe()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ShoeDetailViewModel destroyed!")
    }

    fun onCancel() {
        _eventCancel.value = true
    }

    fun onSaveShoe() {
        hideKeyboard()
        Timber.i("ShoeDetailViewModel onSaveShoe")
        _eventSave.value = true
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

    fun onSaveComplete() {
        _eventSave.value = false
    }

}
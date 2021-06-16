package com.udacity.shoestore.screens.instructions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class InstructionsViewModel : ViewModel() {

    private var _eventSkipInstructions = MutableLiveData<Boolean>()
    val eventCompleteInstructions: LiveData<Boolean>
        get() = _eventSkipInstructions

    init {
        Timber.i("InstructionsViewModel created!")
        _eventSkipInstructions.value = false
    }

    fun onSkip() {
        Timber.i("do sign up")
        _eventSkipInstructions.value = true
    }

    fun onSkipComplete() {
        _eventSkipInstructions.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("InstructionsViewModel destroyed!")
    }
}
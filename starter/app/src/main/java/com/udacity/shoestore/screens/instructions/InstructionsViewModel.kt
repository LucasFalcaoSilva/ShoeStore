package com.udacity.shoestore.screens.instructions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Instruction
import timber.log.Timber

class InstructionsViewModel : ViewModel() {

    val instruction = Instruction(
        firstInstruction = "Instruction One",
        secondInstruction = "Instruction Two",
        thirdInstruction = "Instruction Three",
        fourthInstruction = "Instruction Four",
        fifthInstruction = "Instruction Five",
        sixthInstruction = "Instruction Six"
    )

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
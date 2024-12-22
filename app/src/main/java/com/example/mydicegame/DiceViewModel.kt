package com.example.mydicegame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class DiceViewModel : ViewModel() {
    private val _diceValues = MutableLiveData<List<Dice>>()
    val diceValues: LiveData<List<Dice>> get() = _diceValues

    private var rollingJob: Job? = null

    init {
        _diceValues.value = List(5) { Dice((1..6).random()) }
    }

    fun startRolling() {
        if (rollingJob?.isActive == true) return

        rollingJob = CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                _diceValues.postValue(List(5) { Dice((1..6).random()) })
                delay(500)
            }
        }
    }

    fun stopRolling() {
        rollingJob?.cancel()
        rollingJob = null
    }
}

data class Dice(val value: Int)
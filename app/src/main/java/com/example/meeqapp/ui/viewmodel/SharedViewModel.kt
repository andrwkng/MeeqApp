package com.example.meeqapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.meeqapp.data.ThoughtStore
import com.example.meeqapp.ui.checkup.Checkup
import com.example.meeqapp.ui.predictions.Prediction
import com.example.meeqapp.ui.thoughts.Thought
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val  thoughtStore: ThoughtStore
): ViewModel() {
    private var _checkup: MutableState<Checkup?> = mutableStateOf(null)
    val checkup = _checkup.value

    private var _thought: MutableState<Thought?> = mutableStateOf(null)
    val thought = _thought.value

    private var _prediction: MutableState<Prediction?> = mutableStateOf(null)
    val prediction = _prediction.value

    suspend fun saveThought(thought: Thought) {
        _thought.value = thoughtStore.saveThought(thought)
    }

    fun updateThought(thought: Thought) {
        _thought.value = thought
    }

    fun updateCheckup(checkup: Checkup) {
        _checkup.value = checkup
    }

    fun updatePrediction(prediction: Prediction) {
        _prediction.value = prediction
    }
}
package com.example.meeqapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.meeqapp.ui.checkup.Checkup
import com.example.meeqapp.ui.predictions.Prediction
import com.example.meeqapp.ui.thoughts.Thought

class SharedViewModel: ViewModel() {
    var checkup: MutableState<Checkup?> = mutableStateOf(null)
    var thought: MutableState<Thought?> = mutableStateOf(null)
    var prediction: MutableState<Prediction?> = mutableStateOf(null)
}
package com.example.meeqapp.ui.exercises

import androidx.lifecycle.ViewModel
import com.example.meeqapp.data.PredictionStore
import com.example.meeqapp.data.ThoughtStore
import com.example.meeqapp.ui.checkup.Checkup
import com.example.meeqapp.data.CheckupStore
import com.example.meeqapp.ui.predictions.Prediction
import com.example.meeqapp.ui.thoughts.SavedThought
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val  thoughtStore: ThoughtStore,
    private val predictionStore: PredictionStore,
    private val checkupStore: CheckupStore
): ViewModel() {
    suspend fun getOrderedThoughts(): List<SavedThought> {
        return thoughtStore.getOrderedThoughts()
    }

    suspend fun getOrderedCheckups(): List<Checkup> {
        return checkupStore.getOrderedCheckups()
    }

    fun getOrderedPredictions(): List<Prediction> {
        return predictionStore.getOrderedPredictions()
    }
}
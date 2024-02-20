package com.example.meeqapp.ui.predictions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meeqapp.data.PredictionStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PredictionsViewModel @Inject constructor(
    private val predictionStore: PredictionStore
) : ViewModel() {
    fun savePrediction(prediction: Prediction) {
        viewModelScope.launch {
            predictionStore.savePrediction(prediction)
        }
    }

    fun newPrediction(): Prediction {
        return predictionStore.newPrediction()
    }
}
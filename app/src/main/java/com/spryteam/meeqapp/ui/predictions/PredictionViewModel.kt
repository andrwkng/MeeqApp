package com.sprytm.meeqapp.ui.predictions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sprytm.meeqapp.data.PredictionStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PredictionViewModel @Inject constructor(
    private val predictionStore: PredictionStore
) : ViewModel() {

    private val _hasSeenPredictionOnboarding: Boolean = false
    val hasSeenPredictionOnboarding: Boolean
        get() = _hasSeenPredictionOnboarding

    init {
        viewModelScope.launch {
            val _hasSeenPredictionOnboarding: Boolean = predictionStore.hasSeenPredictionOnboarding.first()
        }
    }

    fun savePrediction(prediction: Prediction) {
        viewModelScope.launch {
            predictionStore.savePrediction(prediction)
        }
    }

    fun newPrediction(): Prediction {
        return predictionStore.newPrediction()
    }

    fun setSeenPredictionOnboardingTrue() {
        viewModelScope.launch {
            predictionStore.setSeenPredictionOnboarding(true)
        }
    }

    /*fun hasSeenPredictionOnboarding() {
        predictionStore.setSeenPredictionOnboarding(true)
    }*/

    fun getOrderedPredictions(): List<Prediction> {
        return predictionStore.getOrderedPredictions()
    }

}
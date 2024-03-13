package com.sprytm.meeqapp.ui.exercises

import androidx.lifecycle.ViewModel
import com.sprytm.meeqapp.data.CheckupStore
import com.sprytm.meeqapp.data.PredictionStore
import com.sprytm.meeqapp.data.ThoughtStore
import com.sprytm.meeqapp.ui.checkup.Checkup
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val  thoughtStore: ThoughtStore,
    private val predictionStore: PredictionStore,
    private val checkupStore: CheckupStore
): ViewModel() {


    suspend fun getOrderedCheckups(): List<Checkup> {
        return checkupStore.getOrderedCheckups()
    }


}
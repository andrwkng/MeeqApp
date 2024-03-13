package com.spryteam.meeqapp.ui.exercises

import androidx.lifecycle.ViewModel
import com.spryteam.meeqapp.data.CheckupStore
import com.spryteam.meeqapp.data.ThoughtStore
import com.spryteam.meeqapp.ui.checkup.Checkup
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val  thoughtStore: ThoughtStore,
    private val checkupStore: CheckupStore
): ViewModel() {


    suspend fun getOrderedCheckups(): List<Checkup> {
        return checkupStore.getOrderedCheckups()
    }


}
package com.spryteam.meeqapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.spryteam.meeqapp.data.ExerciseStore
import com.spryteam.meeqapp.ui.checkup.Checkup
import com.spryteam.meeqapp.ui.exercises.ExerciseGroup
import com.spryteam.meeqapp.ui.thoughts.Exercise
import com.spryteam.meeqapp.ui.thoughts.SavedThought
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val exerciseStore: ExerciseStore
) : ViewModel() {
    private val _thought = mutableStateOf<SavedThought?>(null)
    val thought: SavedThought?
        get() = _thought.value

    fun setThought(thought: SavedThought){
        _thought.value = thought
    }

    private val _groups = MutableStateFlow(listOf<ExerciseGroup>())
    val groups = _groups.asStateFlow()
    private val checkups = mutableListOf<Checkup>()

    val thoughts = mutableListOf<SavedThought>()
    fun saveThought(thought: SavedThought) {
        thoughts.add(thought)
    }

    fun updateThought(thought: SavedThought) {
        val index = thoughts.indexOfFirst { it.uuid == thought.uuid }

        // Index out of bounds
        if (index < 0 || index >= thoughts.size) {
            return
        }
        thoughts[index] = thought
    }

    fun getExerciseGroups(): List<ExerciseGroup> {
        return groups.value
    }

    fun loadExercises() {
        val exercises: List<Exercise> = (thoughts + checkups)

        _groups.value =
            exerciseStore.getSortedExerciseGroups(exerciseStore.getSortedExerciseList(exercises))
    }


    // Only shows one in so many days.
    // Useful for giving not just new folks surveys.
    fun passesDayFilter(oneIn: Int): Boolean {
        if (oneIn > 31) {
            throw IllegalArgumentException("oneIn should be lower than 31")
        }

        val diceRoll = LocalDate.now().dayOfMonth % (oneIn - 1)
        return diceRoll == 0
    }

}
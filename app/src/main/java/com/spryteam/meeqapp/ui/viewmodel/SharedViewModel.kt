package com.spryteam.meeqapp.ui.viewmodel

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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val exerciseStore: ExerciseStore
) : ViewModel() {
    private val _groups = MutableStateFlow(listOf<ExerciseGroup>())
    val groups = _groups.asStateFlow()
    val thoughts = mutableListOf<SavedThought>()
    private val checkups = mutableListOf<Checkup>()


    fun saveThought(thought: SavedThought) {
        thoughts.add(thought)
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

fun getFollowUpTime(): String {
    val inAFewHours = LocalDateTime.now().plusHours(2)

    // If we're before 7am or after 9pm, then schedule it for tomorrow.
    if (inAFewHours.hour < 7 || inAFewHours.hour > 21) {
        return inAFewHours.plusHours(12).format(DateTimeFormatter.ISO_DATE_TIME)
    }
    return inAFewHours.format(DateTimeFormatter.ISO_DATE_TIME)
}


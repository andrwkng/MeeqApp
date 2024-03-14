package com.spryteam.meeqapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.spryteam.meeqapp.data.ExerciseStore
import com.spryteam.meeqapp.ui.checkup.Checkup
import com.spryteam.meeqapp.ui.exercises.ExerciseGroup
import com.spryteam.meeqapp.ui.thoughts.Exercise
import com.spryteam.meeqapp.ui.thoughts.SavedThought
import com.spryteam.meeqapp.ui.thoughts.Thought
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

    fun onFinish() {
        //saveThought("New Thought")
        //navigation.navigate(FINISHED_SCREEN)
    }


    fun onCancel() {}

    private fun updateThought(thought: Thought) {

    }

    fun saveThought(thought: SavedThought) {
        //thoughts.value?.exercises?.add(thought as Exercise)
        //thoughtStore.saveThought(thoughts)
        thoughts.add(thought)
    }

    fun setHasBeenSurveyedTrue() {
        /*coroutineScope.launch {
            flagStore.setTrue(Flag.HAS_BEEN_SURVEYED)
        }*/
    }

    fun dismissSurveyPrompt() {
        setHasBeenSurveyedTrue()
    }

    fun navigateToSurveyScreen() {
        setHasBeenSurveyedTrue()
        //navigation.navigate(SURVEY_SCREEN)
    }

    fun navigateToCheckupViewer(checkup: Checkup? = null) {
        //checkup?.let { updateCheckup(it) }
        //navigation.navigate(CHECKUP_SUMMARY_SCREEN)
    }

    suspend fun loadShouldShowSurveyPrompt() {
        val isDayToShow = passesDayFilter(3)
        if (!isDayToShow) {
            //shouldPromptSurvey = false

            return
        }

        /*val passes = passesFeatureFlag(2, userPreferenceStore)
        if (!passes) {
            shouldPromptSurvey = false

            return
        }*/

        val hasBeenSurveyed = false
        //userPreferenceStore.hasBeenSurveyed.collect { hasBeenSurveyed = it }
        if (hasBeenSurveyed.not()) {
            //shouldPromptSurvey = false

            return
        }

        //shouldPromptSurvey = true
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


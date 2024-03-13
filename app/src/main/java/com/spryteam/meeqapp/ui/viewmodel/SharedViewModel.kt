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
    //class SharedViewModel : ViewModel() {
    //val groups = mutableStateOf(listOf<ExerciseGroup>())
    private val _groups = MutableStateFlow(listOf<ExerciseGroup>())
    val groups = _groups.asStateFlow()

    //val thoughts = mutableStateOf<ExerciseGroup?>(null)
    val thoughts = mutableListOf<SavedThought>()

    //private val checkups = mutableStateOf<ExerciseGroup?>(null)
    private val checkups = mutableListOf<Checkup>()


    /*fun updateThought() {
        //_thought.value = thought
        _thought.value?.let {
            //_thought.value as SavedThought
            updateAutomaticThought()
        }
    }*/













    fun onFinish() {
        //saveThought("New Thought")
        //navigation.navigate(FINISHED_SCREEN)
    }





    fun onCancel() {}

    private fun updateThought(thought: Thought) {

    }




    /*fun saveThought() {
        viewModelScope.launch {
            val nThought = thought.value
            if (nThought != null) {
                viewModel.saveThought(nThought)
            }
        }
    }*/

    fun saveThought(thought: SavedThought) {
        //thoughts.value?.exercises?.add(thought as Exercise)
        //thoughtStore.saveThought(thoughts)
        Log.i("saveThought:", thought.alternativeThought)
        thoughts.add(thought)
        Log.i("thoughts size:", thoughts.size.toString())
    }

    fun setHasBeenSurveyedTrue() {
        /*coroutineScope.launch {
            flagStore.setTrue(Flag.HAS_BEEN_SURVEYED)
        }*/
    }

    fun dismissSurveyPrompt() {
        setHasBeenSurveyedTrue()
        //shouldPromptSurvey = false
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

        Log.i("thoughts size:", thoughts.size.toString())

        Log.i("loadExercises size:", exercises.size.toString())

        _groups.value =
            exerciseStore.getSortedExerciseGroups(exerciseStore.getSortedExerciseList(exercises))

        Log.i("groups size:", groups.value.size.toString())

    }





    /*
    @Composable
    fun HomeScreen(navController: NavController) {
        val userObj = User()
        Button(
            onClick = {
                val gson: Gson = GsonBuilder().create()
                val userJson = gson.toJson(userObj)
     Replacing {user} with userJson

                navController.navigate(
                    "detail/${user}" //Just modify your route accordingly
                        .replace(
                            oldValue = "{user}",
                            newValue = userJson
                        )
                )
            }
        ) {
            Text(text = "Navigate to Detail with userJson")
        }
    }
    */

    // Only shows one in so many days.
// Useful for giving not just new folks surveys.
    fun passesDayFilter(oneIn: Int): Boolean {
        if (oneIn > 31) {
            throw IllegalArgumentException("oneIn should be lower than 31")
        }

        val diceRoll = LocalDate.now().dayOfMonth % (oneIn - 1)
        return diceRoll == 0
    }

    /**
     * Checks if this user passes a feature flag, records
     * a Segment identify that marks them as on/off.
     * @param oneIn ex: 10 for 1 in 10 chance; 5 for 1 in 5 chance.
     */
    /*suspend fun passesFeatureFlag(
        oneIn: Int,
        //userPrefViewModel: UserPreferenceStore
    ): Boolean {
        var id: String? = null
        //runBlocking { userPrefViewModel.getUserID().collect { id = it } }
        val diceRoll = id?.hashCode()?.rem((oneIn - 1))

        return diceRoll == 0
    }*/




}

fun getFollowUpTime(): String {
    val inAFewHours = LocalDateTime.now().plusHours(2)

    // If we're before 7am or after 9pm, then schedule it for tomorrow.
    if (inAFewHours.hour < 7 || inAFewHours.hour > 21) {
        return inAFewHours.plusHours(12).format(DateTimeFormatter.ISO_DATE_TIME)
    }
    return inAFewHours.format(DateTimeFormatter.ISO_DATE_TIME)
}
/*
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val  thoughtStore: ThoughtStore
): ViewModel() {
    private var _checkup: MutableState<Checkup?> = mutableStateOf(null)
    val checkup = _checkup.value

    private var _thought: MutableState<Thought?> = mutableStateOf(null)
    val thought = _thought.value


    suspend fun saveThought(thought: Thought) {
        _thought.value = thoughtStore.saveThought(thought)
    }

    fun updateThought(thought: Thought) {
        _thought.value = thought
    }

    fun updateCheckup(checkup: Checkup) {
        _checkup.value = checkup
    }

}*/

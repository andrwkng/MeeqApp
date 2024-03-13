package com.sprytm.meeqapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sprytm.meeqapp.data.ExerciseStore
import com.sprytm.meeqapp.ui.checkup.Checkup
import com.sprytm.meeqapp.ui.distortions.distortions
import com.sprytm.meeqapp.ui.exercises.ExerciseGroup
import com.sprytm.meeqapp.ui.predictions.Prediction
import com.sprytm.meeqapp.ui.thoughts.Exercise
import com.sprytm.meeqapp.ui.thoughts.ImmediateCheckup
import com.sprytm.meeqapp.ui.thoughts.SavedThought
import com.sprytm.meeqapp.ui.thoughts.Thought
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    val groups = mutableStateOf(listOf<ExerciseGroup>())

    //val thoughts = mutableStateOf<ExerciseGroup?>(null)
    val thoughts = mutableListOf<SavedThought>()

    //private val checkups = mutableStateOf<ExerciseGroup?>(null)
    private val checkups = mutableListOf<Checkup>()

    //val predictions = mutableStateOf<ExerciseGroup?>(null)
    val predictions = mutableListOf<Prediction>()

    private val _createdAt = mutableStateOf(LocalDate.now())
    val createdAt: LocalDate
        get() = _createdAt.value

    /*fun updateThought() {
        //_thought.value = thought
        _thought.value?.let {
            //_thought.value as SavedThought
            updateAutomaticThought()
        }
    }*/

    // FollowUp
    private val _followUpCompleted = mutableStateOf<Boolean?>(null)

    private val _followUpNote = mutableStateOf<String?>(null)
    val followUpNote: String?
        get() = _followUpNote.value

    private val _followUpDate = mutableStateOf<String?>(null)

    val navigateToFinished = mutableStateOf({})
    fun onContinue() {
        navigateToFinished.value()
    }

    fun onSetCheckup() {
        _followUpDate.value = getFollowUpTime()
        //saveThought()
        onContinue()
    }

    // Feeling
    private val _checkup = mutableStateOf<ImmediateCheckup?>(null)

    fun onFeltWorse(navigate: () -> Unit) {
        saveCheckup(ImmediateCheckup.WORSE)
        navigate()
    }

    private fun saveCheckup(feeling: ImmediateCheckup) {
        _checkup.value = feeling
        //saveThought()
    }

    fun onFeltTheSame(navigate: () -> Unit) {
        saveCheckup(ImmediateCheckup.WORSE)
        navigate()
    }

    fun onFeltBetter(navigate: () -> Unit) {
        saveCheckup(ImmediateCheckup.BETTER)
        navigate()
    }

    // Distortions
    private val _distortions = MutableStateFlow(distortions)
    val distortionList = _distortions.asStateFlow()

    fun onPressSlug(selected: Any) {
        val list = _distortions.value
        list.let {
            val index = list.indexOfFirst { it.slug == selected }

            if (index != -1) {
                list[index].selected = !list[index].selected
                //distortionList = cognitiveDistortions.toList()
                _distortions.value = list
            }
        }
    }



    // Challenge
    private val _challenge = MutableStateFlow("")
    val challenge: StateFlow<String> = _challenge.asStateFlow()

    fun onChallengeChange(value: String) {
        _challenge.value = value
        _isNextDisabled.value = false
    }

    // Automatic Thought
    private val _isNextDisabled = mutableStateOf(true)
    val isNextDisabled: Boolean
        get() = _isNextDisabled.value

    private val _autoThought = MutableStateFlow("")
    val automaticThought: StateFlow<String> = _autoThought.asStateFlow()

    private val _isEditing = mutableStateOf(false)
    val isEditing: Boolean
        get() = _isEditing.value

    fun onFinish() {
        //saveThought("New Thought")
        //navigation.navigate(FINISHED_SCREEN)
    }

    fun onNext(navigate: () -> Unit) {
        //saveThought("New Thought")
        //navigation.navigate(DISTORTION_SCREEN)
        navigate()
    }

    fun onChange(value: String) {
        _autoThought.value = value
        _isNextDisabled.value = false
        //thought.value?.automaticThought = label
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
        thoughts.add(thought)
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
        val exercises: List<Exercise> = (thoughts + checkups + predictions)

        groups.value =
            exerciseStore.getSortedExerciseGroups(exerciseStore.getSortedExerciseList(exercises))
    }

    fun navigateToPredictionViewer(prediction: Prediction? = null) {
        /*prediction?.let {
            viewModel.updatePrediction(it)
            if (getPredictionState(it) === PredictionState.READY) {
                navigation.navigate(PREDICTION_FOLLOW_UP_SCREEN)
                return
            }
        }
        navigation.navigate(PREDICTION_SUMMARY_SCREEN)*/
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
    suspend fun passesFeatureFlag(
        oneIn: Int,
        //userPrefViewModel: UserPreferenceStore
    ): Boolean {
        var id: String? = null
        //runBlocking { userPrefViewModel.getUserID().collect { id = it } }
        val diceRoll = id?.hashCode()?.rem((oneIn - 1))

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
/*
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val  thoughtStore: ThoughtStore
): ViewModel() {
    private var _checkup: MutableState<Checkup?> = mutableStateOf(null)
    val checkup = _checkup.value

    private var _thought: MutableState<Thought?> = mutableStateOf(null)
    val thought = _thought.value

    private var _prediction: MutableState<Prediction?> = mutableStateOf(null)
    val prediction = _prediction.value

    suspend fun saveThought(thought: Thought) {
        _thought.value = thoughtStore.saveThought(thought)
    }

    fun updateThought(thought: Thought) {
        _thought.value = thought
    }

    fun updateCheckup(checkup: Checkup) {
        _checkup.value = checkup
    }

    fun updatePrediction(prediction: Prediction) {
        _prediction.value = prediction
    }
}*/

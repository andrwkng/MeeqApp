package com.example.meeqapp.ui

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meeqapp.data.Flag
import com.example.meeqapp.data.FlagStore
import com.example.meeqapp.data.HistoryButtonLabelSetting
import com.example.meeqapp.ui.checkup.Checkup
import com.example.meeqapp.ui.exercises.ExerciseGroup
import com.example.meeqapp.ui.exercises.ExerciseList
import com.example.meeqapp.ui.exercises.getSortedExerciseGroups
import com.example.meeqapp.ui.predictions.PREDICTION_FOLLOW_UP_SCREEN
import com.example.meeqapp.ui.predictions.PREDICTION_SUMMARY_SCREEN
import com.example.meeqapp.ui.predictions.Prediction
import com.example.meeqapp.ui.predictions.PredictionState
import com.example.meeqapp.ui.predictions.getPredictionState
import com.example.meeqapp.ui.survey.SurveyPrompt
import com.example.meeqapp.ui.thoughts.FollowUpState
import com.example.meeqapp.ui.thoughts.SavedThought
import com.example.meeqapp.ui.thoughts.followUpState
import com.example.meeqapp.ui.viewmodel.SharedViewModel
import com.example.meeqapp.ui.viewmodel.UserPreferenceStore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.temporal.ChronoUnit


@Composable
fun Feed(
    navigation: NavController,
    viewModel: SharedViewModel = viewModel(),
//    userPrefViewModel: UserPreferenceViewModel = viewModel()
) {
    val context = LocalContext.current
    val flagStore = FlagStore(context)

    val userPreferenceStore = UserPreferenceStore(flagStore)

    var groups: List<ExerciseGroup> = emptyList()

    var shouldFadeIn by remember { mutableStateOf(false) }
    var shouldPromptCheckup by remember { mutableStateOf(false) }
    var shouldPromptSurvey by remember { mutableStateOf(false) }

    var areExercisesLoaded: Boolean = false
    var isVisible: Boolean = shouldFadeIn

    val coroutineScope = rememberCoroutineScope()

    fun setHasBeenSurveyedTrue() {
        coroutineScope.launch {
            flagStore.setTrue(Flag.HAS_BEEN_SURVEYED)
        }
    }

    fun dismissSurveyPrompt() {
        setHasBeenSurveyedTrue()
        shouldPromptSurvey = false
    }

    fun navigateToSurveyScreen() {
        setHasBeenSurveyedTrue()
        navigation.navigate(SURVEY_SCREEN)
    }

    fun navigateToCheckupViewer(checkup: Checkup? = null) {
        viewModel.checkup.value = checkup
        navigation.navigate(CHECKUP_SUMMARY_SCREEN)
    }

    suspend fun loadShouldShowSurveyPrompt() {
        val isDayToShow = passesDayFilter(3)
        if (!isDayToShow) {
            shouldPromptSurvey = false

            return
        }

        val passes = passesFeatureFlag(2, userPreferenceStore)
        if (!passes) {
            shouldPromptSurvey = false

            return
        }

        var hasBeenSurveyed = false
        userPreferenceStore.hasBeenSurveyed.collect { hasBeenSurveyed = it }
        if (hasBeenSurveyed.not()) {
            shouldPromptSurvey = false

            return
        }

        shouldPromptSurvey = true
    }

    fun loadExercises() {
        GlobalScope.launch {
            try {
                groups = getSortedExerciseGroups(context)
                Log.i("loadExercises", groups.size.toString())
            } catch (e: Exception) {
                Log.e("loadExercises", "Error retrieving exercise groups", e)
            } finally {
                areExercisesLoaded = true
            }
        }
    }

    LaunchedEffect(true) {
        Log.i("LaunchedEffect","started")
        loadExercises()
        shouldPromptCheckup = loadShouldPromptCheckup(userPreferenceStore)
        loadShouldShowSurveyPrompt()

        // Simulating setTimeout with delay
        delay(150)
        shouldFadeIn = true
    }



    fun navigateToPredictionViewer(prediction: Prediction? = null) {
        viewModel.prediction.value = prediction
        if (getPredictionState(prediction) === PredictionState.READY) {
            navigation.navigate(PREDICTION_FOLLOW_UP_SCREEN)
            return
        }
        navigation.navigate(PREDICTION_SUMMARY_SCREEN)
    }

    fun navigateToViewerWithThought(
        thought: SavedThought? = null,
        //navigation: NavController,
        //viewModel: SharedViewModel = viewModel()
    ) {
        //viewModel.thought.value = thought
        // Follow-ups
        when (followUpState(thought)) {
            FollowUpState.READY -> {
                navigation.navigate(FOLLOW_UP_NOTE_SCREEN)
                return
            }
            // Regular finished screen
            else -> {
                navigation.navigate(FINISHED_SCREEN)
            }
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        ExerciseList(
            groups = groups,
            historyButtonLabel = HistoryButtonLabelSetting.ALTERNATIVE_THOUGHT,
            navigateToThoughtViewer = { navigateToViewerWithThought() },
            navigateToCheckupViewer = { navigateToCheckupViewer() },
            navigateToPredictionViewer = { navigateToPredictionViewer() }
        )

        if (shouldPromptCheckup) {
            CheckupPrompt(
                onPress = {
                    navigation.navigate(CHECKUP_SCREEN)
                }
            )
        }

        if (shouldPromptSurvey && !shouldPromptCheckup) {
            SurveyPrompt(
                onPressYes = { navigateToSurveyScreen() },
                onPressNo = { dismissSurveyPrompt() }
            )
        }
    }
}

suspend fun loadShouldPromptCheckup(store: UserPreferenceStore): Boolean {
    val date = getNextCheckupDate(store)

    return LocalDate.parse(date.toString()).isAfter(LocalDate.now())
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
    userPrefViewModel: UserPreferenceStore
): Boolean {
    var id: String? = null
    runBlocking { userPrefViewModel.getUserID().collect { id = it } }
    val diceRoll = id?.hashCode()?.rem((oneIn - 1))

    return diceRoll == 0
}

suspend fun getNextCheckupDate(userPrefViewModel: UserPreferenceStore): LocalDate {
    try {
        var date: String? = null
        userPrefViewModel.getCheckUpScheduleKey.collect { date = it }

        if (date.isNullOrEmpty()) {
            // If we haven't had a checkup yet, schedule it for an hour ago
            return LocalDate.now().minus(1, ChronoUnit.HOURS)
            /*return Calendar.getInstance().apply {
                    add(Calendar.HOUR, -1)
                }.time*/

        }

        return LocalDate.parse(date)
    } catch (err: Exception) {
        // Next week
        return LocalDate.now().plus(1, ChronoUnit.WEEKS)
    }
}

@Preview
@Composable
fun FeedPreview() {
    Feed(rememberNavController())
}

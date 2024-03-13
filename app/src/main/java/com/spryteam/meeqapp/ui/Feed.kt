package com.spryteam.meeqapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.spryteam.meeqapp.data.HistoryButtonLabelSetting
import com.spryteam.meeqapp.ui.components.CheckupPrompt
import com.spryteam.meeqapp.ui.exercises.ExerciseGroup
import com.spryteam.meeqapp.ui.exercises.ExerciseList
import com.spryteam.meeqapp.ui.thoughts.FollowUpState


@Composable
fun Feed(
    groups: List<ExerciseGroup>,
    navigateToThoughtViewer: () -> Unit,
    navigateToCheckup: () -> Unit,
    navigateToCheckupViewer: () -> Unit,
    shouldFadeIn: Boolean = false,
    shouldPromptCheckup: Boolean = false,
    onLoad: () -> Unit,
    followUpState: () -> FollowUpState,
    //viewModel: SharedViewModel,
    //exerciseViewModel: ExerciseViewModel = hiltViewModel()
) {
//    val context = LocalContext.current
//    val flagStore = FlagStore(context)
//
//    val userPreferenceStore = UserPreferenceStore(flagStore)
//
//

//
//    var areExercisesLoaded: Boolean = false
    //val isVisible: Boolean = shouldFadeIn
    val isVisible = true
//
//    val coroutineScope = rememberCoroutineScope()



    LaunchedEffect(true) {
        //Log.i("LaunchedEffect","started")
        onLoad()
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        ExerciseList(
            groups = groups,
            historyButtonLabel = HistoryButtonLabelSetting.ALTERNATIVE_THOUGHT,
            navigateToThoughtViewer = { navigateToThoughtViewer() },
            navigateToCheckupViewer = { navigateToCheckupViewer() },
            followUpState = followUpState,
        )

        if (shouldPromptCheckup) {
            CheckupPrompt(
                onPress = {
                    //navigation.navigate(CHECKUP_SCREEN)
                    navigateToCheckup()
                }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun FeedPreview() {
    /*Feed(
        groups = ,
        navigateToThoughtViewer = { TODO },
        navigateToCheckup = { TODO },
        navigateToSurvey = { TODO },
        navigateToCheckupViewer = { TODO },
        shouldFadeIn = true,
        shouldPromptCheckup = false,
        dismissSurveyPrompt = { }
    )*/
}

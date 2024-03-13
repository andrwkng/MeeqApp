package com.spryteam.meeqapp.ui.thoughts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spryteam.meeqapp.ui.Feed
import com.spryteam.meeqapp.ui.components.ExerciseButton
import com.spryteam.meeqapp.ui.exercises.ExerciseGroup
import com.spryteam.meeqapp.ui.predictions.PredictionViewModel
import com.spryteam.meeqapp.ui.viewmodel.SharedViewModel

@Composable
fun ThoughtScreen(
    screeningData: FormScreeningData,
    isNextDisabled: Boolean,
    onClosePressed: () -> Unit,
    onPreviousPressed: () -> Unit,
    onNextPressed: () -> Unit,
    onFinishPressed: () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    Surface {
        Scaffold(
            content = content,
        )
    }
}

@Composable
fun MainRoute(
    onNavigateToPredictionOnboarding: () -> Unit,
    onNavigateToAssumption: () -> Unit,
    onNavigateToAutoThought: () -> Unit,
    onNavigateToThoughtViewer: () -> Unit,
    onNavigateToCheckup: () -> Unit,
    onNavigateToCheckupViewer: () -> Unit,
    onNavigateToPredictionViewer: () -> Unit,
    sharedViewModel: SharedViewModel,
    thoughtViewModel: ThoughtViewModel = hiltViewModel(),
    predictionViewModel: PredictionViewModel = hiltViewModel()
) {
    sharedViewModel.loadExercises()
    val groups: List<ExerciseGroup> by sharedViewModel.groups.collectAsState(emptyList())

    HomeScreen(
        groups = groups,
        onNewAutoThought = { onNavigateToAutoThought() },
        onNewPrediction = {
            if (!predictionViewModel.hasSeenPredictionOnboarding) {
                predictionViewModel.setSeenPredictionOnboardingTrue()
                onNavigateToPredictionOnboarding()
            } else {
                onNavigateToAssumption()
            }
        },
        navigateToThoughtViewer = onNavigateToThoughtViewer,
        navigateToCheckup = onNavigateToCheckup,
        navigateToCheckupViewer = onNavigateToCheckupViewer,
        navigateToPredictionViewer = onNavigateToPredictionViewer,
        shouldFadeIn = thoughtViewModel.shouldFadeIn.value,
        shouldPromptCheckup = thoughtViewModel.shouldPromptCheckup.value,
        followUpState = { thoughtViewModel.followUpState() },
        onLoad = { sharedViewModel.loadExercises() }
    )
}
@Composable
fun HomeScreen(
    groups: List<ExerciseGroup>,
    onNewAutoThought: () -> Unit,
    onNewPrediction: () -> Unit,
    modifier: Modifier = Modifier,
    //Feed state
    navigateToThoughtViewer: () -> Unit,
    navigateToCheckup: () -> Unit,
    navigateToCheckupViewer: () -> Unit,
    navigateToPredictionViewer: () -> Unit,
    shouldFadeIn: Boolean,
    shouldPromptCheckup: Boolean,
    followUpState: () -> FollowUpState,
    onLoad: () -> Unit
) {
    //val existingUser = viewModel.isExistingUser.collectAsState()

    /*LaunchedEffect(Unit) { // the key define when the block is relaunched
        if(!existingUser.value){
                viewModel.setIsExistingUser()
        }
    }*/

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ExerciseButtons(
            onNewPredictionPressed = onNewPrediction,
            onNewAutoThoughtPressed = onNewAutoThought
        )
        Feed(
            groups = groups,
            navigateToThoughtViewer = navigateToThoughtViewer,
            navigateToCheckup = navigateToCheckup,
            navigateToCheckupViewer = navigateToCheckupViewer,
            navigateToPredictionViewer = navigateToPredictionViewer,
            shouldFadeIn = shouldFadeIn,
            shouldPromptCheckup = shouldPromptCheckup,
            followUpState = followUpState,
            onLoad = onLoad
        )
    }
}

@Composable
fun ExerciseButtons(
    onNewPredictionPressed: () -> Unit,
    onNewAutoThoughtPressed: () -> Unit
) {
//    val context = LocalContext.current
//    val flagStore = FlagStore(context)
//    val hasSeenPredictionOnboardingFlow: Flow<Boolean> =
//        flagStore.getFlag(Flag.HAS_SEEN_PREDICTION_ONBOARDING)
//    val hasSeenPredictionOnboarding by hasSeenPredictionOnboardingFlow.collectAsState(initial = false)
//    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 24.dp)
            .padding(end = 12.dp, start = 12.dp)
    ) {
        /*ExerciseButton(
            title = "New Prediction",
            hint = "Manage anxiety around upcoming events or tasks.",
            icon = Icons.Default.Star,
            onClick = onNewPredictionPressed,
        )*/

        ExerciseButton(
            title = "New Automatic Thought",
            hint = "Challenge your in-the-moment automatic negative thoughts.",
            onClick = onNewAutoThoughtPressed
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ThoughtScreenPreview() {
    /*ThoughtScreen(
        screeningData = ,
        isNextDisabled = ,
        onClosePressed = { *//*TODO*//* },
        onPreviousPressed = { *//*TODO*//* },
        onNextPressed = { *//*TODO*//* },
        onFinishPressed = { *//*TODO*//* }) {

    }*/
}

@Preview(showBackground = true)
@Composable
fun ExerciseButtonsPreview() {
    ExerciseButtons(onNewPredictionPressed = { /*TODO*/ }) {
        
    }
}
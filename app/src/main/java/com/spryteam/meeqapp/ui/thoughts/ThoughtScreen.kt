package com.sprytm.meeqapp.ui.thoughts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sprytm.meeqapp.ui.ExerciseButton
import com.sprytm.meeqapp.ui.exercises.ExerciseGroup
import com.sprytm.meeqapp.ui.predictions.PredictionViewModel
import com.sprytm.meeqapp.ui.theme.Theme
import com.sprytm.meeqapp.ui.viewmodel.SharedViewModel

@Composable
fun ThoughtRoute(
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

    ThoughtScreen(
        groups = sharedViewModel.groups.value,
        onNewAutoThought = onNavigateToAutoThought,
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
        onLoad = { sharedViewModel.loadExercises() }
    )
}
@Composable
fun ThoughtScreen(
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
        /*Feed(
            groups = groups,
            navigateToThoughtViewer = navigateToThoughtViewer,
            navigateToCheckup = navigateToCheckup,
            navigateToCheckupViewer = navigateToCheckupViewer,
            navigateToPredictionViewer = navigateToPredictionViewer,
            shouldFadeIn = shouldFadeIn,
            shouldPromptCheckup = shouldPromptCheckup,
            onLoad = onLoad
        )*/
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
            .padding(end = 12.dp)
            .background(color = Theme.colorOffwhite)
            .border(1.dp, Theme.colorLightGray)
    ) {
        ExerciseButton(
            title = "New Prediction",
            hint = "Manage anxiety around upcoming events or tasks.",
            icon = Icons.Default.Star,
            onClick = onNewPredictionPressed,
        )

        ExerciseButton(
            title = "New Automatic Thought",
            hint = "Challenge your in-the-moment automatic negative thoughts.",
            icon = Icons.Default.Email,
            onClick = onNewAutoThoughtPressed
        )
    }
}

@Preview
@Composable
fun ThoughtScreenPreview() {
    //ThoughtScreen(rememberNavController())
}

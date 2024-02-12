package com.example.meeqapp.ui.thoughts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.meeqapp.data.Flag
import com.example.meeqapp.data.FlagStore
import com.example.meeqapp.ui.AUTOMATIC_THOUGHT_SCREEN
import com.example.meeqapp.ui.ExerciseButton
import com.example.meeqapp.ui.predictions.ASSUMPTION_SCREEN
import com.example.meeqapp.ui.predictions.PREDICTION_ONBOARDING_SCREEN
import com.example.meeqapp.ui.pulse.Pulse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun ThoughtScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val flagStore = FlagStore(context)
    /*val userPreferenceViewModel: UserPreferenceViewModel = viewModel(
        factory = UserPreferenceViewModel.Factory(FlagStore(LocalContext.current))
    )*/

    val hasSeenPredictionOnboardingFlow: Flow<Boolean> =
        flagStore.getFlag(Flag.HAS_SEEN_PREDICTION_ONBOARDING)

    suspend fun setSeenPredictionOnboarding(bool: Boolean) {
        flagStore.setFlag(Flag.HAS_SEEN_PREDICTION_ONBOARDING, bool)
    }

    var showProgression by remember { mutableStateOf(false) }
    val hasSeenPredictionOnboarding by hasSeenPredictionOnboardingFlow.collectAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(LocalContext.current) {
        // Replace with your logic for checking the feature flag and updating showProgression
        //showProgression = passesFeatureFlag("awareness-1", 3)
        onDispose { /* cleanup logic */ }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 12.dp, bottom = 24.dp)
    ) {
        if (showProgression) {
            Pulse(navController)
        }

        ExerciseButton(
            title = "New Prediction",
            hint = "Manage anxiety around upcoming events or tasks.",
            icon = Icons.Default.Star,
            onClick = {
                if (!hasSeenPredictionOnboarding) {
                    navController.navigate(PREDICTION_ONBOARDING_SCREEN)

                    //set HAS_SEEN_PREDICTION_ONBOARDING flag to true
                    coroutineScope.launch {
                        setSeenPredictionOnboarding(true)
                    }
                } else {
                    navController.navigate(ASSUMPTION_SCREEN)
                }
            }
        )

        ExerciseButton(
            title = "New Automatic Thought",
            hint = "Challenge your in-the-moment automatic negative thoughts.",
            icon = Icons.Default.Email,
            onClick = { navController.navigate(AUTOMATIC_THOUGHT_SCREEN) }
        )
    }
}

@Preview
@Composable
fun ThoughtScreenPreview() {
    ThoughtScreen(rememberNavController())
}

package com.example.meeqapp.ui.thoughts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.meeqapp.data.Flag
import com.example.meeqapp.data.FlagStore
import com.example.meeqapp.ui.AUTOMATIC_THOUGHT_SCREEN
import com.example.meeqapp.ui.ExerciseButton
import com.example.meeqapp.ui.Feed
import com.example.meeqapp.ui.predictions.ASSUMPTION_SCREEN
import com.example.meeqapp.ui.predictions.PREDICTION_ONBOARDING_SCREEN
import com.example.meeqapp.ui.theme.Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun ThoughtScreen(
    navigation: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ThoughtViewModel = hiltViewModel(),
) {
    val existingUser = viewModel.isExistingUser.collectAsState()

    LaunchedEffect(Unit) { // the key define when the block is relaunched
        if(!existingUser.value){
                viewModel.setIsExistingUser()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ExerciseButtons(navigation,viewModel)
        Feed(navigation, hiltViewModel())
    }
}

@Composable
fun ExerciseButtons(
    navigation: NavHostController,
    viewModel: ThoughtViewModel
) {
    val context = LocalContext.current
    val flagStore = FlagStore(context)
    val hasSeenPredictionOnboardingFlow: Flow<Boolean> =
        flagStore.getFlag(Flag.HAS_SEEN_PREDICTION_ONBOARDING)
    val hasSeenPredictionOnboarding by hasSeenPredictionOnboardingFlow.collectAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

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
            onClick = {
                if (!hasSeenPredictionOnboarding) {
                    navigation.navigate(PREDICTION_ONBOARDING_SCREEN)

                    //set HAS_SEEN_PREDICTION_ONBOARDING flag to true
                    coroutineScope.launch {
                        viewModel.hasSeenPredictionOnboarding()
                    }
                } else {
                    navigation.navigate(ASSUMPTION_SCREEN)
                }
            }
        )

        ExerciseButton(
            title = "New Automatic Thought",
            hint = "Challenge your in-the-moment automatic negative thoughts.",
            icon = Icons.Default.Email,
            onClick = { navigation.navigate(AUTOMATIC_THOUGHT_SCREEN) }
        )
    }
}

@Preview
@Composable
fun ThoughtScreenPreview() {
    ThoughtScreen(rememberNavController(), hiltViewModel())
}

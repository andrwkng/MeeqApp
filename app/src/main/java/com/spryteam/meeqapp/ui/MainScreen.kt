package com.spryteam.meeqapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.spryteam.meeqapp.ui.exercises.ExerciseGroup
import com.spryteam.meeqapp.ui.thoughts.FollowUpState
import com.spryteam.meeqapp.ui.thoughts.HomeScreen
import com.spryteam.meeqapp.ui.thoughts.ThoughtViewModel
import com.spryteam.meeqapp.ui.viewmodel.SharedViewModel

@Composable
fun MainRoute(
    onNavigateToAutoThought: () -> Unit,
    onNavigateToThought: () -> Unit,
    onNavigateToCheckup: () -> Unit,
    onNavigateToCheckupViewer: () -> Unit,
    onNavigateToFollowUpNote: () -> Unit,
    onNavigateToFinished: () -> Unit,
    sharedViewModel: SharedViewModel,
    thoughtViewModel: ThoughtViewModel = hiltViewModel(),
) {
    sharedViewModel.loadExercises()
    val groups: List<ExerciseGroup> by sharedViewModel.groups.collectAsState(emptyList())
    thoughtViewModel.navigateToThought = onNavigateToThought

    HomeScreen(
        groups = groups,
        onNewAutoThought = { onNavigateToAutoThought() },
        setThought = sharedViewModel::setThought,
        navigateToThoughtViewer = {
            when (thoughtViewModel.followUpState()) {
                FollowUpState.READY -> {
                    onNavigateToFollowUpNote()
                }
                // Regular finished screen
                else -> {
                    onNavigateToFinished()
                }
            }
        },
        navigateToCheckup = onNavigateToCheckup,
        navigateToCheckupViewer = onNavigateToCheckupViewer,
        shouldFadeIn = thoughtViewModel.shouldFadeIn.value,
        shouldPromptCheckup = thoughtViewModel.shouldPromptCheckup.value,
        followUpState = { thoughtViewModel.followUpState() },
        onLoad = { sharedViewModel.loadExercises() }
    )
}
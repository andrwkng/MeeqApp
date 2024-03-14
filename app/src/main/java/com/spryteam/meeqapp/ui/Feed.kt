package com.spryteam.meeqapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
) {
    val isVisible = true

    LaunchedEffect(true) {
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
                    navigateToCheckup()
                }
            )
        }

    }
}
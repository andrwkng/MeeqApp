package com.example.meeqapp.ui.followup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meeqapp.ui.components.ActionButton
import com.example.meeqapp.ui.components.GhostButton
import com.example.meeqapp.ui.components.HintHeader
import com.example.meeqapp.ui.components.MediumHeader
import com.example.meeqapp.ui.thoughts.Thought

val POSITIVE_HEADER = "Great! We'll write that down."
val NEUTRAL_OR_NEGATIVE_HEADER = "Here's your next steps."
val POSITIVE_HINT = "Would you still like to review your thought? You can also can finish now and go about your day.";
val NEUTRAL_OR_NEGATIVE_HINT = "If you're dealing with something new or want to walk through the process again, record a new thought. Otherwise, you should review what you wrote before; is it accurate? ";


@Composable
fun FollowUpFeelingReviewScreen(navController: NavController) {
    var thought by remember { mutableStateOf<Thought?>(null) }
    var isReady by remember { mutableStateOf(false) }

    /*DisposableEffect(navController) {
        val callback = NavController.OnDestinationChangedListener { _, _, _ ->
            thought = navController.previousBackStackEntry?.arguments?.getParcelable("thought")
            isReady = true
        }
        navController.addOnDestinationChangedListener(callback)

        onDispose {
            navController.removeOnDestinationChangedListener(callback)
        }
    }*/

    if (!isReady) {
        // Loading state
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        // Content
        Box(
            modifier = Modifier
                .fillMaxSize()
                //.padding(top = LocalDensity.current.run { 24.dp.toPx() })
                .background(Color.Gray)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                MediumHeader(
                    text = if (thought?.followUpCheckup == "better") {
                        POSITIVE_HEADER
                    } else {
                        NEUTRAL_OR_NEGATIVE_HEADER
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                HintHeader(
                    text = if (thought?.followUpCheckup == "better") {
                        POSITIVE_HINT
                    } else {
                        NEUTRAL_OR_NEGATIVE_HINT
                    },
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                if (thought?.followUpCheckup == "better") {
                    ActionButton(
                        title = "Finish",
                        style = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                        onClick = { onFinish(thought) }
                    )
                    GhostButton(
                        title = "Review Thought",
                        onClick = { onReviewThought(thought) }
                    )
                } else {
                    ActionButton(
                        title = "Finish",
                        style = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                        onClick = { onNewThought(thought) }
                    )
                    GhostButton(
                        title = "Review Thought",
                        onClick = { onReviewThought(thought) }
                    )
                }
            }
        }
    }
}

fun onFinish(thought: Thought?) {
/*    // Implement your logic for completing the follow-up
    stats.userCompletedFollowUp()

    // Mark this follow-up as completed
    thought?.followUpCompleted = true
    // Call the appropriate method to save the thought
    saveThought(thought)
    // Navigate to the THOUGHT_SCREEN
    navController.navigate(THOUGHT_SCREEN)*/
}

fun onReviewThought(thought: Thought?) {
/*    // Implement your logic for reviewing the thought
    stats.userReviewedThoughtOnFollowUp()
    // Call the appropriate method to navigate to the FINISHED_SCREEN with the thought data
    navController.navigate(FINISHED_SCREEN, navOptions { arguments = bundleOf("thought" to thought) })*/
}

fun onNewThought(thought: Thought?) {
/*    // Implement your logic for recording a new thought
    stats.userRecordedNewThoughtOnFollowUp()
    // Call the appropriate method to complete the follow-up
    onFinish(thought)
    // Navigate to the THOUGHT_SCREEN with a new thought and popup flag
    navController.navigate(THOUGHT_SCREEN, navOptions { arguments = bundleOf("thought" to newThought(), "isRequestingPopUp" to true) })*/
}
/*
@Composable
@Preview(showBackground = true)
fun FollowUpFeelingReviewScreenPreview() {
    FeelsJournalTheme {
        // Mock a NavController for the preview
        val navController = rememberNavController()
        FollowUpFeelingReviewScreen(navController = navController)
    }
}
*/

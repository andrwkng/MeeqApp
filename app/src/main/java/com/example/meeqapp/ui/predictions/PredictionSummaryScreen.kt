package com.example.meeqapp.ui.predictions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meeqapp.ui.components.ActionButton
import com.example.meeqapp.ui.components.Badge
import com.example.meeqapp.ui.components.GhostButton
import com.example.meeqapp.ui.components.GhostButtonWithGuts
import com.example.meeqapp.ui.components.HintHeader
import com.example.meeqapp.ui.components.MediumHeader
import com.example.meeqapp.ui.components.Paragraph
import com.example.meeqapp.ui.components.SubHeader
import com.example.meeqapp.ui.theme.Theme
import com.example.meeqapp.ui.viewmodel.SharedViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun PredictionSummaryScreen(
    navigation: NavController,
    prediction: Prediction? = null,
    sharedViewModel : SharedViewModel = viewModel()
) {
    var prediction by remember { mutableStateOf<Prediction?>(null) }
    //var scrollState = rememberLazyListState()

    LaunchedEffect(sharedViewModel) {
        prediction = sharedViewModel.prediction.value
    }

    if (prediction == null) {
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .background(Theme.lightOffwhite)
            .verticalScroll(rememberScrollState())
    ) {
        prediction?.let {
            if (getPredictionState(it) == PredictionState.WAITING) {
                Badge(
                    text = "Follow up scheduled",
                    icon = Icons.Default.DateRange
                )
            }
            if (getPredictionResult(it) == PredictionResult.BETTER) {
                Badge(
                    text = "Better than expected",
                    icon = Icons.Default.ThumbUp
                )
            }
        }

        MediumHeader("Summary of Prediction")
        HintHeader(
            text = SimpleDateFormat(
                "D MMM YYYY, h:mm a",
                Locale.getDefault()
            ).format(prediction?.createdAt ?: Date())
        )

        Column(
            modifier = Modifier
                .padding(vertical = 12.dp)
        ) {
            SubHeader("Event or Task")
            GhostButtonWithGuts(
                borderColor = Theme.lightGray,
                onClick = { navigation.navigate(ASSUMPTION_SCREEN) }
            ) {
                Paragraph{ prediction?.eventLabel ?: ""}
            }
        }

        if (getPredictionState(prediction) == PredictionState.COMPLETE) {
            ActualExperience(prediction!!, navigation)
        }

        if (getPredictionState(prediction) == PredictionState.WAITING) {
            FollowUp(prediction!!) {
                sharedViewModel.prediction.value = prediction
                navigation.navigate(PREDICTION_FOLLOW_UP_SCREEN)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 48.dp)
        ) {
            GhostButton(
                title = "Delete",
                borderColor = Theme.red,
                textColor = Theme.red,
                style = Modifier
                    .weight(1f)
                    .padding(end = 12.dp),
                onClick = {}
            )

            ActionButton(
                title = "Finish",
                style = Modifier.weight(1f),
                onClick = {}
            )
        }
    }
}

@Composable
fun FollowUp(prediction: Prediction, onFollowUp: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        SubHeader(text = "Following up at")

        val formattedDate = SimpleDateFormat("d MMM yyyy, h:mm a", Locale.getDefault())
            .format(prediction.followUpAt)

        Paragraph(
            text = { formattedDate },
            modifier = Modifier.padding(bottom = 12.dp)
        )

        GhostButton(
            title = "Follow up now",
            borderColor = Theme.lightGray,
            onClick = {
                onFollowUp()
            }
        )
    }
}

@Composable
fun ActualExperience(
    prediction: Prediction,
    navigation: NavController,
    sharedViewModel : SharedViewModel = viewModel()
) {
    Column(
        modifier = Modifier.padding(bottom = 12.dp)
    ) {
        SubHeader(text = "Actual Experience")

        GhostButtonWithGuts(
            borderColor = Theme.lightGray,
            onClick = {
                sharedViewModel.prediction.value = prediction
                navigation.navigate(PREDICTION_FOLLOW_UP_SCREEN)
            },
            style = Modifier.padding(bottom =  6.dp)
        ) {
            val string = actualExperienceText[prediction.actualExperience]
            Paragraph { string!! }
        }

        GhostButtonWithGuts(
            borderColor = Theme.lightGray,
            onClick = {
                sharedViewModel.prediction.value = prediction
                navigation.navigate(PREDICTION_FOLLOW_UP_SCREEN)
            },
        ) {
            Paragraph{ prediction.actualExperienceNote ?: "* Left Blank *" }
        }
    }
}

val actualExperienceText = mapOf(
    Experience.GOOD to "Went well 👍",
    Experience.NEUTRAL to "Went okay 🤷‍",
    Experience.BAD to "Went poorly 👎"
)

@Preview(showBackground = true)
@Composable
fun PredictionSummaryScreenPreview() {
    PredictionSummaryScreen(rememberNavController(),)
}

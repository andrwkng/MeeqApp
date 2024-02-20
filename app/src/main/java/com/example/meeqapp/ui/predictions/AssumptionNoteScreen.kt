package com.example.meeqapp.ui.predictions

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.meeqapp.ui.components.ActionButton
import com.example.meeqapp.ui.components.GhostButton
import com.example.meeqapp.ui.components.HintHeader
import com.example.meeqapp.ui.components.MediumHeader
import com.example.meeqapp.ui.components.RoundedSelectorButton
import com.example.meeqapp.ui.components.SubHeader
import com.example.meeqapp.ui.theme.MeeqAppTheme
import com.example.meeqapp.ui.viewmodel.SharedViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssumptionNoteScreen(
    navigation: NavHostController,
    viewModel : SharedViewModel,
    isEditing: Boolean = false,
) {
    val prediction: Prediction? = viewModel.prediction
    Log.i("AssumptionNoteScreen",prediction?.uuid ?: "empty")
    val predictedExperienceNote by remember { mutableStateOf("") }

    fun onFelt(felt: Experience) {
        prediction?.predictedExperience = felt
    }

    fun onChangeNote(note: String) {
        prediction?.predictedExperienceNote = note
    }

    Column(
        modifier = Modifier
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {

        MediumHeader(text = "Predicted Experience")
        HintHeader(text = "How do you think this will go?")
        SubHeader(
            text = "Expected Experience",
            modifier = Modifier.padding(top = 12.dp)
        )

        RoundedSelectorButton(
            title = "Going to go well 👍",
            onClick = { onFelt(Experience.GOOD) },
            selected = prediction?.predictedExperience == Experience.GOOD
        )
        RoundedSelectorButton(
            title = "Going to go okay 🤷‍",
            onClick = { onFelt(Experience.NEUTRAL) },
            selected = prediction?.predictedExperience == Experience.NEUTRAL
        )
        RoundedSelectorButton(
            title = "Going to go poorly 👎",
            onClick = { onFelt(Experience.BAD) },
            selected = prediction?.predictedExperience == Experience.BAD
        )

        SubHeader(
            text = "Thought",
            modifier = Modifier
                .padding(top = 12.dp)
        )

        HintHeader(text = "In your own words, describe what you think might happen.")
        TextField(
            value = predictedExperienceNote,
            onValueChange = { onChangeNote(it) },
            textStyle = TextStyle(
                textAlign = TextAlign.Start,
                lineHeight = 20.sp
            ),
            modifier = Modifier
                .padding(top = 4.dp, bottom = 12.dp)
                .fillMaxWidth()
                .heightIn(min = 120.dp)
        )

        prediction?.let { renderButtons(it, isEditing, navigation) }
    }
}

@Composable
fun renderButtons(
    prediction: Prediction,
    isEditing: Boolean,
    navigation: NavHostController,
    userPreferenceViewModel: PredictionsViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    fun onFinish() {
        coroutineScope.launch {
            userPreferenceViewModel.savePrediction(prediction);

            if (isEditing) {
                navigation.navigate(PREDICTION_SUMMARY_SCREEN);
            }else {
                navigation.navigate(PREDICTION_FOLLOW_UP_SCHEDULE_SCREEN)
            }
        }
    }

    if (isEditing) {
        Row(
            modifier = Modifier.padding(top = 12.dp),
        ) {
            ActionButton(
                title = "Finish",
                onClick = { onFinish() },
                disabled = prediction.predictedExperience == null
            )
        }
        return
    }

    Row(
        modifier = Modifier.padding(top = 12.dp),
    ) {
        GhostButton(
            onClick = { navigation.navigate(ASSUMPTION_SCREEN) },
            title = "Back",
            style = Modifier.padding(end = 12.dp)
        )
        ActionButton(
            title = "Continue",
            onClick = { onFinish() },
            disabled = prediction.predictedExperience == null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AssumptionNoteScreenPreview() {
    MeeqAppTheme {
        AssumptionNoteScreen(rememberNavController(), hiltViewModel())
    }
}
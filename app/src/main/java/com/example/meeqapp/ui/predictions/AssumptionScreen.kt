package com.example.meeqapp.ui.predictions

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.meeqapp.ui.THOUGHT_SCREEN
import com.example.meeqapp.ui.components.ActionButton
import com.example.meeqapp.ui.components.GhostButton
import com.example.meeqapp.ui.components.HintHeader
import com.example.meeqapp.ui.components.MediumHeader
import com.example.meeqapp.ui.components.SubHeader
import com.example.meeqapp.ui.viewmodel.SharedViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssumptionScreen(
    navigation: NavHostController,
    sharedViewModel: SharedViewModel = hiltViewModel(),
    userPreferenceViewModel: PredictionsViewModel = viewModel(factory = PredictionsViewModel.provideFactory())
) {
    val prediction = remember { mutableStateOf<Prediction?>(null) }
    val isEditing = remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        prediction.value = newPrediction()
        isEditing.value = false
    }

    fun onFinish() {
        // Don't continue if we don't have an event label
        if (prediction.value?.eventLabel.isNullOrEmpty()) {
            Log.i("onFinish", "eventLabel null or empty, returned")
            return
        }

        GlobalScope.launch {
            userPreferenceViewModel.savePrediction(prediction.value!!)
        }
        sharedViewModel.prediction = prediction

        if (isEditing.value) {
            Log.i("onFinish", "isEditing true, navigate to prediction-summary-screen")
            navigation.navigate(PREDICTION_SUMMARY_SCREEN)
            return
        }
        navigation.navigate(ASSUMPTION_NOTE_SCREEN)
    }

    fun onChange(label: String) {
        prediction.value?.eventLabel = label
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                24.dp
                /*top = 24.dp,
                start = 24.dp,
                end = 24.dp,
                bottom = 24.dp*/
            )
    ) {
        MediumHeader(text = "New Prediction 🔮")
        HintHeader(
            text = "Predict your experience of an upcoming event and we’ll follow-up later to see if you were correct."
        )

        SubHeader(text = "Event or Task")

        var eventLabel by remember { mutableStateOf("") }
        TextField(
            value = eventLabel,
            onValueChange = {
                eventLabel = it
                onChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        )

        RenderButtons(
            isEditing = isEditing.value,
            onFinish = { onFinish() },
            onNavigate = { navigation.navigate(THOUGHT_SCREEN) },
            eventLabel = prediction.value?.eventLabel
        )
    }
}

@Composable
fun RenderButtons(
    isEditing: Boolean,
    onFinish: () -> Unit,
    onNavigate: () -> Unit,
    eventLabel: String?,
) {
    Row(
        modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxWidth()
    ) {
        if (isEditing) {
            ActionButton(
                style = Modifier
                    .weight(1f)
                    .padding(end = 12.dp),
                title = "Finish",
                onClick = onFinish,
                disabled = eventLabel.isNullOrBlank()
            )
        } else {
            GhostButton(
                onClick = onNavigate,
                title = "Cancel",
                marginRight = 12.dp,
            )
            ActionButton(
                title = "Continue",
                onClick = onFinish,
                disabled = eventLabel.isNullOrBlank()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AssumptionScreenPreview() {
    AssumptionScreen(rememberNavController())
}

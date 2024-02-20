package com.example.meeqapp.ui.predictions

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meeqapp.ui.components.ActionButton
import com.example.meeqapp.ui.components.HintHeader
import com.example.meeqapp.ui.components.MediumHeader
import com.example.meeqapp.ui.components.Paragraph
import com.example.meeqapp.ui.components.RoundedSelectorButton
import com.example.meeqapp.ui.components.SubHeader
import com.example.meeqapp.ui.pulse.Boost
import com.example.meeqapp.ui.pulse.PulseViewModel
import com.example.meeqapp.ui.viewmodel.SharedViewModel
import kotlinx.coroutines.launch

val START_PREDICTION: Boost = Boost(score = 3, label = "Prediction Started")
val FINISH_PREDICTION: Boost = Boost(score = 4, label = "Finished Prediction")

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PredictionFollowUpScreen(
    navigation: NavController,
    viewModel : SharedViewModel,
    predictionsViewModel: PredictionsViewModel = hiltViewModel(),
    pulseViewModel: PulseViewModel = viewModel()
) {
    val prediction: Prediction? = viewModel.prediction
    val (actualExperience, setActualExperience) = remember { mutableStateOf(prediction?.actualExperience) }
    val (actualExperienceNote, setActualExperienceNote) = remember { mutableStateOf(prediction?.actualExperienceNote) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(prediction) {
        setActualExperience(prediction?.actualExperience)
        setActualExperienceNote(prediction?.actualExperienceNote)
    }

     fun onFinish() {
         coroutineScope.launch {
             if (prediction != null) {
                 predictionsViewModel.savePrediction(prediction)
             }

             pulseViewModel.scheduleBoost(FINISH_PREDICTION)
             navigation.navigate(PREDICTION_SUMMARY_SCREEN)
         }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        topBar = {
            TopAppBar(
                title = {
                    Text("Actual Experience")
                },
                navigationIcon = {
                    IconButton(onClick = { navigation.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                MediumHeader(text = "Actual Experience")
                HintHeader(text = "How did it go?")

                SubHeader(text = "Event or Task", modifier = Modifier.padding(top = 12.dp))
                Paragraph{prediction?.eventLabel ?: ""}

                SubHeader(text = "Actual Experience", modifier = Modifier.padding(top = 12.dp))
                RoundedSelectorButton(
                    title = "It went well 👍",
                    selected = actualExperience == Experience.GOOD,
                    onClick = { setActualExperience(Experience.GOOD) }
                )
                RoundedSelectorButton(
                    title = "It went okay 🤷‍",
                    selected = actualExperience == Experience.NEUTRAL,
                    onClick = { setActualExperience(Experience.NEUTRAL) }
                )
                RoundedSelectorButton(
                    title = "It went poorly 👎",
                    selected = actualExperience == Experience.BAD,
                    onClick = { setActualExperience(Experience.BAD) }
                )

                SubHeader(text = "Actual Description", modifier = Modifier.padding(top = 12.dp))
                HintHeader(text = "What happened?")
                BasicTextField(
                    value = actualExperienceNote ?: "",
                    onValueChange = { setActualExperienceNote(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 48.dp)
                )

                ActionButton(
                    title = "Finish",
                    onClick = { onFinish() },
                    style = Modifier
                        .padding(top = 12.dp, bottom = 24.dp)
                        .fillMaxWidth(),
                    //enabled = actualExperience != null
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PredictionFollowUpScreenPreview() {
    PredictionFollowUpScreen(rememberNavController(), hiltViewModel())
}

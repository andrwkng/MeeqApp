package com.example.meeqapp.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.meeqapp.ui.CardCrown
import com.example.meeqapp.ui.TouchableCardContainer
import com.example.meeqapp.ui.components.HintHeader
import com.example.meeqapp.ui.components.MediumHeader
import com.example.meeqapp.ui.components.SubHeader
import com.example.meeqapp.ui.theme.Theme

@Composable
fun AnxietyCheckScreen(
    navigation: NavController,
) {
    var selectedType by remember { mutableStateOf("") }

    DisposableEffect(Unit) {
        onDispose {
            // Cleanup or perform actions on component disposal
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .background(Theme.lightOffwhite)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        MediumHeader(
            text = "Do any of these sound familiar?",
            modifier = Modifier
                .padding(bottom = 24.dp)
        )

        CardButton(
            title = "I'm currently worried about the outcome of something that just happened.",
            description = "Like a mistake made or the results of a test.",
            crown = "OUTCOMES",
            onClick = {
                selectedType = "outcome"
                navigation.navigate(PREDICTION_PROMPT_SCREEN.plus("/$selectedType"))
            }
        )

        CardButton(
            title = "I'm currently anxious about something I'm about to do.",
            description = "Like going to the dentist, flying on a plane, or interviewing for a job.",
            crown = "FORTUNE TELLING",
            onClick = {
                selectedType = "fortune_telling"
                navigation.navigate(PREDICTION_PROMPT_SCREEN.plus("/$selectedType"))
            }
        )

        CardButton(
            title = "I'm currently putting off a healthy behavior.",
            description = "Like exercising, seeing friends, or studying for a test.",
            crown = "PROCRASTINATION",
            onClick = {
                selectedType = "procrastination"
                navigation.navigate(PREDICTION_PROMPT_SCREEN.plus("/$selectedType"))
            }
        )

        TouchableCardContainer(
            onClick = {
                navigation.navigate(CHECKUP_PROMPT_SCREEN)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            SubHeader(
                text = "None of these.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}


@Composable
fun CardButton(
    onClick: () -> Unit,
    crown: String,
    title: String,
    description: String
) {
    TouchableCardContainer(
        onClick = onClick
    ) {
        CardCrown(
            text = crown,
            icon = Icons.Filled.Info
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            SubHeader(
                modifier = Modifier
                    .padding(bottom = 4.dp),
                fontSize = 14.sp,
                text = title
            )
            HintHeader(
                modifier = Modifier,
                fontSize = 14.sp,
                text = description
            )
        }
    }
}

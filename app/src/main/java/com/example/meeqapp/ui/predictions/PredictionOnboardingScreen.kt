package com.example.meeqapp.ui.predictions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meeqapp.ui.components.ActionButton
import com.example.meeqapp.ui.components.HintHeader
import com.example.meeqapp.ui.components.LI
import com.example.meeqapp.ui.components.Paragraph
import com.example.meeqapp.ui.components.SubHeader
import com.example.meeqapp.ui.theme.Theme.lightOffwhite
import com.example.meeqapp.ui.thoughts.MediumHeader

@Composable
fun PredictionOnboardingScreen(navigation: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 24.dp,
                start = 24.dp,
                end = 24.dp
            )
            .background(lightOffwhite)
            .verticalScroll(rememberScrollState())
    ) {
        // Status bar
        /*StatusBars(
            hidden = false
        )*/

        // Header
        MediumHeader(title = "What are Predictions?")
        Spacer(modifier = Modifier.height(8.dp))
        // Hint Header
        HintHeader(text = "Read this; you'll only see it once.")
        Spacer(modifier = Modifier.height(8.dp))
        // Overview Section
        SubHeader(text = "Overview")
        Paragraph(
            modifier = Modifier
                .padding(bottom = 12.dp),
        ) { "This exercise lets you predict your enjoyment of upcoming events or tasks. Use it to test your ability to tell the future." }

        // When to use Predictions Section
        SubHeader(text = "When to use Predictions")
        Paragraph(
            modifier = Modifier
                .padding(bottom = 12.dp),
        ){
            "You can use Predictions like a TODO-list for stuff you're anxious about. A good cue is procrastinating something or being overly concerned with the consequences of a minor mistake you made."
        }

        // Examples Section
        SubHeader(text = "Examples")
        LI(text = "You're afraid of flying on the airplane you're about to board.")
        Spacer(modifier = Modifier.height(8.dp))
        LI(text = "You have a test tomorrow, but you're procrastinating studying for it.")
        Spacer(modifier = Modifier.height(8.dp))
        LI(text = "You used to really enjoy cooking, but you now predict you \"can't enjoy it anymore.\"")
        Spacer(modifier = Modifier.height(8.dp))
        LI(text = "You were 5 minutes late and now you're convinced your boss is going to fire you.")
        Spacer(modifier = Modifier.height(8.dp))
        LI(text = "You have a dentist appointment in the morning and you can't sleep because you're worried it will be terrible.")
        Spacer(modifier = Modifier.height(8.dp))

        // Continue Button
        ActionButton(
            title = "Continue",
            marginTop = 12.dp,
            marginBottom = 48.dp,
            onClick = {
                navigation.navigate(ASSUMPTION_SCREEN)
            }
        )
    }
}

@Preview
@Composable
fun PredictionOnboardingScreenPreview() {
    PredictionOnboardingScreen(rememberNavController())
}
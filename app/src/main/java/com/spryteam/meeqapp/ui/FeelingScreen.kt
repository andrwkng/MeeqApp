package com.spryteam.meeqapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spryteam.meeqapp.ui.components.GhostButton
import com.spryteam.meeqapp.ui.components.MediumHeader
import com.spryteam.meeqapp.ui.theme.Theme
import com.spryteam.meeqapp.ui.thoughts.ThoughtViewModel

@Composable
fun FeelingRoute(
    onNavigateToFollowUp: () -> Unit,
    thoughtViewModel: ThoughtViewModel,
    //viewModel: SharedViewModel,
) {
    FeelingScreen(
        onFeltWorsePressed = { thoughtViewModel.onFeltWorse(onNavigateToFollowUp) },
        onFeltTheSamePressed = { thoughtViewModel.onFeltTheSame(onNavigateToFollowUp) },
        onFeltBetterPressed = { thoughtViewModel.onFeltBetter(onNavigateToFollowUp) }
    )
}

@Composable
fun FeelingScreen(
    onFeltWorsePressed: () -> Unit,
    onFeltTheSamePressed: () -> Unit,
    onFeltBetterPressed: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.lightOffWhite)
            .padding(12.dp)
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        MediumHeader(
            text = "How are you feeling now?",
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
        )

        // GhostButton - Felt Better
        GhostButton(
            title = "Better than before 👍",
            modifier = Modifier.fillMaxWidth(),
            borderColor = Theme.colorBlue,
            textColor = Theme.darkText,
            marginBottom = 12.dp,
            onClick = { onFeltBetterPressed() }
        )

        // GhostButton - Felt The Same
        GhostButton(
            title = "About the same 🤷‍",
            modifier = Modifier.fillMaxWidth(),
            borderColor = Theme.colorGray,
            textColor = Theme.darkText,
            marginBottom = 12.dp,
            onClick = { onFeltTheSamePressed() }
        )

        // GhostButton - Felt Worse
        GhostButton(
            title = "Worse than before 👎",
            modifier = Modifier.fillMaxWidth(),
            borderColor = Theme.colorRed,
            textColor = Theme.darkText,
            onClick = { onFeltWorsePressed() }
        )
    }
}


@Preview
@Composable
fun FeelingScreenPreview() {
    FeelingScreen(
        onFeltWorsePressed = { /*TODO*/ },
        onFeltTheSamePressed = { /*TODO*/ },
        onFeltBetterPressed = { /*TODO*/ }
    )
}
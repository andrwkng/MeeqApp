package com.sprytm.meeqapp.ui

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
import com.sprytm.meeqapp.ui.components.GhostButton
import com.sprytm.meeqapp.ui.components.MediumHeader
import com.sprytm.meeqapp.ui.theme.Theme
import com.sprytm.meeqapp.ui.viewmodel.SharedViewModel

@Composable
fun FeelingRoute(
    onNavigateToFollowUp: () -> Unit,
    viewModel: SharedViewModel,
) {
    FeelingScreen(
        onFeltWorsePressed = { viewModel.onFeltWorse(onNavigateToFollowUp) },
        onFeltTheSamePressed = { viewModel.onFeltTheSame(onNavigateToFollowUp) },
        onFeltBetterPressed = { viewModel.onFeltBetter(onNavigateToFollowUp) }
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
            .background(Theme.colorLightOffwhite)
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
            style = Modifier.fillMaxWidth(),
            borderColor = Theme.colorLightGray,
            textColor = Theme.darkText,
            marginBottom = 12.dp,
            onClick = { onFeltBetterPressed() }
        )

        // GhostButton - Felt The Same
        GhostButton(
            title = "About the same 🤷‍",
            style = Modifier.fillMaxWidth(),
            borderColor = Theme.colorLightGray,
            textColor = Theme.darkText,
            marginBottom = 12.dp,
            onClick = { onFeltTheSamePressed() }
        )

        // GhostButton - Felt Worse
        GhostButton(
            title = "Worse than before 👎",
            style = Modifier.fillMaxWidth(),
            borderColor = Theme.colorLightGray,
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
package com.sprytm.meeqapp.ui.followup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprytm.meeqapp.ui.theme.Theme
import com.sprytm.meeqapp.ui.viewmodel.SharedViewModel

@Composable
fun FollowUpRoute(
    onNavigateToFinished: () -> Unit,
    viewModel: SharedViewModel,
) {
    viewModel.navigateToFinished.value = { onNavigateToFinished() }
    FollowUpScreen(
        onContinue = { viewModel.onContinue() },
        onSetCheckup = { viewModel.onSetCheckup() }
    )

}

@Composable
fun FollowUpScreen(
    onContinue: () -> Unit,
    onSetCheckup: () -> Unit,
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .background(Theme.colorLightOffwhite)
            .padding(16.dp),
        //contentAlignment = Alignment.Center
    ) {
        // Status Bar
        /*Spacer(
            //modifier = Modifier
                //.statusBarsHeight()
                //.background(MaterialTheme.colorBackground)
        )*/

        // Medium Header
        Text(
            text = "Want to follow up later?",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )

        // Hint Header
        Text(
            text = "This is a chance to re-examine your thoughts with a different " +
                    "perspective and cement your changed view.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            //style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )

        // Action Button
        Button(
            onClick = onSetCheckup,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .height(48.dp)
        ) {
            Text(text = "Sure, let's do it.")
        }

        // Ghost Button
        OutlinedButton(
            onClick = {
                onContinue()
            },
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            border = BorderStroke(1.dp, Theme.colorGray),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Theme.lightText
            )
        ) {
            Text(text = "No thanks.")
        }
    }

}

@Preview
@Composable
fun FollowUpScreenPreview() {
    FollowUpScreen(
        onContinue = { /*TODO*/ },
        onSetCheckup = { /*TODO*/ }
    )
}

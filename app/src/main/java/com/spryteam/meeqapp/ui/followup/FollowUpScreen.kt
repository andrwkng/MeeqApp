package com.spryteam.meeqapp.ui.followup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spryteam.meeqapp.ui.components.ActionButton
import com.spryteam.meeqapp.ui.components.GhostButton
import com.spryteam.meeqapp.ui.components.HintHeader
import com.spryteam.meeqapp.ui.components.MediumHeader
import com.spryteam.meeqapp.ui.theme.Theme


@Composable
fun FollowUpScreen(
    onContinue: () -> Unit,
    onSetCheckup: () -> Unit,
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .background(Theme.lightOffWhite)
            .padding(16.dp),
    ) {

        MediumHeader(
            text = "Want to follow up later?",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            textAlign = TextAlign.Center
        )

        HintHeader(
            text = "This is a chance to re-examine your thoughts with a different " +
                    "perspective and cement your changed view.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Left
        )

        ActionButton(
            title = "Sure, let's do it.",
            onClick = { onSetCheckup() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        GhostButton(
            title = "No thanks.",
            onClick = {
                onContinue()
            },
            modifier = Modifier
                .fillMaxWidth(),
            borderColor = Theme.colorGray,
            textColor = Theme.lightText
        )
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

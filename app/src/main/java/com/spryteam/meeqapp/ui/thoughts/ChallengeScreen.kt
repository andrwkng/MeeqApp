package com.spryteam.meeqapp.ui.thoughts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spryteam.meeqapp.R
import com.spryteam.meeqapp.ui.components.ActionButton
import com.spryteam.meeqapp.ui.components.GhostButton
import com.spryteam.meeqapp.ui.components.GhostButtonWithGuts
import com.spryteam.meeqapp.ui.components.HintHeader
import com.spryteam.meeqapp.ui.components.MediumHeader
import com.spryteam.meeqapp.ui.components.SingleLineText
import com.spryteam.meeqapp.ui.components.SubHeader
import com.spryteam.meeqapp.ui.theme.Theme


@Composable
fun ChallengeScreen(
    challengeVal: String,
    onChallengeChange: (String) -> Unit,
    isEditing: Boolean,
    isNextDisabled: Boolean,
    autoThoughtVal: String,
    onNavigateToAutoThought: () -> Unit,
    onNextPressed: () -> Unit,
    onFinishPressed: () -> Unit,
    onBackPressed: () -> Unit
) {
    val textInputStyle = TextStyle(color = Color.Black, fontSize = 16.sp)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Theme.lightOffWhite)
    ) {
        Column {
            MediumHeader(text = stringResource(id = R.string.challenge))
            HintHeader(
                text = "What could you be wrong about? Is there something you don't have " +
                        "enough evidence for?"
            )

            Column(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
            ) {
                SubHeader(text = "Your Thought")
                GhostButtonWithGuts(
                    borderColor = Theme.colorGray,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onNavigateToAutoThought
                ) {
                    SingleLineText(autoThoughtVal)
                }
            }

            OutlinedTextField(
                value = challengeVal,
                onValueChange = onChallengeChange,
                textStyle = textInputStyle,
                label = { SubHeader(text = "Your Challenge") },
                placeholder = { Text(text = stringResource(id = R.string.changed_placeholder)) },
                modifier = Modifier
                    //.padding(top = 8.dp)
                    .fillMaxWidth()
                    //.heightIn(min = 100.dp)

            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .padding(end = 24.dp)
                    .wrapContentWidth(align = Alignment.End)
            ) {
                if (isEditing) {
                    ActionButton(
                        title = "Save",
                        onClick = { onFinishPressed() },
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    GhostButton(
                        borderColor = Theme.lightGray,
                        textColor = Theme.veryLightText,
                        title = "Back",
                        modifier = Modifier.weight(1f),
                        marginRight = 24.dp,
                        onClick = { onBackPressed() }
                    )
                    ActionButton(
                        title = "Next",
                        onClick = { onNextPressed() },
                        disabled = isNextDisabled,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChallengeScreenPreview() {
    ChallengeScreen(
        challengeVal = "",
        onChallengeChange = {},
        isEditing = false,
        isNextDisabled = true,
        autoThoughtVal = "My Automatic Thought",
        onNavigateToAutoThought = { /*TODO*/ },
        onNextPressed = { /*TODO*/ },
        onFinishPressed = { /*TODO*/ }) {

    }
}
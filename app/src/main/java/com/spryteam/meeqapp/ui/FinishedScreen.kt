package com.spryteam.meeqapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spryteam.meeqapp.ui.components.ActionButton
import com.spryteam.meeqapp.ui.components.Badge
import com.spryteam.meeqapp.ui.components.GhostButton
import com.spryteam.meeqapp.ui.components.GhostButtonWithGuts
import com.spryteam.meeqapp.ui.components.HintHeader
import com.spryteam.meeqapp.ui.components.MediumHeader
import com.spryteam.meeqapp.ui.components.SingleLineText
import com.spryteam.meeqapp.ui.components.SubHeader
import com.spryteam.meeqapp.ui.distortions.CognitiveDistortion
import com.spryteam.meeqapp.ui.distortions.distortions
import com.spryteam.meeqapp.ui.theme.Theme
import com.spryteam.meeqapp.ui.thoughts.FollowUpState
import java.text.SimpleDateFormat
import java.time.LocalDate

@SuppressLint("SimpleDateFormat")
@Composable
fun FinishedScreen(
    automaticThought: String,
    challenge: String,
    cognitiveDistortions: List<CognitiveDistortion>,
    alternativeThought: String,
    followUpNote: String,
    followUpState: FollowUpState,
    createdAt: String,
    onAutoThoughtPressed: () -> Unit,
    onDistortionsPressed: () -> Unit,
    onChallengePressed: () -> Unit,
    onAltThoughtPressed: () -> Unit,
    onFollowUpPressed: () -> Unit,
    onDeletePressed: () -> Unit,
    onRepeatPressed: () -> Unit,
    onFinishPressed: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 50.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                //.padding(bottom = 50.dp + TAB_BAR_HEIGHT)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp)
            ) {
                if (followUpState == FollowUpState.SCHEDULED) {
                Badge(
                    text = "Follow up scheduled",
                    icon = Icons.Default.DateRange,
                    modifier = Modifier.padding(bottom = 18.dp)
                )
                }

                if (followUpState == FollowUpState.READY) {
                    Badge(
                        text = "Reviewing Thought",
                        icon = Icons.Default.DateRange,
                        backgroundColor = Theme.lightPink,
                        modifier = Modifier.padding(bottom = 18.dp)
                    )
                }

                MediumHeader(text = if (followUpState == FollowUpState.READY) "Does this still seem correct?" else "Summary of Thought")

                HintHeader(
                    //text = " 8 Mar 2024, 4:36 am"
                    if (followUpState == FollowUpState.READY) {
                    "Thought recorded on ${
                        SimpleDateFormat("D MMM y, h:mm a").format(
                            createdAt
                        )
                    }"
                    } else {
                        ""
                    }
                )
            }

            Column(
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                SubHeader(text = "Your first thought")

                GhostButtonWithGuts(
                    borderColor = Theme.lightGray,
                    onClick = onAutoThoughtPressed,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {
                    SingleLineText(automaticThought)
                }
            }

            Column(
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                SubHeader(text = "How you challenged it")

                GhostButtonWithGuts(
                    borderColor = Theme.lightGray,
                    onClick = onDistortionsPressed,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp)
                ) {
                    EmojiList(cognitiveDistortions)
                }

                GhostButtonWithGuts(
                    borderColor = Theme.lightGray,
                    onClick = onChallengePressed
                ) {
                    SingleLineText(challenge)
                }
            }

            Column(
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                SubHeader(text = "What you could think")

                GhostButtonWithGuts(
                    borderColor = Theme.lightGray,
                    onClick = onAltThoughtPressed
                ) {
                    SingleLineText(alternativeThought)
                }
            }

            if (followUpNote.isNotEmpty()) {
                Column(
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    SubHeader(text = "Follow-up Note")

                    GhostButtonWithGuts(
                        borderColor = Theme.lightGray,
                        onClick = onFollowUpPressed
                    ) {
                        SingleLineText(followUpNote)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                GhostButton(
                    borderColor = Color.Red,
                    textColor = Color.Red,
                    onClick = { onDeletePressed() },
                    modifier = Modifier.padding(end = 12.dp),
                    title = "Delete",
                )

                GhostButtonWithGuts(
                    onClick = { onRepeatPressed() },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        //.padding(end = 12.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Repeat",
                            modifier = Modifier.weight(1f, fill = true),
                            fontSize = 16.sp
                        )
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null,
                            tint = Theme.colorBlue
                        )
                    }
                }
            }

            ActionButton(
                title = "Finish",
                onClick = { onFinishPressed() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun FinishedScreenPreview() {
    FinishedScreen(
        automaticThought = "My automatic thought",
        challenge = "My challenge",
        cognitiveDistortions = distortions,
        alternativeThought = "My alternative thought",
        followUpNote = "Follow up",
        followUpState = FollowUpState.SCHEDULED,
        createdAt = LocalDate.now().toString(),
        onAutoThoughtPressed = { /*TODO*/ },
        onDistortionsPressed = { /*TODO*/ },
        onChallengePressed = { /*TODO*/ },
        onAltThoughtPressed = { /*TODO*/ },
        onFollowUpPressed = { /*TODO*/ },
        onDeletePressed = { /*TODO*/ },
        onRepeatPressed = { /*TODO*/ }) {
        
    }
}

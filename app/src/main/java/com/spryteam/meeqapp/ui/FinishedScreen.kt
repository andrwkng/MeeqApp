package com.sprytm.meeqapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprytm.meeqapp.ui.components.Badge
import com.sprytm.meeqapp.ui.components.GhostButton
import com.sprytm.meeqapp.ui.components.GhostButtonWithGuts
import com.sprytm.meeqapp.ui.components.HintHeader
import com.sprytm.meeqapp.ui.components.MediumHeader
import com.sprytm.meeqapp.ui.components.Paragraph
import com.sprytm.meeqapp.ui.components.SubHeader
import com.sprytm.meeqapp.ui.distortions.CognitiveDistortion
import com.sprytm.meeqapp.ui.theme.Theme
import com.sprytm.meeqapp.ui.thoughts.FollowUpState
import com.sprytm.meeqapp.ui.thoughts.ThoughtViewModel
import com.sprytm.meeqapp.ui.viewmodel.SharedViewModel
import java.text.SimpleDateFormat

@Composable
fun FinishedRoute(
    onNavigateToAutoThought: () -> Unit,
    onNavigateToDistortions: () -> Unit,
    onNavigateToChallenge: () -> Unit,
    onNavigateToAlternative: () -> Unit,
    onNavigateToFollowUp: () -> Unit,
    resetNavigationToThought: () -> Unit,
    viewModel: SharedViewModel,
    thoughtViewModel: ThoughtViewModel
    ) {
    val autoThought: String by viewModel.automaticThought.collectAsState("")
    val altThought: String by thoughtViewModel.alternativeThought.collectAsState("")
    val distortions: List<CognitiveDistortion> by viewModel.distortionList.collectAsState(emptyList())
    val challenge: String by viewModel.challenge.collectAsState("")

    FinishedScreen(
        automaticThought = autoThought,
        challenge = challenge,
        cognitiveDistortions = distortions,
        alternativeThought = altThought,
        followUpNote = viewModel.followUpNote ?: "",
        followUpState = thoughtViewModel.followUpState(),
        createdAt = viewModel.createdAt.toString(),
        onAutoThoughtPressed = onNavigateToAutoThought,
        onDistortionsPressed = onNavigateToDistortions,
        onChallengePressed = onNavigateToChallenge,
        onAltThoughtPressed = onNavigateToAlternative,
        onFollowUpPressed = onNavigateToFollowUp,
        onDeletePressed = { thoughtViewModel.deleteThought() },
        onRepeatPressed = { thoughtViewModel.onRepeat(onNavigateToAutoThought) },
        onFinishPressed = { thoughtViewModel.onFinishedNext(resetNavigationToThought) }
    )

}

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
        //if (thought != null) {
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
                        backgroundColor = Theme.colorLightPink,
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
                    Paragraph { automaticThought }
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
                    Paragraph { challenge }
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
                    Paragraph { alternativeThought }
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
                        Paragraph { followUpNote }
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
                    style = Modifier.padding(end = 12.dp),
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

            Button(
                onClick = { onFinishPressed() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Finish")
            }

        }

        //}
    }
}

@Preview(showBackground = true)
@Composable
fun FinishedScreenPreview() {
    //FinishedScreen(rememberNavController())
}

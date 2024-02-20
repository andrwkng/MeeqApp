package com.example.meeqapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meeqapp.ui.articles.TAB_BAR_HEIGHT
import com.example.meeqapp.ui.components.ActionButton
import com.example.meeqapp.ui.components.Badge
import com.example.meeqapp.ui.components.GhostButton
import com.example.meeqapp.ui.components.GhostButtonWithGuts
import com.example.meeqapp.ui.components.HintHeader
import com.example.meeqapp.ui.components.Paragraph
import com.example.meeqapp.ui.components.SubHeader
import com.example.meeqapp.ui.theme.Theme
import com.example.meeqapp.ui.thoughts.FollowUpState
import com.example.meeqapp.ui.thoughts.ImmediateCheckup
import com.example.meeqapp.ui.thoughts.MediumHeader
import com.example.meeqapp.ui.thoughts.SavedThought
import com.example.meeqapp.ui.thoughts.ThoughtViewModel
import com.example.meeqapp.ui.thoughts.followUpState
import com.example.meeqapp.ui.thoughts.newThought
import com.example.meeqapp.ui.viewmodel.SharedViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import resetNavigationTo
import java.text.SimpleDateFormat

@Composable
fun FinishedScreen(
    navigation: NavController,
    viewModel: SharedViewModel,
    thoughtViewModel: ThoughtViewModel = hiltViewModel()
) {
    val thought = viewModel.thought as SavedThought
    val followUpState = remember { followUpState(thought) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    fun shouldSendToAndroidReview(): Boolean {
        /*if (userPreferenceStore.hasRatedFalse!!) {
            return false
        }*/

        runBlocking {
            if (thoughtViewModel.countThoughts() < 2) {
                return@runBlocking false
            } else {
            }
        }

        if (thought?.immediateCheckup != ImmediateCheckup.BETTER) {
            return false
        }

        return true
    }

    fun onRepeat() {
        val newThought = newThought()
        newThought.automaticThought = thought?.automaticThought!!
        viewModel.updateThought(newThought)
        navigation.navigate(AUTOMATIC_THOUGHT_SCREEN)
    }

    fun onNext() {
        if (followUpState(thought) == FollowUpState.READY) {
            val oldThought = thought!!
            oldThought.followUpCompleted = true
            coroutineScope.launch { viewModel.saveThought(oldThought) }
        }

        if (shouldSendToAndroidReview()) {
            navigation.navigate(FEEDBACK_SCREEN)
            return
        }
        resetNavigationTo(navigation, THOUGHT_SCREEN)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 50.dp)
    ) {
        if (thought != null) {
            Box(
                modifier = Modifier
                    .padding(bottom = 50.dp + TAB_BAR_HEIGHT)
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

                    MediumHeader(title = if (followUpState == FollowUpState.READY) "Does this still seem correct?" else "Summary of Thought")

                    HintHeader(
                        text = if (followUpState == FollowUpState.READY) {
                            "Thought recorded on ${
                                SimpleDateFormat("D MMM YYYY, h:mm a").format(
                                    thought.createdAt
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
                        onClick = {
                            navigation.navigate(AUTOMATIC_THOUGHT_SCREEN)
                        },
                        style = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Paragraph { thought.automaticThought }
                    }
                }

                Column(
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    SubHeader(text = "How you challenged it")

                    GhostButtonWithGuts(
                        borderColor = Theme.lightGray,
                        onClick = {
                            // Handle button click
                        },
                        style = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp)
                    ) {
                        EmojiList(thought = thought)
                    }

                    GhostButtonWithGuts(
                        borderColor = Theme.lightGray,
                        onClick = {
                            // Handle button click
                        }
                    ) {
                        Paragraph { thought.challenge }
                    }
                }

                Column(
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    SubHeader(text = "What you could think")

                    GhostButtonWithGuts(
                        borderColor = Theme.lightGray,
                        onClick = {
                            // Handle button click
                        }
                    ) {
                        Paragraph { thought.alternativeThought }
                    }
                }

                if (thought.followUpNote != null) {
                    Column(
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        SubHeader(text = "Follow-up Note")

                        GhostButtonWithGuts(
                            borderColor = Theme.lightGray,
                            onClick = {
                                // Handle button click
                            }
                        ) {
                            Paragraph { thought.followUpNote ?: "" }
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
                        title = "Delete",
                        borderColor = Color.Red,
                        textColor = Color.Red,
                        onClick = {
                            // Handle button click
                        },
                        style = Modifier.padding(end = 12.dp)
                    )

                    GhostButtonWithGuts(
                        onClick = { onRepeat() },
                        style = Modifier
                            //.flex(1)
                            .fillMaxHeight()
                            .padding(end = 12.dp),
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
                    onClick = { onNext() },
                    style = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

package com.spryteam.meeqapp.ui.thoughts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spryteam.meeqapp.data.HistoryButtonLabelSetting
import com.spryteam.meeqapp.data.newSavedThought
import com.spryteam.meeqapp.ui.CardAttentionDot
import com.spryteam.meeqapp.ui.CardBadge
import com.spryteam.meeqapp.ui.CardCrown
import com.spryteam.meeqapp.ui.CardMutedContent
import com.spryteam.meeqapp.ui.CardTextContent
import com.spryteam.meeqapp.ui.EmojiList
import com.spryteam.meeqapp.ui.distortions.distortions
import com.spryteam.meeqapp.ui.theme.Theme

@Composable
fun ThoughtItem(
    thought: SavedThought,
    historyButtonLabel: HistoryButtonLabelSetting,
    followUpState: () -> FollowUpState,
    onPress: (SavedThought) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Theme.lightOffWhite)
    ) {
        if (followUpState() == FollowUpState.READY) {
            CardAttentionDot()
        }

        ElevatedCard(
            onClick = { onPress(thought) },
            colors = CardColors(
                contentColor = Theme.veryLightText,
                containerColor = Theme.lightGray,
                disabledContainerColor = Theme.lightGray,
                disabledContentColor = Theme.colorGray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onPress(thought) }
        ) {
            CardCrown(text = "THOUGHT", Icons.Default.Email)

            CardTextContent(
                text = when (historyButtonLabel) {
                    HistoryButtonLabelSetting.ALTERNATIVE_THOUGHT -> thought.alternativeThought
                    else -> thought.automaticThought
                }
            )

            CardMutedContent {
                EmojiList(thought.cognitiveDistortions)
            }

            if (thought.immediateCheckup == ImmediateCheckup.BETTER) {
                CardBadge(
                    text = "Felt better after recording",
                    Icons.Default.ThumbUp,
                    backgroundColor = Theme.lightOffWhite
                )
            }

            if (thought.followUpCheckup == "better") {
                CardBadge(
                    text = "Felt better later on",
                    Icons.Default.ThumbUp,
                    backgroundColor = Theme.lightOffWhite
                )
            }

            if (followUpState() == FollowUpState.SCHEDULED) CardBadge(
                text = "Follow up scheduled",
                Icons.Default.DateRange,
                backgroundColor = Theme.lightOffWhite
            )

            if (followUpState() == FollowUpState.READY) CardBadge(
                text = "Tap to start follow up",
                Icons.Default.PlayArrow,
                backgroundColor = Theme.lightOffWhite
            )
        }
    }
}

enum class FollowUpState {
    SCHEDULED, READY, NONE
}

@Preview(showBackground = true)
@Composable
fun PreviewThoughtItem() {
    val list = distortions
    list[2].selected = true
    list[3].selected = true
    list[5].selected = true
    val savedThought = newSavedThought(
        automaticThought = "My automatic thought",
        challenge = "My challenge",
        alternativeThought = "My alternative though",
        cognitiveDistortions = distortions,
        immediateCheckup = ImmediateCheckup.BETTER,
        followUpCheckup = "better"
    )
    ThoughtItem(
        thought = savedThought,
        historyButtonLabel = HistoryButtonLabelSetting.ALTERNATIVE_THOUGHT,
        followUpState = { FollowUpState.READY },
        onPress = {},
    )
}
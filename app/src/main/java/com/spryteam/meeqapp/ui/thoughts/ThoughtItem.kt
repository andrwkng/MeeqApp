package com.sprytm.meeqapp.ui.thoughts

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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprytm.meeqapp.data.HistoryButtonLabelSetting
import com.sprytm.meeqapp.data.NewThought
import com.sprytm.meeqapp.ui.CardAttentionDot
import com.sprytm.meeqapp.ui.CardBadge
import com.sprytm.meeqapp.ui.CardCrown
import com.sprytm.meeqapp.ui.CardMutedContent
import com.sprytm.meeqapp.ui.CardTextContent
import com.sprytm.meeqapp.ui.EmojiList
import com.sprytm.meeqapp.ui.theme.Theme


/*
@Composable
fun ThoughtItem(
    thought: SavedThought,
    historyButtonLabel: HistoryButtonLabelSetting,
    onPress: (SavedThought) -> Unit,
    onDelete: (SavedThought) -> Unit
) {
    val theme = Theme

    Row(
        modifier = Modifier
            .padding(bottom = 18.dp)
            .clickable { onPress(thought) }
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .border(2.dp, theme.lightGray, RoundedCornerShape(8.dp))
                .border(1.dp, theme.lightGray, RoundedCornerShape(8.dp))
                .padding(end = 18.dp)
                .weight(1f)
        ) {
            Text(
                text = if (historyButtonLabel == HistoryButtonLabelSetting.ALTERNATIVE_THOUGHT) {
                    thought.alternativeThought
                } else {
                    thought.automaticThought
                },
                modifier = Modifier
                    .padding(12.dp),
                    //.weight(1f),
                color = theme.colorDarkText,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp
            )

            Card(
                modifier = Modifier
                    .background(theme.lightOffwhite)
                    .padding(4.dp)
                    .border(1.dp, Color.Transparent, RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                    .fillMaxWidth()
                    .heightIn(min = 0.dp)
                    .weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = take(
                        thought.cognitiveDistortions
                            .filterNotNull()
                            .filter { it.selected }
                            .map { emojiForSlug(it.slug) },
                        8
                    ).filterNotNull().joinToString(" ").trim(),
                    modifier = Modifier.padding(8.dp),
                    color = Theme.colorDarkText
                )
            }
        }

        IconButton(
            modifier = Modifier.align(Alignment.Top),
            onClick = { onDelete(thought) },
            contentDescription = "Delete Thought"
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Thought")
        }
    }
}
*/



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThoughtItem(
    thought: SavedThought,
    historyButtonLabel: HistoryButtonLabelSetting,
    followUpState: () -> FollowUpState,
    onPress: (SavedThought) -> Unit,
    //onDelete: (SavedThought) -> Unit,
    key: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (followUpState() == FollowUpState.READY) {
            CardAttentionDot()
        }

        Card(
            onClick = { onPress(thought) },
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
                CardBadge(text = "Felt better after recording", Icons.Default.ThumbUp)
            }

            if (thought.followUpCheckup == "better") {
                CardBadge(text = "Felt better later on", Icons.Default.ThumbUp)
            }

            when (followUpState()) {
                FollowUpState.SCHEDULED -> CardBadge(
                    text = "Follow up scheduled",
                    Icons.Default.DateRange
                )

                FollowUpState.READY -> CardBadge(
                    text = "Tap to start follow up",
                    Icons.Default.PlayArrow,
                    backgroundColor = Theme.colorLightPink
                )

                else -> {}
            }
        }
    }
}

enum class FollowUpState {
    SCHEDULED, READY, NONE

}

@Preview(showBackground = true)
@Composable
fun PreviewThoughtItem() {
    val savedThought = NewThought(
        automaticThought = "My automatic thought",
        challenge = "My challenge",
        alternativeThought = "My alternative thougth",
    )
    ThoughtItem(
        thought = savedThought,
        historyButtonLabel = HistoryButtonLabelSetting.ALTERNATIVE_THOUGHT,
        followUpState = { FollowUpState.READY },
        onPress = {},
        key = "Key_random_12234"
    )
}
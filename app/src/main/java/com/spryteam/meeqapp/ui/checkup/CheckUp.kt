package com.spryteam.meeqapp.ui.checkup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spryteam.meeqapp.ui.components.CardBadge
import com.spryteam.meeqapp.ui.components.CardCrown
import com.spryteam.meeqapp.ui.components.CardMutedContent
import com.spryteam.meeqapp.ui.exercises.Mood
import com.spryteam.meeqapp.ui.theme.Theme
import com.spryteam.meeqapp.ui.thoughts.Exercise
import java.time.LocalDate

data class Checkup(
    val currentMood: Mood,
    val predictedMood: Mood? = null,
    val note: String? = null,
    val goal: String? = null,
    override val uuid: String,
    override val createdAt: LocalDate,
    override var updatedAt: LocalDate
): Exercise

@Composable
fun CheckUpCard(
    currentCheckup: Checkup,
    onPress: (Checkup) -> Unit,
    key: String? = null
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable { onPress(currentCheckup) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            CardCrown(
                text = "MILESTONE",
                icon = Icons.Filled.Face,
            )

            CardMutedContent {
                Text(
                    text = "Recorded on ${currentCheckup.createdAt.toString()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Theme.veryLightText // Replace with your actual theme color
                )
            }

            if (currentCheckup.currentMood == Mood.GOOD) {
                CardBadge(
                    icon = Icons.Filled.ThumbUp,
                    text = "Doing well",
                )
            }
        }
    }
}

@Composable
fun CheckupList(checkups: List<Checkup>, onItemClick: (Checkup) -> Unit) {
    LazyColumn {
        items(checkups) { checkup ->
            CheckUpCard(currentCheckup = checkup, onPress = onItemClick)
        }
    }
}

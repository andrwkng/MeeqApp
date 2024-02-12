package com.example.meeqapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.meeqapp.ui.theme.Theme

@Composable
fun ExerciseButton(
    title: String,
    hint: String,
    icon: ImageVector,
    hasYourAttention: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        if (hasYourAttention) {
            CardAttentionDot()
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .background(
                    color = if (hasYourAttention) Theme.colorLightPink else Theme.colorLightOffwhite
                )
                .border(
                    width = 1.dp,
                    color = if (hasYourAttention) Theme.colorPink else Theme.colorLightGray,
                    shape = RoundedCornerShape(0.dp)
                )
                .padding(start = 12.dp, end = 12.dp)
                .shadow(elevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CardTitleAndSubtitleContent(title = title, subtitle = hint)

                Box(
                    modifier = Modifier
                        .background(Theme.colorLightOffwhite)
                        .size(64.dp)
                        .padding(end = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        icon,
                        contentDescription = null,
                        tint = if (hasYourAttention) Theme.colorDarkPink else Theme.colorDarkBlue,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

/*@Composable
fun ThoughtList(
    groups: List<ExerciseGroup>,
    historyButtonLabel: HistoryButtonLabelSetting,
    navigateToThoughtViewer: (SavedThought) -> Unit,
    navigateToCheckupViewer: (Checkup) -> Unit,
    navigateToPredictionViewer: (Prediction) -> Unit
) {
    if (groups.isEmpty()) {
        EmptyThoughtIllustration()
    } else {
        Column(
            modifier = Modifier
                .padding(top = Constants.statusBarHeight.dp)
                .padding(horizontal = 24.dp)
                .padding(bottom = 12.dp)
        ) {
            groups.forEach { group ->
                val isToday = LocalDate.parse(group.date).isEqual(LocalDate.now())
                ThoughtGroup(
                    date = group.date,
                    isToday = isToday,
                    exercises = group.exercises,
                    navigateToThoughtViewer = navigateToThoughtViewer,
                    navigateToCheckupViewer = navigateToCheckupViewer,
                    navigateToPredictionViewer = navigateToPredictionViewer,
                    historyButtonLabel = historyButtonLabel
                )
            }
        }
    }
}*/

/*@Composable
fun ThoughtGroup(
    date: String,
    isToday: Boolean,
    exercises: List<Exercise>,
    navigateToThoughtViewer: (SavedThought) -> Unit,
    navigateToCheckupViewer: (Checkup) -> Unit,
    navigateToPredictionViewer: (Prediction) -> Unit,
    historyButtonLabel: HistoryButtonLabelSetting
) {
    Column {
        Label(text = if (isToday) "Today" else LocalDate.parse(date).toString())
        exercises.sortedBy { it.createdAt }.forEach { exercise ->
            when (exercise) {
                is Checkup -> CheckUpCard(
                    currentCheckup = exercise,
                    onPress = { navigateToCheckupViewer(exercise) }
                )
                is Prediction -> PredictionCard(
                    prediction = exercise,
                    onPress = { navigateToPredictionViewer(exercise) }
                )
                is SavedThought -> ThoughtItem(
                    thought = exercise,
                    onPress = { navigateToThoughtViewer(exercise) },
                    historyButtonLabel = historyButtonLabel
                )
            }
        }
    }
}*/

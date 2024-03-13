package com.sprytm.meeqapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprytm.meeqapp.ui.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseButton(
    title: String,
    hint: String,
    icon: ImageVector,
    hasYourAttention: Boolean = false,
    onClick: () -> Unit
) {
    TouchableCardContainer(
        onClick = { onClick() },
        modifier = Modifier
            .border(
                width = 1.dp,
                color = if (hasYourAttention) Theme.colorPink else Theme.colorLightGray,
            )
        //.padding(bottom = 12.dp)
    ) {
        Row(
            modifier = Modifier.height(116.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                CardTitleAndSubtitleContent(title = title, subtitle = hint)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(
                        color = if (hasYourAttention) Theme.colorLightPink else Theme.colorLightOffwhite
                    )
                    .width(64.dp)
                    .fillMaxHeight()
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = if (hasYourAttention) Theme.colorDarkPink else Theme.colorDarkBlue,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseButtonPreview() {
    ExerciseButton(
        title = "Exercise Button",
        hint = "Manage anxiety around upcoming events or tasks.",
        icon = Icons.Default.Star,
        hasYourAttention = true,
        onClick = {}
    )
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

package com.spryteam.meeqapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spryteam.meeqapp.ui.theme.Theme




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

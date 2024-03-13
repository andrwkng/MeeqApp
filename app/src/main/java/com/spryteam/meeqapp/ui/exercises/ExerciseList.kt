package com.spryteam.meeqapp.ui.exercises

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spryteam.meeqapp.R

import com.spryteam.meeqapp.data.HistoryButtonLabelSetting
import com.spryteam.meeqapp.ui.checkup.CheckUpCard
import com.spryteam.meeqapp.ui.checkup.Checkup
import com.spryteam.meeqapp.ui.thoughts.Exercise
import com.spryteam.meeqapp.ui.thoughts.FollowUpState
import com.spryteam.meeqapp.ui.thoughts.SavedThought
import com.spryteam.meeqapp.ui.thoughts.ThoughtItem
import java.time.LocalDate


/*@Composable
fun XExerciseList(
    groups: List<ExerciseGroup>,
    historyButtonLabel: HistoryButtonLabelSetting,
    navigateToThoughtViewer: (thought: SavedThought) -> Unit,
    navigateToCheckupViewer: (checkup: Checkup) -> Unit,
) {
    if (groups.isEmpty()) {
        EmptyThoughtIllustration()
    } else {
        LazyColumn(
            modifier = Modifier
                .statusBarsPadding()
                .padding(16.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(groups) { group ->
                val isToday = SimpleDateFormat("yyyy-MM-dd").format(group.date) == SimpleDateFormat("yyyy-MM-dd").format(Date())

                item {
                    Surface {
                        Text(text = if (isToday) "Today" else SimpleDateFormat("EEE, MMM dd, yyyy").format(group.date))
                    }
                }

                group.exercises.sortedByDescending { it.uuid }.forEach { ex ->
                    when {
                        isCheckup(ex) -> {
                            CheckUpCard(
                                currentCheckup = ex,
                                onPress = { navigateToCheckupViewer(ex) },
                                key = ex.uuid
                            )
                        }
                        isThought(ex) -> {
                            ThoughtItem(thought = ex, onPress = { navigateToThoughtViewer(ex) }, historyButtonLabel = historyButtonLabel)
                        }
                    }
                }
            }

            items(exercises) { exercise ->
                ExerciseItem(exercise, props.navigateToExerciseViewer)
            }
        }
    }
}*/

enum class Mood {
    GOOD,
    NEUTRAL,
    BAD,
    UNSELECTED
}

@Composable
fun EmptyThoughtIllustration() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 36.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.looker), // Assuming you have the image in your resources
            contentDescription = null,
            modifier = Modifier
                .size(200.dp, 150.dp)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 32.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "No thoughts yet!",
            modifier = Modifier.padding(bottom = 18.dp),
            textAlign = TextAlign.Center
        )
    }
}

data class ExerciseGroup(
    val date: String,
    val exercises: MutableList<Exercise>
)

@Composable
fun ExerciseList(
    groups: List<ExerciseGroup>,
    navigateToThoughtViewer: (SavedThought) -> Unit,
    navigateToCheckupViewer: (Checkup) -> Unit,
    historyButtonLabel: HistoryButtonLabelSetting,
    followUpState: () -> FollowUpState,
) {
    if (groups.isEmpty()) {
        EmptyThoughtIllustration()
    } else {
        LazyColumn {
            items(groups) { group ->
                //val items = groups.map { group ->
                val exercises = group.exercises.sortedByDescending { it.createdAt }
                    .map { ex ->
                        when {
                            (ex is Checkup && ex.currentMood != null) -> {
                                //if(ex is Checkup && ex.currentMood != null) {
                                val checkup: Checkup = ex

                                CheckUpCard(
                                    key = ex.uuid,
                                    currentCheckup = checkup,
                                    onPress = { navigateToCheckupViewer(ex) }
                                )
                            }

                            (ex is SavedThought && ex.automaticThought != null) -> {
                                val thought: SavedThought = ex

                                ThoughtItem(
                                    thought = thought,
                                    historyButtonLabel = historyButtonLabel,
                                    followUpState = { followUpState() },
                                    onPress = { navigateToThoughtViewer(ex) },
                                    key = ex.uuid
                                )
                            }

                            else -> null // Handle other cases or provide a default value
                        }
                    }.filterNotNull() // Filter out null values if any

                val isToday = LocalDate.parse(group.date) == LocalDate.now()

                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = if (isToday) "Today" else group.date.toString(),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    exercises.forEach { exercise ->
                        exercise
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseListPreview(){
    /*ExerciseList(
        groups = emptyList(),
        navigateToThoughtViewer = {},
        navigateToCheckupViewer = {},
        historyButtonLabel = HistoryButtonLabelSetting.ALTERNATIVE_THOUGHT
    )*/
}

@Preview(showBackground = true)
@Composable
fun EmptyThoughtIllustrationPreview() {
    EmptyThoughtIllustration()
}
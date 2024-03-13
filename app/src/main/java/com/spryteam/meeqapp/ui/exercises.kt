package com.spryteam.meeqapp.ui




/*@Composable
fun ThoughtList(
    groups: List<ExerciseGroup>,
    historyButtonLabel: HistoryButtonLabelSetting,
    navigateToThoughtViewer: (SavedThought) -> Unit,
    navigateToCheckupViewer: (Checkup) -> Unit,
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
                is SavedThought -> ThoughtItem(
                    thought = exercise,
                    onPress = { navigateToThoughtViewer(exercise) },
                    historyButtonLabel = historyButtonLabel
                )
            }
        }
    }
}*/

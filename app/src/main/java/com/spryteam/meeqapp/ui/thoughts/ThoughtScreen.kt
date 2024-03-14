package com.spryteam.meeqapp.ui.thoughts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spryteam.meeqapp.ui.Feed
import com.spryteam.meeqapp.ui.components.ExerciseButton
import com.spryteam.meeqapp.ui.exercises.ExerciseGroup

@Composable
fun ThoughtScreen(
    screeningData: FormScreeningData,
    isNextDisabled: Boolean,
    onClosePressed: () -> Unit,
    onPreviousPressed: () -> Unit,
    onNextPressed: () -> Unit,
    onFinishPressed: () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    Surface {
        Scaffold(
            content = content,
        )
    }
}

@Composable
fun HomeScreen(
    groups: List<ExerciseGroup>,
    onNewAutoThought: () -> Unit,
    modifier: Modifier = Modifier,
    //Feed state
    navigateToThoughtViewer: () -> Unit,
    navigateToCheckup: () -> Unit,
    navigateToCheckupViewer: () -> Unit,
    shouldFadeIn: Boolean,
    shouldPromptCheckup: Boolean,
    followUpState: () -> FollowUpState,
    onLoad: () -> Unit
) {
    //val existingUser = viewModel.isExistingUser.collectAsState()

    /*LaunchedEffect(Unit) { // the key define when the block is relaunched
        if(!existingUser.value){
                viewModel.setIsExistingUser()
        }
    }*/

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ExerciseButtons(
            onNewAutoThoughtPressed = onNewAutoThought
        )
        Feed(
            groups = groups,
            navigateToThoughtViewer = navigateToThoughtViewer,
            navigateToCheckup = navigateToCheckup,
            navigateToCheckupViewer = navigateToCheckupViewer,
            shouldFadeIn = shouldFadeIn,
            shouldPromptCheckup = shouldPromptCheckup,
            followUpState = followUpState,
            onLoad = onLoad
        )
    }
}

@Composable
fun ExerciseButtons(
    onNewAutoThoughtPressed: () -> Unit
) {
//    val context = LocalContext.current
//    val flagStore = FlagStore(context)
//    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 24.dp)
            .padding(end = 12.dp, start = 12.dp)
    ) {

        ExerciseButton(
            title = "New Automatic Thought",
            hint = "Challenge your in-the-moment automatic negative thoughts.",
            onClick = onNewAutoThoughtPressed
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ThoughtScreenPreview() {
    /*ThoughtScreen(
        screeningData = ,
        isNextDisabled = ,
        onClosePressed = { *//*TODO*//* },
        onPreviousPressed = { *//*TODO*//* },
        onNextPressed = { *//*TODO*//* },
        onFinishPressed = { *//*TODO*//* }) {

    }*/
}

@Preview(showBackground = true)
@Composable
fun ExerciseButtonsPreview() {
    ExerciseButtons(onNewAutoThoughtPressed = { /*TODO*/ })
}
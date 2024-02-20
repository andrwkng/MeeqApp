package com.example.meeqapp.ui.predictions
/*

@Composable
fun PredictionThoughtRedirectScreen(
    screenProps: ScreenProps,
    onThoughtScreenNavigation: (Thought) -> Unit
) {
    var prediction by remember { mutableStateOf<Prediction?>(null) }

    DisposableEffect(Unit) {
        onDispose {
            // Cleanup logic (if needed) when the composable is removed from the composition
        }
    }

    LaunchedEffect(screenProps.navigation) {
        val predictionArg = screenProps.navigation.currentBackStackEntry
            ?.arguments?.getParcelable<Prediction>("prediction")

        prediction = predictionArg
    }

    if (prediction == null) {
        return // Placeholder for null prediction state
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = with(LocalDensity.current) { Constants.StatusBarHeight.toDp() } + 24.dp,
                start = 24.dp,
                end = 24.dp
            ),
        backgroundColor = Color.Gray // Change to desired background color
    ) {
        Text(
            text = "You should challenge this thought.",
            style = MaterialTheme.typography.h4
        )
        Text(
            text = "Challenging the thought can help you overcome anxiety around this event or task.",
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val thought = newThought()
                thought.automaticThought =
                    "${prediction?.eventLabel} - ${prediction?.predictedExperienceNote.orEmpty()}"
                onThoughtScreenNavigation(thought)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Okay, let's challenge it")
        }

        Spacer(modifier = Modifier.height(6.dp))

        Button(
            onClick = {
                onThoughtScreenNavigation(newThought())
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "No thanks")
        }
    }
}
*/

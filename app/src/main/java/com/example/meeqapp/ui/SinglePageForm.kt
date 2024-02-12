package com.example.meeqapp.ui

/*@Composable
fun SinglePageForm(steps: List<@Composable (Modifier) -> Unit>, index: Int) {
    var pose by remember { mutableStateOf("visible") }

    FadesIn(pose = pose) {
        steps[index](Modifier.fillMaxSize())
    }
}*/

/*@Composable
fun FadesIn(pose: String, content: @Composable () -> Unit) {
    var hidden by remember { mutableStateOf(true) }

    if (pose == "visible" && hidden) {
        LaunchedEffect(pose) {
            hidden = false
        }
    }

    Backstack(pose) {
        // Use whatever animation you want for the fade-in effect
        slideOut('F', animation = tween(500)) {
            // Change the alpha over time
            var alpha by animateFloatAsState(targetValue = if (hidden) 0f else 1f)

            // If the component is hidden, set its alpha to 0
            if (hidden) alpha = 0f

            // Draw the content with the calculated alpha
            Surface(
                modifier = Modifier.fillMaxSize().then(Modifier.alpha(alpha)),
                color = Color.Black
            ) {
                content()
            }
        }
    }
}*/

// Preview the component
/*@Preview
@Composable
fun SinglePageFormPreview() {
    val steps = listOf(
        // Define your composables for each step here
    )
    SinglePageForm(steps, index = 0)
}*/

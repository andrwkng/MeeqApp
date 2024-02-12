package com.example.meeqapp.ui.onboarding
/*

@Composable
fun PredictionPromptScreen(navController: NavController) {
    var type by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(top = 24.dp + statusBarHeight())
            .padding(horizontal = 24.dp)
            .fillMaxSize()
            .background(theme.lightOffwhite)
    ) {
        MediumHeader(text = "Let's make a prediction.")

        if (type != "procrastination") {
            Paragraph(
                text = """
                    When you're anxious about the future, you're Fortune Telling.
                    People are generally quite bad at telling the future, but it's
                    hard to understand that in the moment. So instead, you'll write
                    down how you think something will go, and Quirk will follow up
                    with you later to see if you were correct.
                """.trimIndent(),
                modifier = Modifier
                    .padding(bottom = 6.dp)
            )

            Paragraph(
                text = """
                    In the meantime, Quirk will run you through one of the most common
                    CBT exercises to help you with your immediate anxiety.
                """.trimIndent(),
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }

        if (type == "procrastination") {
            Paragraph(
                text = """
                    When you're putting off a healthy behavior like studying for a
                    test, seeing friends, or exercising, it's often due to a distorted
                    prediction of what that healthy behavior will actually be like.
                    You might think it will be boring, or stressful, or maybe just not
                    very fun. This could be true, but it's not necessarily true. You
                    might find you enjoyed yourself or that you feel quite productive
                    afterwards.
                """.trimIndent(),
                modifier = Modifier.padding(bottom = 6.dp)
            )

            Paragraph(
                text = """
                    To overcome this type of procrastination, you'll make a prediction
                    about how you think your task will go. Then, we'll walk through a
                    common CBT exercise to challenge the validity of your anxiety.
                    Then, you'll go out and do the task. Finally, we'll follow up to
                    check if your prediction was accurate.
                """.trimIndent(),
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }

        SubHeader(text = "In the next screen, you'll put a future event you're anxious about like:")

        Card(
            modifier = Modifier
                .padding(12.dp)
                .background(theme.offwhite)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(text = if (type.isNotEmpty()) EXAMPLES[type] ?: "" else "")
        }

        SubHeader(text = "As a quick summary, we'll be:")

        ListItem(index = "1", text = "\"Predicting\" how we think the future will go.")
        ListItem(index = "2", text = "Challenging the validity of that prediction through a CBT exercise.")
        ListItem(index = "3", text = "Following up later to check if our prediction was correct.")

        ActionButton(
            onClick = {
                navController.navigate(ASSUMPTION_SCREEN)
            },
            title = "Start First Prediction",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 48.dp)
        )
    }
}

// Utility function to get the status bar height
@Composable
fun statusBarHeight(): Dp {
    val context = LocalContext.current
    val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId > 0) {
        context.resources.getDimensionPixelSize(resourceId).dp
    } else {
        0.dp
    }
}
*/

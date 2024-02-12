package com.example.meeqapp.ui.predictions

/*
@Composable
fun ScheduleFollowUpScreen(onFinish: () -> Unit) {
    val (followUpOn, setFollowUpOn) = remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp + statusBarHeight(), horizontal = 24.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        StatusBar(hidden = false)
        KeyboardAvoidingView(
            behavior = KeyboardAvoidingViewBehavior.Position,
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            MediumHeader(text = "Schedule Follow Up")
            HintHeader(text = "We'll follow up in the future to see if your prediction came true.")

            RoundedSelectorButton(
                title = "+3 hours from now",
                onClick = { setFollowUpOn("+3 hours") },
                selected = followUpOn == "+3 hours"
            )
            RoundedSelectorButton(
                title = "Tomorrow",
                onClick = { setFollowUpOn("+1 day") },
                selected = followUpOn == "+1 day"
            )
            RoundedSelectorButton(
                title = "+5 days from now",
                onClick = { setFollowUpOn("+5 days") },
                selected = followUpOn == "+5 days"
            )
            RoundedSelectorButton(
                title = "+30 days from now",
                onClick = { setFollowUpOn("+30 days") },
                selected = followUpOn == "+30 days"
            )

            ActionButton(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                title = "Continue",
                onClick = { onFinish() }
            )
        }
    }
}
*/

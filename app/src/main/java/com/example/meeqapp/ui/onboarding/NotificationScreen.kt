package com.example.meeqapp.ui.onboarding
/*

@Composable
fun NotificationScreen() {
    val slugs by remember { mutableStateOf(emptyList<String>()) }

    Column(
        modifier = Modifier
            .padding(top = 24.dp + statusBarHeight())
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        ScrollView {
            Image(
                painter = painterResource(id = R.drawable.notifications),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(196.dp)
                    .padding(bottom = 48.dp),
                contentScale = ContentScale.Fit
            )

            MediumHeader(
                text = "Quirk comes with a follow-up system designed to get you out of particularly bad periods.",
                modifier = Modifier.padding(bottom = 8.dp)
            )

            HintHeader(
                text = "For this feature to work, you'll need to turn notifications on; but you can turn them off within the app if you'd like.",
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                ActionButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    title = "Continue with notifications",
                    onClick = {
                        userTurnedOnNotifications()
                        if (Platform.OS === "ios") {
                            // Add iOS specific logic here
                        }
                        OneSignal.setSubscription(true)
                        onContinue()
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                ActionButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    title = "Turn off a key feature",
                    fillColor = Color(0xFFEDF0FC),
                    textColor = MaterialTheme.colorScheme.primary,
                    onClick = { onContinue() }
                )
            }
        }
    }
}
*/

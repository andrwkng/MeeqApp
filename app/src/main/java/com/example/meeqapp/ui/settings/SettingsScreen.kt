package com.example.meeqapp.ui.settings

/*

@Composable
fun SettingScreen() {
    var isReady by remember { mutableStateOf(false) }
    var areNotificationsOn by remember { mutableStateOf(false) }
    var shouldShowRemovePincode by remember { mutableStateOf(false) }

    if (isReady) {
        FadesIn(
            modifier = Modifier.background(Color.LightGray),
            visible = isReady
        ) {
            ScrollView(
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(top = Constants.statusBarHeight.dp + 24.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Container(
                    modifier = Modifier.padding(bottom = 128.dp)
                ) {
                    // Your content goes here

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 22.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        // SubHeader and Paragraph for reminders

                        RoundedSelectorButton(
                            title = "Please remind me",
                            selected = areNotificationsOn,
                            onClick = {
                                if (Platform.OS === "ios") {
                                    // Handle iOS specific logic
                                }
                                OneSignal.setSubscription(true)
                                areNotificationsOn = true
                                stats.userTurnedOnNotifications()
                            }
                        )

                        RoundedSelectorButton(
                            title = "No reminders, thanks",
                            selected = !areNotificationsOn,
                            onClick = {
                                OneSignal.setSubscription(false)
                                areNotificationsOn = false
                                stats.userTurnedOffNotifications()
                            }
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 22.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        // SubHeader and Paragraph for pincode lock

                        ActionButton(
                            title = if (shouldShowRemovePincode) "Reset Pincode" else "Set Pincode",
                            width = Modifier.fillMaxWidth(),
                            fillColor = Color(0xFFEDF0FC),
                            textColor = theme.darkBlue,
                            onClick = {
                                // Handle navigation to lock screen
                                shouldShowRemovePincode = true
                            }
                        )

                        if (shouldShowRemovePincode) {
                            ActionButton(
                                title = "Remove Pincode",
                                width = Modifier.fillMaxWidth(),
                                fillColor = Color(0xFFEDF0FC),
                                textColor = theme.darkBlue,
                                onClick = {
                                    // Handle remove pincode logic
                                    shouldShowRemovePincode = false
                                },
                                modifier = Modifier.padding(top = 6.dp)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 22.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        // SubHeader and Paragraph for feedback

                        Feedback()
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 22.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        // SubHeader and content for subscription
                        SubHeader(text = "*subscription")
                        if (isGrandfatheredIntoSubscription) {
                            GrandfatheredInFreeQuirk()
                        } else {
                            SubscriptionExpirationDate(
                                expirationDate = subscriptionExpirationDate
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Feedback() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        SubHeader("*feedback")
        Paragraph(
            modifier = Modifier
                .padding(bottom = 16.dp),
            text = "We take your feedback extremely seriously. The email below goes directly to the creators of Quirk."
        )
        ActionButton(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 0.dp, color = Color.Transparent)
                .border(bottom = 0.dp, color = Color.Transparent),
            title = "Email Feedback",
            fillColor = Color(0xFFEDF0FC),
            textColor = Color.DarkBlue,
            onClick = {
                val uri = Uri.parse("mailto:humans@quirk.fyi?subject=${Uri.encode("Quirk Feedback")}")
                Intent(Intent.ACTION_SENDTO, uri).also {
                    startActivity(it)
                }
            }
        )
    }
}

@Composable
fun CancelationInstructions() {
    ActionButton(
        flex = 1,
        title = "Cancellation Instructions",
        fillColor = Color(0xFFEDF0FC),
        textColor = theme.darkBlue,
        style = {
            borderWidth = 0.dp
            borderBottomWidth = 0.dp
        },
        onPress = {
            val url = if (Platform.OS == "android") {
                "https://support.google.com/googleplay/answer/7018481"
            } else {
                "https://support.apple.com/en-us/HT202039"
            }
            openUrl(url)
        }
    )
}

@Composable
fun openUrl(url: String) {
    // Implement the logic to open the URL in the browser or a web view.
    // You can use the appropriate Compose navigation or external library for this.
    // The actual implementation might depend on your navigation setup.
}

@Composable
fun GrandfatheredInFreeQuirk() {
    Column(
        modifier = Modifier
            .padding(bottom = 49.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "You've been given Quirk for free! 🙌",
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(bottom = 4.dp)
        )
        Text(
            text = "This will go away if you uninstall the app. Feel free to reach out by " +
                    "email (ejc@quirk.fyi) if you get a new phone; we'll work " +
                    "something out. Thanks for being an early supporter! ❤️"
        )
    }
}

@Composable
fun SubscriptionExpirationDate(expirationDate: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Thanks for supporting message
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Paragraph(
                text = "Thanks for supporting the development of Quirk!",
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )
        }

        // Subscription renewal date message
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Paragraph(
                text = "You're currently subscribed. On ${dayjs(expirationDate).format("YYYY-MM-DD")} your subscription will renew.",
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )
        }

        // Cancellation instructions
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            CancelationInstructions()
        }
    }
}
*/

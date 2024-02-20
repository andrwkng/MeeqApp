package com.example.meeqapp.ui

/*@Composable
fun AlternativeScreen(*//*navController: NavController*//*) {
    var thought by remember { mutableStateOf<Thought?>(null) }
    var isEditing by remember { mutableStateOf(false) }

    //val haptic = LocalHapticFeedback.current

    fun onNext() {
        *//*haptic.impact(HapticFeedbackType.Light)
        thought?.let {
            navController.navigate(FEELING_SCREEN + "/${it.id}")
        }*//*
    }

    fun onFinish() {
        *//*haptic.impact(HapticFeedbackType.Light)
        thought?.let {
            navController.navigate(FINISHED_SCREEN + "/${it.id}")
        }*//*
    }

    val i18n = LocalI18n.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        thought?.let {
            MediumHeader(text = i18n.t("alt_thought"))
            HintHeader(
                text = "Given this situation again, what could you think instead?"
            )

            var alternativeThought by remember { mutableStateOf(it.alternativeThought) }

            BasicTextField(
                value = alternativeThought,
                onValueChange = {
                    alternativeThought = it
                },
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp),
                maxLines = 6,
                placeholder = { Text(i18n.t("cbt_form.alt_thought_placeholder")) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Handle Done button click
                        stats.userFilledOutFormField("alternative")
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    placeholderColor = textInputPlaceholderColor.toColor(),
                    backgroundColor = Color.Transparent,
                    cursorColor = theme.lightGray
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.End
            ) {
                if (isEditing) {
                    ActionButton(
                        title = "Save",
                        onClick = { onFinish() },
                        modifier = Modifier.widthIn(max = LocalConfiguration.current.screenWidthDp.dp)
                    )
                } else {
                    GhostButton(
                        borderColor = theme.lightGray,
                        textColor = theme.veryLightText,
                        title = "Back",
                        modifier = Modifier.weight(1f),
                        onClick = {
                            navController.navigate("${CHALLENGE_SCREEN}/${thought?.id}")
                        }
                    )
                    ActionButton(
                        title = "Next",
                        onClick = { onNext() },
                        modifier = Modifier.widthIn(max = LocalConfiguration.current.screenWidthDp.dp)
                    )
                }
            }
        }
    }
}*/

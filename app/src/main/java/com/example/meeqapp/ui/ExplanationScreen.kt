package com.example.meeqapp.ui

/*
@Composable
fun ExplanationScreen(
    onboardingScreenNavigation: () -> Unit
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        recordScreenCallOnFocus("help")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { */
/* Handle navigation icon click *//*
 }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, top = 24.dp, end = 24.dp)
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(Constants.statusBarHeight + 24.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Constants.statusBarHeight + 24.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (isDebug) {
                            GhostButton(
                                title = "Intro",
                                width = 80.dp,
                                height = 48.dp,
                                borderColor = Theme.lightGray,
                                textColor = Theme.veryLightText,
                                onClick = onboardingScreenNavigation
                            )
                        }
                    }

                    AllOrNothingThinking()
                    Catastrophizing()
                    EmotionalReasoning()
                    FortuneTelling()
                    //Labeling()
                    MagnificationOfTheNegative()
                    MindReading()
                    MinimizationOfThePositive()
                    OtherBlaming()
                    OverGeneralization()
                    SelfBlaming()
                    ShouldStatements()
                }
            }
        }
    )
}

@Composable
fun Distortion(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .padding(bottom = 48.dp)
    ) {
        content()
    }
}


@Composable
fun AllOrNothingThinking() {
    Distortion {
        SubHeader(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 18.sp)) {
                    append(i18n.t("all_or_nothing_thinking"))
                }
                append(" 🌓")
            }
        )

        Paragraph(text = i18n.t("all_or_nothing_thinking_explanation"))

        BubbleThought(text = i18n.t("all_or_nothing_thinking_thought"))
    }
}


@Composable
fun Catastrophizing() {
    Distortion {
        SubHeader {
            Text("${stringResource(R.string.catastrophizing)} 🤯")
        }

        Paragraph(text = stringResource(R.string.catastrophizing_explanation))

        BubbleThought(color = Color.Purple) {
            Text(stringResource(R.string.catastrophizing_thought))
        }
    }
}


@Composable
fun EmotionalReasoning() {
    Distortion {
        SubHeader(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(i18n.t("emotional_reasoning"))
                }
                append(" 🎭")
            }
        )

        Paragraph(text = i18n.t("emotional_reasoning_explanation_1") + "\n")

        Paragraph(text = i18n.t("emotional_reasoning_explanation_2"))

        BubbleThought(
            color = Color.Pink,
            text = i18n.t("emotional_reasoning_thought")
        )
    }
}


@Composable
fun FortuneTelling() {
    Distortion {
        Column {
            SubHeader(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(i18n.t("fortune_telling"))
                    }
                    append(" 🔮")
                }
            )

            Paragraph(text = i18n.t("fortune_telling_explanation"))

            BubbleThought(color = Color.Purple) {
                Text(text = i18n.t("fortune_telling_thought"))
            }
        }
    }
}


@Composable
fun MagnificationOfTheNegative() {
    Distortion {
        SubHeader(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 24.sp)) {
                    append(i18n.t("magnification_of_the_negative"))
                }
                append(" ")
                withStyle(style = SpanStyle(fontSize = 24.sp, emoji = "👎")) {
                    append(" ")
                }
            }
        )

        Paragraph(text = i18n.t("magnification_of_the_negative_explanation"))

        BubbleThought(text = i18n.t("magnification_of_the_negative_thought"))
    }
}


@Composable
fun MindReading() {
    Distortion {
        SubHeader(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 20.sp)) {
                    append(i18n.t("mind_reading"))
                }
                append(" 🧠")
            }
        )

        Paragraph(text = i18n.t("mind_reading_explanation"))

        BubbleThought(
            color = Color.Pink,
            text = i18n.t("mind_reading_thought")
        )
    }
}


@Composable
fun MinimizationOfThePositive() {
    Distortion {
        SubHeader {
            Text(text = "${stringResource(id = R.string.minimization_of_the_positive)} 👍")
        }

        Paragraph(text = stringResource(id = R.string.minimization_of_the_positive_explanation))

        BubbleThought(text = stringResource(id = R.string.minimization_of_the_positive_thought))
    }
}

@Composable
fun OtherBlaming() {
    Distortion {
        SubHeader(text = "${stringResource(R.string.other_blaming)} 🧛‍")

        Paragraph(text = "${stringResource(R.string.other_blaming_explanation_1)}\n")

        Paragraph(text = stringResource(R.string.other_blaming_explanation_2))

        BubbleThought(color = Color.Purple) {
            Text(text = stringResource(R.string.other_blaming_thought))
        }
    }
}


@Composable
fun OverGeneralization() {
    Distortion {
        SubHeader {
            Text(text = "${stringResource(id = R.string.over_generalization)} 👯‍")
        }

        Paragraph(text = stringResource(id = R.string.over_generalization_explanation))

        BubbleThought(text = stringResource(id = R.string.over_generalization_thought))
    }
}

@Composable
fun SelfBlaming() {
    Distortion {
        SubHeader {
            Text("${i18n.t("self_blaming")}👁")
        }

        Paragraph(text = i18n.t("self_blaming_explanation"))

        BubbleThought(color = Color.Pink) {
            Text(i18n.t("self_blaming_thought"))
        }
    }
}


@Composable
fun ShouldStatements() {
    Distortion {
        SubHeader(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 24.sp)) {
                    append(i18n.t("should_statements"))
                }
                append(" ✨")
            }
        )

        Paragraph(text = i18n.t("should_statements_explanation_1"))

        Paragraph(text = i18n.t("should_statements_explanation_2"))

        BubbleThought(text = i18n.t("should_statements_thought"))
    }
}
*/

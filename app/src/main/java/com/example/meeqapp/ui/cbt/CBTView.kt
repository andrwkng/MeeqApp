package com.example.meeqapp.ui.cbt

/*
@Composable
fun ThoughtComponent(thought: Thought) {
    Column {
        FormContainer {
            SubHeader(text = stringResource(id = R.string.auto_thought))
            if (!thought.automaticThought.isNullOrBlank()) {
                BubbleThought(
                    text = thought.automaticThought,
                    modifier = Modifier
                        .padding(top = 0.dp)
                )
            } else {
                Paragraph(text = "🤷‍")
            }
        }

        FormContainer {
            SubHeader(text = stringResource(id = R.string.cog_distortion))
            CognitiveDistortionsToText(thought.cognitiveDistortions)
        }

        FormContainer {
            SubHeader(text = stringResource(id = R.string.challenge))
            Paragraph(text = thought.challenge ?: "🤷‍")
        }

        FormContainer {
            SubHeader(text = stringResource(id = R.string.alt_thought))
            if (!thought.alternativeThought.isNullOrBlank()) {
                BubbleThought(
                    text = thought.alternativeThought,
                    modifier = Modifier
                        .padding(top = 0.dp),
                    color = Color.Pink // Assuming there's a Color.Pink in your theme
                )
            } else {
                Paragraph(text = "🤷‍")
            }
        }
    }
}
*/

/*

@Composable
fun CognitiveDistortionsToText(cognitiveDistortions: List<CognitiveDistortion>) {
    val selectedDistortions = cognitiveDistortions.filter { it.selected }

    if (selectedDistortions.isEmpty()) {
        Paragraph(text = "🤷‍")
    } else {
        selectedDistortions.forEach { distortion ->
            Paragraph(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle().copy(
                        marginBottom = 8.sp
                    )) {
                        append(emojiForSlug(distortion.slug))
                        append(" ${distortion.label}")
                    }
                }
            )
        }
    }
}
*/

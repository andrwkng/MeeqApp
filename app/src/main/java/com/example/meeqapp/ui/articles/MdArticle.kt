package com.example.meeqapp.ui.articles

//import com.example.meeqapp.TitleTopBar

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarkdownArticle(
    pages: Any,
    title: Any,
    description: Any,
    onFinish: () -> Unit,
    onExit: () -> Unit,
    shouldHideExitButton: Any
) {


            var index by remember { mutableStateOf(-1) }
            var rightFlasherPose by remember { mutableStateOf("hidden") }
            var articleTopBarPose by remember { mutableStateOf("hidden") }

            Scaffold {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    // index == -1 is the title page
                    if (index == -1) {
                        TitleTopBar(
                            onExit = { onExit() },
                            shouldShowExitButton = !shouldHideExitButton
                        )
                    } else {
                        ArticleTopBar(
                            onExit = { onExit() },
                            progress = progress,
                            pose = articleTopBarPose,
                            shouldShowExitButton = !shouldHideExitButton
                        )
                    }

                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxHeight()
                    ) {
                        renderPage()
                    }
                }

                // Right Flasher
                ClickableIcon(
                    onClick = { onNext() },
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(18.percent)
                        .background(MaterialTheme.colorScheme.background),
                    icon = {
                        CircleFlasher(pose = rightFlasherPose)
                    }
                )

                // Left Clickable Area
                ClickableIcon(
                    onClick = { onBack() },
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(18.percent)
                        .background(MaterialTheme.colorScheme.background)
                )
            }
*/


/*
    private fun renderPage() {
        if (index == -1) {
            TitlePage(title = title, description = description)
        } else {
            MarkdownText(text = pages[index].trim())
        }
    }

    private fun onNext() {
        haptic.impact(Haptic.ImpactFeedbackStyle.Light)

        if (index == -1) {
            // Title screen => Article Screen
            setTimeout({ articleTopBarPose = "visible" }, 100)
        }

        if (index >= pages.size - 1) {
            // Article screen => Finish
            haptic.notification(Haptic.NotificationFeedbackType.Success)
            onFinish()
            return
        }

        rightFlasherPose = "hidden"
        index += 1
        clearInterval(rightFlicker)
    }

    private fun onBack() {
        haptic.impact(Haptic.ImpactFeedbackStyle.Light)

        if (index == 0) {
            rightFlicker = setInterval({ flickerRightFlasher() }, 800)
        }

        if (index - 1 < -1) {
            return
        }

        if (index - 1 == -1) {
            articleTopBarPose = "hidden"
        }

        index -= 1
    }

    private fun onExit() {
        haptic.impact(Haptic.ImpactFeedbackStyle.Medium)
        onExit.invoke()
    }

    override fun onDestroy() {
        super.onDestroy()
        clearInterval(rightFlicker)
    }
}
*/
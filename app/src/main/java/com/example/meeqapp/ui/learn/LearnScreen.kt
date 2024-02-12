package com.example.meeqapp.ui.learn

/*@Composable
fun ArticleWithImageCard(
    content: Content,
    onPress: (Content) -> Unit
) {
    TouchableCardContainer(onPress = { onPress(content) }) {
        Column {
            CardCrown(text = "ARTICLE", featherIconName = "circle", color = theme.blue)
            Divider(
                color = theme.lightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(top = 4.dp)
            )
            HowToQuirkImage()
            CardTitleAndSubtitleContent(
                title = content.title,
                subtitle = content.description
            )
        }
    }
}*/

/*@Composable
fun ArticleCard(
    content: Content,
    onPress: (Content) -> Unit
) {
    TouchableCardContainer(onPress = { onPress(content) }) {
        CardCrown(text = "ARTICLE", featherIconName = "circle", color = theme.blue)
        CardTitleAndSubtitleContent(
            title = content.title,
            subtitle = content.description
        )
    }
}*/

/*@Composable
fun LearnScreen(screenProps: ScreenProps) {
    var selectedContent by remember { mutableStateOf<Content?>(null) }

    fun openContent(content: Content, nextScreen: String? = null) {
        selectedContent = content
        // Handle navigation to the next screen if needed
    }

    Column(
        modifier = Modifier
            .padding(top = statusBarHeight + 24.dp, horizontal = 24.dp)
            .background(color = theme.lightOffwhite)
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 128.dp)
        ) {
            MediumHeader(
                modifier = Modifier
                    .padding(bottom = 24.dp)
            ) {
                Text("To Read")
            }

            SubHeader {
                Text("Start Here")
            }
            ArticleWithImageCard(content = cbt101) { openContent(it) }
            ArticleCard(content = howToUseQuirk) { openContent(it) }

            SubHeader {
                Text("Cognitive Distortions")
            }
            ArticleCard(content = allOrNothing) { openContent(it) }
            ArticleCard(content = catastrophizing) { openContent(it) }
            ArticleCard(content = emotionalReasoning) { openContent(it) }
            ArticleCard(content = fortuneTelling) { openContent(it) }
            ArticleCard(content = labeling) { openContent(it) }
            ArticleCard(content = magnification) { openContent(it) }
            ArticleCard(content = mindReading) { openContent(it) }
            ArticleCard(content = minimization) { openContent(it) }
            ArticleCard(content = otherBlaming) { openContent(it) }
            ArticleCard(content = overgeneralization) { openContent(it) }
            ArticleCard(content = predictions) { openContent(it) }
            ArticleCard(content = selfBlaming) { openContent(it) }
            ArticleCard(content = shouldStatements) { openContent(it) }
        }
    }
}*/

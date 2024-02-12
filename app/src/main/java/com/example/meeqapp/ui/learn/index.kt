package com.example.meeqapp.ui.learn

/*@Composable
fun Article(
    title: String,
    subheader: String,
    heroImage: @Composable () -> Unit,
    url: String,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .padding(top = 24.dp, paddingValues = PaddingValues(0.dp))
    ) {
        SubHeader(text = title)
        HintHeader(text = subheader)
        Box(
            modifier = Modifier.clickable {
                stats.userReadArticle(title)
                navController.navigate(ArticleScreen.routeWithUrl(url))
            }
        ) {
            heroImage()
        }
    }
}*/

/*

@Composable
fun IndexLearnScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    ScrollView(
        modifier = Modifier
            .padding(top = statusBarsHeight() + 24.dp)
            //.background(MaterialTheme.colorScheme.background)
            .padding(bottom = (TabRowDefaults.Height * 2) + 24.dp)
            .scrollState(scrollState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            MediumHeader(text = "Read This First")
            HintHeader(
                text = "Learn the basics of CBT and how to use Quirk.",
                modifier = Modifier
                    .margin(bottom = 0.dp)
            )
            Article(
                title = "How to use Quirk",
                subheader = "8 min read",
                heroImage = { HowToQuirkImage() },
                url = "https://embedded.quirk.fyi/how-to-quirk",
                navController = navController
            )

            Spacer(modifier = Modifier.height(48.dp))

            MediumHeader(text = "Cognitive Distortions")
            HintHeader(
                text = "Learn the most common ways your thoughts can be distorted.",
                modifier = Modifier.margin(bottom = 0.dp)
            )

            val cognitiveDistortions = listOf(
                "All or Nothing Thinking",
                "Catastrophizing",
                "Emotional Reasoning",
                "Fortune Telling",
                "Labeling",
                "Magnification of the Negative",
                "Mind Reading",
                "Minimization of the Positive",
                "Other Blaming",
                "Overgeneralization",
                "Self Blaming",
                "Should Statements"
            )

            cognitiveDistortions.forEach { distortion ->
                Article(
                    title = distortion,
                    subheader = "1 min read",
                    heroImage = { distortion.getDistortionImage() },
                    url = "https://embedded.quirk.fyi/${distortion.toLowerCase().replace(" ", "-")}",
                    navController = navController
                )
            }
        }
    }
}
*/

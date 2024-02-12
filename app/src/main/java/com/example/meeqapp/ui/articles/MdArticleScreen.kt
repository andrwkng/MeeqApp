package com.example.meeqapp.ui.articles


/*@Composable
fun MarkdownArticleScreen(
    navController: NavController,
    screenParams: MarkdownArticleScreenParams
) {
    var isReady by remember { mutableStateOf(false) }
    var shouldFadeIn by remember { mutableStateOf(false) }

    var pages by remember { mutableStateOf(emptyList<String>()) }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var nextScreen by remember { mutableStateOf(INDEX_LEARN_SCREEN) }
    var shouldHideExitButton by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        onDispose {
            navController.removeBackStackEntryListener(backStackEntryListener)
            navController.addOnDestinationChangedListener(destinationChangeListener)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            Handler(Looper.getMainLooper()).postDelayed({
                shouldFadeIn = true
            }, 100)
        }
    }

    val backStackEntryListener = NavController.OnBackStackEntryListener {
        isReady = false
    }

    val destinationChangeListener = NavController.OnDestinationChangedListener { _, _, _ ->
        isReady = false
    }

    DisposableEffect(navController) {
        onDispose {
            navController.addOnDestinationChangedListener(destinationChangeListener)
        }
    }

    LaunchedEffect(screenParams) {
        isReady = true
        pages = screenParams.pages
        title = screenParams.title
        description = screenParams.description
        nextScreen = screenParams.nextScreen
        shouldHideExitButton = screenParams.shouldHideExitButton
    }

    MarkdownArticle(
        pages = pages,
        title = title,
        description = description,
        onFinish = { onFinish(navController) },
        onExit = { onFinish(navController) },
        shouldHideExitButton = shouldHideExitButton
    )
}*/
/*
@Composable
private fun onFinish(navController: NavController) {
    navController.navigate(route = nextScreen)
}
*/

package com.example.meeqapp.ui.articles

/*
class ArticleScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FeatherAndroidTasksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArticleScreenContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreenContent() {
    val state = rememberArticleScreenState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Back",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            //backgroundColor = MaterialTheme.colorScheme.primary,
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            },
            actions = {
                // Add any additional actions here
            }
        )

        ArticleWebView(url = state.url, onLoadComplete = { state.isLoading = false })

        if (state.isLoading) {
            LoadingIndicator()
        }
    }
}

@Composable
fun ArticleWebView(url: String, onLoadComplete: () -> Unit) {
    // Assume that a Compose WebView implementation is used
    // You can replace this with an actual Compose WebView implementation
    // and handle loading completion accordingly
    WebView(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        onProgressChanged = { newProgress ->
            if (newProgress == 100) {
                onLoadComplete()
            }
        },
        initialUrl = url
    )
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun rememberArticleScreenState(): ArticleScreenState {
    val url = "https://example.com" // Initialize with the desired URL
    val isLoading = true
    return ArticleScreenState(url, isLoading)
}

data class ArticleScreenState(
    val url: String,
    var isLoading: Boolean
)

@Composable
fun ArticleScreenSample(navController: NavController, url: String) {
    var isLoading by remember { mutableStateOf(true) }

    DisposableEffect(navController) {
        val willFocusListener = NavController.OnDestinationChangedListener { _, _, _ ->
            // Handle willFocus event
            val newUrl = navController.currentBackStackEntry?.arguments?.getString("url")
            if (!newUrl.isNullOrBlank()) {
                url = newUrl
            }
        }

        val didFocusListener = NavController.OnDestinationChangedListener { _, _, _ ->
            // Handle didFocus event
            isLoading = true
        }

        navController.addOnDestinationChangedListener(willFocusListener)
        navController.addOnDestinationChangedListener(didFocusListener)

        onDispose {
            navController.removeOnDestinationChangedListener(willFocusListener)
            navController.removeOnDestinationChangedListener(didFocusListener)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = statusBarHeight() + 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GhostButton(
                title = "Back",
                borderColor = theme.lightGray,
                textColor = theme.lightText,
                onPress = {
                    navController.navigate(INDEX_LEARN_SCREEN)
                }
            )
        }

        WebView(
            onLoadingFinished = {
                isLoading = false
            },
            modifier = Modifier
                .flex(1f)
                .padding(horizontal = 24.dp),
            javaScriptEnabled = false,
            url = url
        )

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
*/

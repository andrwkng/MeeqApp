
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sprytm.meeqapp.ui.ALTERNATIVE_SCREEN
import com.sprytm.meeqapp.ui.AUTOMATIC_THOUGHT_SCREEN
import com.sprytm.meeqapp.ui.CHALLENGE_SCREEN
import com.sprytm.meeqapp.ui.DISTORTION_SCREEN
import com.sprytm.meeqapp.ui.FEELING_SCREEN
import com.sprytm.meeqapp.ui.FINISHED_SCREEN
import com.sprytm.meeqapp.ui.FOLLOW_UP_REQUEST_SCREEN
import com.sprytm.meeqapp.ui.FeelingRoute
import com.sprytm.meeqapp.ui.FinishedRoute
import com.sprytm.meeqapp.ui.MAIN_SCREEN
import com.sprytm.meeqapp.ui.MainScreen
import com.sprytm.meeqapp.ui.SCREENING_ROUTE
import com.sprytm.meeqapp.ui.THOUGHT_SCREEN
import com.sprytm.meeqapp.ui.distortions.DistortionRoute
import com.sprytm.meeqapp.ui.followup.FollowUpRoute
import com.sprytm.meeqapp.ui.screening.ScreeningRoute
import com.sprytm.meeqapp.ui.thoughts.AlternativeThoughtRoute
import com.sprytm.meeqapp.ui.thoughts.AutomaticThoughtRoute
import com.sprytm.meeqapp.ui.thoughts.ChallengeRoute
import com.sprytm.meeqapp.ui.thoughts.ThoughtRoute
import com.sprytm.meeqapp.ui.thoughts.ThoughtViewModel
import com.sprytm.meeqapp.ui.viewmodel.SharedViewModel

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MAIN_SCREEN,
    thoughtViewModel: ThoughtViewModel = hiltViewModel(),
    viewModel: SharedViewModel = viewModel()
) {
    val navigateToFinished = { navController.navigate(FINISHED_SCREEN) }
    val navigateToDistortion = { navController.navigate(DISTORTION_SCREEN) }
    val navigateToAutoThought = { navController.navigate(AUTOMATIC_THOUGHT_SCREEN) }
    val navigateToChallenge = { navController.navigate(CHALLENGE_SCREEN) }
    val navigateToAlternative = { navController.navigate(ALTERNATIVE_SCREEN) }
    val navigateToFollowUp = { navController.navigate(FOLLOW_UP_REQUEST_SCREEN) }


    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MAIN_SCREEN) {
            MainScreen { navController.navigate(THOUGHT_SCREEN) }
        }
        // On-boarding
        /*composable(WELCOME_SCREEN) {
            WelcomeScreen(navController)
        }*/
        /*composable(CHECKUP_SUMMARY_SCREEN) {
                CheckUpSummaryScreen(navController,viewModel = sharedViewModel)
        }*/
        /*composable(PREDICTION_ONBOARDING_SCREEN) {
            PredictionOnboardingScreen(navController)
        }*/
        /*composable(ASSUMPTION_SCREEN) {
            AssumptionScreen(navController,sharedViewModel = sharedViewModel)
        }*/
        /*composable(PREDICTION_SUMMARY_SCREEN) {
            PredictionSummaryScreen(navController, viewModel = sharedViewModel)
        }*/
        /*composable(ASSUMPTION_NOTE_SCREEN) {
            AssumptionNoteScreen(navController, sharedViewModel)
        }*/
        /*composable(PREDICTION_FOLLOW_UP_SCREEN) {
            PredictionFollowUpScreen(navController, viewModel = sharedViewModel)
        }*/
        composable(AUTOMATIC_THOUGHT_SCREEN) {
            AutomaticThoughtRoute(
                onNavigateToFinished = { navigateToFinished() },
                onNavigateToDistortion = { navController.navigate(DISTORTION_SCREEN) },
                onNavigateToThought = { navController.navigate(THOUGHT_SCREEN) },
                viewModel = viewModel
            )
        }
        composable(THOUGHT_SCREEN) {
            ThoughtRoute(
                onNavigateToPredictionOnboarding = { /*TODO*/ },
                onNavigateToAssumption = { /*TODO*/ },
                onNavigateToAutoThought = { navigateToAutoThought() },
                onNavigateToThoughtViewer = { /*TODO*/ },
                onNavigateToCheckup = { /*TODO*/ },
                onNavigateToCheckupViewer = { /*TODO*/ },
                onNavigateToPredictionViewer = { /*TODO*/ },
                sharedViewModel = viewModel
            )
        }
        composable(DISTORTION_SCREEN) {
            DistortionRoute(
                onNavigateToFinished = { navigateToFinished() },
                onNavigateToChallenge = { navController.navigate(CHALLENGE_SCREEN) },
                onNavigateToAutoThought = { navigateToAutoThought() },
                viewModel = viewModel
            )
        }
        composable(CHALLENGE_SCREEN) {
            ChallengeRoute(
                onNavigateToFinished = { navigateToFinished() },
                onNavigateToDistortion = { navigateToDistortion() },
                onNavigateToAlternative = { navigateToAlternative() },
                onNavigateToAutoThought = { navigateToAutoThought() },
                viewModel = viewModel
            )
        }
        composable(ALTERNATIVE_SCREEN) {
            AlternativeThoughtRoute(
                onNavigateToFinished = { navigateToFinished() },
                onNavigateToFeeling = { navController.navigate(FEELING_SCREEN) },
                onNavigateToChallenge = { navigateToChallenge() },
                thoughtViewModel = thoughtViewModel,
                viewModel = viewModel
            )
        }
        composable(FEELING_SCREEN) {
            FeelingRoute(onNavigateToFollowUp = { navController.navigate(FOLLOW_UP_REQUEST_SCREEN) }, viewModel = viewModel)
        }
        composable(FOLLOW_UP_REQUEST_SCREEN) {
            FollowUpRoute(onNavigateToFinished = { navController.navigate(FINISHED_SCREEN) }, viewModel = viewModel)
        }
        composable(FINISHED_SCREEN) {
            FinishedRoute(
                onNavigateToAutoThought = { navigateToAutoThought() },
                onNavigateToDistortions = { navigateToDistortion() },
                onNavigateToChallenge = { navigateToChallenge() },
                onNavigateToAlternative = { navigateToAlternative() },
                onNavigateToFollowUp = { navigateToFollowUp() },
                resetNavigationToThought = { resetNavigationTo(navController, THOUGHT_SCREEN) },
                viewModel = viewModel,
                thoughtViewModel = thoughtViewModel
            )
        }

        composable(SCREENING_ROUTE) {
            ScreeningRoute()
        }
    }
}

fun resetNavigationTo(navigation: NavController, routeName: String) {
    navigation.navigate(routeName) {
        navigation.popBackStack(0, true)
    }
}
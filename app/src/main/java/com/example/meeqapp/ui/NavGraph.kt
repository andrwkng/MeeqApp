
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meeqapp.ui.AUTOMATIC_THOUGHT_SCREEN
import com.example.meeqapp.ui.CHECKUP_SUMMARY_SCREEN
import com.example.meeqapp.ui.DISTORTION_SCREEN
import com.example.meeqapp.ui.FINISHED_SCREEN
import com.example.meeqapp.ui.FinishedScreen
import com.example.meeqapp.ui.MAIN_SCREEN
import com.example.meeqapp.ui.MainScreen
import com.example.meeqapp.ui.THOUGHT_SCREEN
import com.example.meeqapp.ui.checkup.CheckUpSummaryScreen
import com.example.meeqapp.ui.distortions.DistortionScreen
import com.example.meeqapp.ui.onboarding.WELCOME_SCREEN
import com.example.meeqapp.ui.onboarding.WelcomeScreen
import com.example.meeqapp.ui.predictions.ASSUMPTION_NOTE_SCREEN
import com.example.meeqapp.ui.predictions.ASSUMPTION_SCREEN
import com.example.meeqapp.ui.predictions.AssumptionNoteScreen
import com.example.meeqapp.ui.predictions.AssumptionScreen
import com.example.meeqapp.ui.predictions.PREDICTION_FOLLOW_UP_SCREEN
import com.example.meeqapp.ui.predictions.PREDICTION_ONBOARDING_SCREEN
import com.example.meeqapp.ui.predictions.PREDICTION_SUMMARY_SCREEN
import com.example.meeqapp.ui.predictions.PredictionFollowUpScreen
import com.example.meeqapp.ui.predictions.PredictionOnboardingScreen
import com.example.meeqapp.ui.predictions.PredictionSummaryScreen
import com.example.meeqapp.ui.thoughts.AutomaticThoughtScreen
import com.example.meeqapp.ui.thoughts.SavedThought
import com.example.meeqapp.ui.viewmodel.SharedViewModel

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MAIN_SCREEN,
    sharedViewModel: SharedViewModel = viewModel()
) {
    val thought = sharedViewModel.thought.value
    val prediction = sharedViewModel.prediction.value

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MAIN_SCREEN) {
            MainScreen(navController)
        }
        // On-boarding
        composable(WELCOME_SCREEN) {
            WelcomeScreen(navController)
        }
        composable(CHECKUP_SUMMARY_SCREEN) {
            val checkup = sharedViewModel.checkup.value
            if (checkup != null) {
                CheckUpSummaryScreen(navController,checkup)
            }
        }
        composable(PREDICTION_ONBOARDING_SCREEN) {
            PredictionOnboardingScreen(navController)
        }
        composable(ASSUMPTION_SCREEN) {
            AssumptionScreen(navController)
        }
        composable(PREDICTION_SUMMARY_SCREEN) {
            PredictionSummaryScreen(navController, prediction)
        }
        composable(ASSUMPTION_NOTE_SCREEN) {
            AssumptionNoteScreen(navController, sharedViewModel)
        }
        composable(PREDICTION_FOLLOW_UP_SCREEN) {
            PredictionFollowUpScreen(navController, prediction)
        }
        composable(AUTOMATIC_THOUGHT_SCREEN) {
            AutomaticThoughtScreen(navController, thought)
        }
        composable(FINISHED_SCREEN) {
            FinishedScreen(navController,thought as SavedThought)
        }
        composable(THOUGHT_SCREEN) {
            MainScreen(navController)
        }
        composable(DISTORTION_SCREEN) {
            DistortionScreen(navController)
        }
    }
}

fun resetNavigationTo(navigation: NavController, routeName: String) {
    navigation.navigate(routeName) {
        navigation.popBackStack(0, true)
    }
}
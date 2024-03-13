
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spryteam.meeqapp.ui.ALTERNATIVE_SCREEN
import com.spryteam.meeqapp.ui.AUTOMATIC_THOUGHT_SCREEN
import com.spryteam.meeqapp.ui.CHALLENGE_SCREEN
import com.spryteam.meeqapp.ui.DISTORTION_SCREEN
import com.spryteam.meeqapp.ui.FEELING_SCREEN
import com.spryteam.meeqapp.ui.FINISHED_SCREEN
import com.spryteam.meeqapp.ui.FOLLOW_UP_REQUEST_SCREEN
import com.spryteam.meeqapp.ui.FeelingRoute
import com.spryteam.meeqapp.ui.FinishedRoute
import com.spryteam.meeqapp.ui.MAIN_SCREEN
import com.spryteam.meeqapp.ui.MainRoute
import com.spryteam.meeqapp.ui.THOUGHT_SCREEN
import com.spryteam.meeqapp.ui.distortions.DistortionRoute
import com.spryteam.meeqapp.ui.followup.FollowUpRoute
import com.spryteam.meeqapp.ui.thoughts.AlternativeThoughtRoute
import com.spryteam.meeqapp.ui.thoughts.AutomaticThoughtRoute
import com.spryteam.meeqapp.ui.thoughts.ChallengeRoute
import com.spryteam.meeqapp.ui.thoughts.ThoughtRoute
import com.spryteam.meeqapp.ui.thoughts.ThoughtViewModel
import com.spryteam.meeqapp.ui.viewmodel.SharedViewModel

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
    val navigateToThought = { navController.navigate(THOUGHT_SCREEN) }


    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MAIN_SCREEN) {
            MainRoute(
                onNavigateToAssumption = { /*TODO*/ },
                onNavigateToAutoThought = { navController.navigate(THOUGHT_SCREEN) },
                onNavigateToThoughtViewer = { /*TODO*/ },
                onNavigateToCheckup = { /*TODO*/ },
                onNavigateToCheckupViewer = { /*TODO*/ },
                sharedViewModel = viewModel
            )
        }

        composable(AUTOMATIC_THOUGHT_SCREEN) {
            AutomaticThoughtRoute(
                onNavigateToFinished = { navigateToFinished() },
                onNavigateToDistortion = { navController.navigate(DISTORTION_SCREEN) },
                onNavigateToThought = { navController.navigate(THOUGHT_SCREEN) },
                thoughtViewModel = thoughtViewModel
            )
        }
        composable(THOUGHT_SCREEN) {
            ThoughtRoute(
                onSaveThought = viewModel::saveThought,
                onNavigateToMain = { navController.navigate(MAIN_SCREEN) })

        }
        composable(DISTORTION_SCREEN) {
            DistortionRoute(
                onNavigateToFinished = { navigateToFinished() },
                onNavigateToChallenge = { navController.navigate(CHALLENGE_SCREEN) },
                onNavigateToAutoThought = { navigateToAutoThought() },
                thoughtViewModel = thoughtViewModel
            )
        }
        composable(CHALLENGE_SCREEN) {
            ChallengeRoute(
                onNavigateToFinished = { navigateToFinished() },
                onNavigateToDistortion = { navigateToDistortion() },
                onNavigateToAlternative = { navigateToAlternative() },
                onNavigateToAutoThought = { navigateToAutoThought() },
                thoughtViewModel = thoughtViewModel
            )
        }
        composable(ALTERNATIVE_SCREEN) {
            AlternativeThoughtRoute(
                onNavigateToFinished = { navigateToFinished() },
                onNavigateToFeeling = { navController.navigate(FEELING_SCREEN) },
                onNavigateToChallenge = { navigateToChallenge() },
                thoughtViewModel = thoughtViewModel,
            )
        }
        composable(FEELING_SCREEN) {
            FeelingRoute(
                onNavigateToFollowUp = { navController.navigate(FOLLOW_UP_REQUEST_SCREEN) },
                thoughtViewModel = thoughtViewModel
            )
        }
        composable(FOLLOW_UP_REQUEST_SCREEN) {
            FollowUpRoute(
                onNavigateToFinished = { navController.navigate(FINISHED_SCREEN) },
                thoughtViewModel = thoughtViewModel
            )
        }
        composable(FINISHED_SCREEN) {
            FinishedRoute(
                onNavigateToAutoThought = { navigateToAutoThought() },
                onNavigateToDistortions = { navigateToDistortion() },
                onNavigateToChallenge = { navigateToChallenge() },
                onNavigateToAlternative = { navigateToAlternative() },
                onNavigateToFollowUp = { navigateToFollowUp() },
                resetNavigationToThought = { resetNavigationTo(navController, THOUGHT_SCREEN) },
                thoughtViewModel = thoughtViewModel,
                viewModel = viewModel
            )
        }
    }
}

fun resetNavigationTo(navigation: NavController, routeName: String) {
    navigation.navigate(routeName) {
        navigation.popBackStack(0, true)
    }
}
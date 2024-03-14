
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spryteam.meeqapp.ui.MAIN_SCREEN
import com.spryteam.meeqapp.ui.MainRoute
import com.spryteam.meeqapp.ui.THOUGHT_SCREEN
import com.spryteam.meeqapp.ui.thoughts.ThoughtRoute
import com.spryteam.meeqapp.ui.viewmodel.SharedViewModel

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MAIN_SCREEN,
    viewModel: SharedViewModel = viewModel()
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MAIN_SCREEN) {
            MainRoute(
                onNavigateToAutoThought = { navController.navigate(THOUGHT_SCREEN) },
                onNavigateToThoughtViewer = { /*TODO*/ },
                onNavigateToCheckup = { /*TODO*/ },
                onNavigateToCheckupViewer = { /*TODO*/ },
                sharedViewModel = viewModel
            )
        }

        composable(THOUGHT_SCREEN) {
            ThoughtRoute(
                onSaveThought = viewModel::saveThought,
                onNavigateToMain = { navController.navigate(MAIN_SCREEN) })

        }

    }
}

fun resetNavigationTo(navigation: NavController, routeName: String) {
    navigation.navigate(routeName) {
        navigation.popBackStack(0, true)
    }
}
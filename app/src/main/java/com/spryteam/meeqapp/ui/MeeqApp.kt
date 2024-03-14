package com.spryteam.meeqapp.ui

import NavGraph
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.spryteam.meeqapp.ui.theme.MeeqAppTheme

@Composable
fun MeeqApp(finishActivity: () -> Unit) {
    MeeqAppTheme {
        val navController = rememberNavController()

        NavGraph(
            navController = navController,
        )
    }
}
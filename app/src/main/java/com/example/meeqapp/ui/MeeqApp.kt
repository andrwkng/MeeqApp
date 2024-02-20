package com.example.meeqapp.ui

import NavGraph
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.meeqapp.ui.theme.MeeqAppTheme

@Composable
fun MeeqApp(finishActivity: () -> Unit) {
    MeeqAppTheme {
        val navController = rememberNavController()

        NavGraph(
            navController = navController,
        )
    }
}
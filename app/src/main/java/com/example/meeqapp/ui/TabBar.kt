package com.example.meeqapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meeqapp.ui.articles.TAB_BAR_HEIGHT
import com.example.meeqapp.ui.components.ActionButton
import com.example.meeqapp.ui.theme.Theme

@Composable
fun TabBar(navController: NavController) {
    val tabRoutes = listOf(
        MAIN_SCREEN,
        EXPLANATION_SCREEN,
        // Add other screen keys as needed
    )

    val currentRoute = navController.currentBackStackEntry?.destination?.route

    // Hide the tab bar in specific screens
    if (currentRoute in listOf(
            LOCK_SCREEN,
            CBT_ON_BOARDING_SCREEN,
            CHECKUP_SCREEN,
            SUPPORT_SCREEN,
            MARKDOWN_ARTICLE_SCREEN
        )
    ) {
        return
    }

    // Hide if we're just hidden from the keyboard
    // (Assuming you have a way to determine keyboard visibility)
    // if (isKeyboardHidden()) {
    //    return
    // }

    Row(
        modifier = Modifier
            .background(Color.White)
            .height(TAB_BAR_HEIGHT)
            .border(1.dp, Theme.lightGray)
            .padding(top = 12.dp, bottom = 24.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionButton(
            title = "Settings",
            width = 100.dp,
            fillColor = if (currentRoute == SETTING_SCREEN) Theme.colorLightBlue else Color.White,
            textColor = if (currentRoute == SETTING_SCREEN) Theme.darkBlue else Theme.veryLightText,
            onClick = {
                navController.navigate(SETTING_SCREEN)
            }
        )

        ActionButton(
            title = "Thoughts",
            width = 100.dp,
            fillColor = if (currentRoute == MAIN_SCREEN) Theme.colorLightBlue else Color.White,
            textColor = if (currentRoute == MAIN_SCREEN) Theme.darkBlue else Theme.veryLightText,
            onClick = {
                navController.navigate(MAIN_SCREEN)
            }
        )

        ActionButton(
            title = "Learn",
            width = 100.dp,
            fillColor = if (currentRoute == EXPLANATION_SCREEN) Theme.colorLightBlue else Color.White,
            textColor = if (currentRoute == EXPLANATION_SCREEN) Theme.darkBlue else Theme.veryLightText,
            onClick = {
                navController.navigate(EXPLANATION_SCREEN)
            }
        )
    }
}

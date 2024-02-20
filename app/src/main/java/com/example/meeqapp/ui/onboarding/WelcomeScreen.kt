package com.example.meeqapp.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.meeqapp.ui.components.ActionButton
import com.example.meeqapp.ui.components.HintHeader
import com.example.meeqapp.ui.components.MediumHeader
import com.example.meeqapp.ui.theme.Theme

@Composable
fun WelcomeScreen(navController: NavHostController) {

    fun onNext() {
        // Handle the "Next" button click
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .background(Theme.lightOffwhite)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        MediumHeader(text = "Welcome to Meeq! 👋")
        HintHeader(
            text = "We'll get you started in a moment, but first we've got somethings to get out of the way.",
            modifier = Modifier
                .padding(bottom = 24.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ActionButton(
                title = "Next",
                //width = Modifier.fillMaxWidth(),
                onClick = { onNext() }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
        WelcomeScreen(rememberNavController())
}
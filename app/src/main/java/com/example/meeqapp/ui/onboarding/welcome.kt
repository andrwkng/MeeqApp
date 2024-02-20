package com.example.meeqapp.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// WelcomeScreen
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YourComposeScreen(onNext: () -> Unit) {
    //val statusBarHeight = Constants.statusBarHeight.dp
    //val theme = LocalTheme.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Welcome to Quirk! 👋", style = MaterialTheme.typography.h5) },
                backgroundColor = theme.lightOffwhite,
                elevation = 0.dp,
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = statusBarHeight)
                    .background(theme.lightOffwhite)
            ) {
                KeyboardAvoidingView(
                    behavior = KeyboardAvoidingViewBehavior.Position,
                    content = {
                        Column(
                            modifier = Modifier
                                .padding(bottom = 24.dp)
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Welcome to Quirk! 👋",
                                style = MaterialTheme.typography.h5,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                            Text(
                                text = "We'll get you started in a moment, but first we've got somethings to get out of the way.",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 24.dp)
                            )
                            Button(
                                onClick = { onNext() },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "Next")
                            }
                        }
                    }
                )
            }
        }
    )
}
*/

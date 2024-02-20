package com.example.meeqapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meeqapp.R
import com.example.meeqapp.ui.components.ActionButton
import com.example.meeqapp.ui.components.GhostButton
import com.example.meeqapp.ui.components.GhostButtonWithGuts
import com.example.meeqapp.ui.components.SubHeader
import com.example.meeqapp.ui.theme.Theme

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChallengeScreen() {
    val thoughtState = remember { mutableStateOf<YourThought?>(null) }
    val isEditing = thoughtState.value != null

    // Replace with appropriate Jetpack Compose theme and colors
    val theme = Theme

    // Replace with appropriate textInputStyle and textInputPlaceholderColor
    val textInputStyle = TextStyle(color = Color.Black, fontSize = 16.sp)
    val textInputPlaceholderColor = Color.Gray

    // Replace with your navigation actions
    //val navController = rememberNavController()


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .background(theme.lightOffwhite)
        ) {
            thoughtState.value?.let { thought ->
                Column {
                    MediumHeader(title = stringResource(id = R.string.challenge))
                    HintHeader(title = stringResource(id = R.string.challenge_hint))

                    Column(
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                            .fillMaxWidth()
                    ) {
                        SubHeader(text = stringResource(id = R.string.your_thought))
                        GhostButtonWithGuts(
                            borderColor = theme.lightGray,
                            onClick = {
                                navController.navigate(
                                    AUTOMATIC_THOUGHT_SCREEN,
                                    bundleOf("thought" to thought)
                                )
                            }
                        ) {
                            Text(text = thought.automaticThought)
                        }
                    }

                    SubHeader(text = stringResource(id = R.string.your_challenge))
                    BasicTextField(
                        value = thought.challenge,
                        onValueChange = { newText -> */
/* Handle text change *//*
 },
                        textStyle = textInputStyle,
                        placeholder = { Text(text = stringResource(id = R.string.changed_placeholder)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp)
                            .padding(top = 8.dp)
                            .onBlur { */
/* Handle blur event *//*
 }
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                            .padding(end = 24.dp)
                            .wrapContentWidth(align = Alignment.End)
                    ) {
                        if (isEditing) {
                            ActionButton(
                                title = stringResource(id = R.string.save),
                                onClick = { */
/* Handle save button click *//*
 },
                                style = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 24.dp)
                            )
                        } else {
                            GhostButton(
                                borderColor = theme.lightGray,
                                textColor = theme.veryLightText,
                                title = stringResource(id = R.string.back),
                                onClick = {
                                    navController.navigate(DISTORTION_SCREEN, bundleOf("thought" to thought))
                                },
                                style = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 24.dp)
                            )

                            ActionButton(
                                title = stringResource(id = R.string.next),
                                onClick = { */
/* Handle next button click *//*
 }
                            )
                        }
                    }
                }
            }
        }

}
*/

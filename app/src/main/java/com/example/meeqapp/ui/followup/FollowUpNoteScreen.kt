package com.example.meeqapp.ui.followup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.meeqapp.ui.components.ActionButton
import com.example.meeqapp.ui.components.HintHeader
import com.example.meeqapp.ui.components.Paragraph
import com.example.meeqapp.ui.components.SubHeader
import com.example.meeqapp.ui.theme.TextInputStyle
import com.example.meeqapp.ui.thoughts.MediumHeader
import com.example.meeqapp.ui.thoughts.Thought


@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun FollowUpNoteScreen() {
        var thought by remember { mutableStateOf<Thought?>(null) }
        val context = LocalContext.current

        DisposableEffect(context) {
            // Add listeners for willFocus and didFocus events
            onDispose {
                // Cleanup
            }
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            //color = MaterialTheme.colorScheme.background
        ) {
            thought?.let {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxSize()
                ) {
                    MediumHeader(
                        title = "Alright, let's start your follow-up.",
                        //modifier = Modifier.padding(bottom = 12.dp)
                    )
                    HintHeader(
                        text = "This is a chance for you to re-evaluate your thoughts with a " +
                                "clearer perspective or to get closure on anything that happened.",
                        modifier = Modifier
                            .padding(bottom = 24.dp)
                    )

                    thought!!.automaticThought?.let {
                        Column(
                            modifier = Modifier
                                .padding(bottom = 24.dp)
                        ) {
                            SubHeader(text = "Your Automatic Thought")
                            Paragraph(
                                 Modifier
                                    .padding(bottom = 12.dp)
                            ){"\"$it\""}
                        }
                    }

                    SubHeader(text = "Add a Follow-up Note")
                    HintHeader(text = "Does your thought seem accurate still? Did anything else happen?")

                    var followUpNote by remember { mutableStateOf("") }

                    TextField(
                        value = followUpNote,
                        onValueChange = { followUpNote = it },
                        placeholder = { Text("ex: \"it wasn't as bad as I had thought\"") },
                        textStyle = TextInputStyle().style,
                        maxLines = 6,
                        singleLine = false,
                        modifier = Modifier.fillMaxSize(),
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        ActionButton(
                            title = "Save",
                            onClick = {
                                // Handle save action
                            },
                            style = Modifier
                                .fillMaxWidth()
                        )
                    }
                }

            }
        }
    }


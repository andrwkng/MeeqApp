package com.spryteam.meeqapp.ui.followup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spryteam.meeqapp.ui.components.ActionButton
import com.spryteam.meeqapp.ui.components.GhostButtonWithGuts
import com.spryteam.meeqapp.ui.components.HintHeader
import com.spryteam.meeqapp.ui.components.MediumHeader
import com.spryteam.meeqapp.ui.components.SingleLineText
import com.spryteam.meeqapp.ui.components.SubHeader
import com.spryteam.meeqapp.ui.theme.Theme


@Composable
fun FollowUpNoteScreen(
    followUpNote: String,
    onFollowUpNoteChange: (String) -> Unit,
    automaticThought: String,
) {
    //var thought by remember { mutableStateOf<Thought?>(null) }

    /*DisposableEffect(context) {
        // Add listeners for willFocus and didFocus events
        onDispose {
            // Cleanup
        }
    }*/

    Column(
        modifier = Modifier.fillMaxSize(),
        //color = MaterialTheme.colorScheme.background
    ) {
        //thought?.let {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    //.fillMaxSize()
            ) {
                MediumHeader(
                    text = "Alright, let's start your follow-up.",
                    //modifier = Modifier.padding(bottom = 12.dp)
                )
                HintHeader(
                    text = "This is a chance for you to re-evaluate your thoughts with a " +
                            "clearer perspective or to get closure on anything that happened.",
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                )

                //thought!!.automaticThought?.let {
                    Column(
                        modifier = Modifier
                            .padding(bottom = 24.dp)
                    ) {
                        SubHeader(text = "Your Automatic Thought")
                        GhostButtonWithGuts(
                            borderColor = Theme.lightGray,
                            onClick = { },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                        ) {
                            SingleLineText(automaticThought)
                        }
                        /*Paragraph(
                            Modifier
                                .padding(bottom = 12.dp)
                        ){*//*"\"$it\""*//*}*/
                    }
                //}

                SubHeader(text = "Add a Follow-up Note")
                HintHeader(text = "Does your thought seem accurate still? Did anything else happen?")

                OutlinedTextField(
                    value = followUpNote,
                    onValueChange = onFollowUpNoteChange,
                    placeholder = { Text("ex: \"it wasn't as bad as I had thought\"") },
                    //textStyle = TextInputStyle().style,
                    //maxLines = 6,
                    //singleLine = false,
                    modifier = Modifier
                        .fillMaxWidth()
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
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }

       // }
    }
}

@Preview(showBackground = true)
@Composable
fun FollowUpNoteScreenPreview() {
    FollowUpNoteScreen(
        followUpNote = "",
        onFollowUpNoteChange = {},
        automaticThought = "My auto thought"
    )
}
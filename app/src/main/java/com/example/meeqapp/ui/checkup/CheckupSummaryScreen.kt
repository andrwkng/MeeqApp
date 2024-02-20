package com.example.meeqapp.ui.checkup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meeqapp.ui.THOUGHT_SCREEN
import com.example.meeqapp.ui.components.ActionButton
import com.example.meeqapp.ui.components.FloatingCard
import com.example.meeqapp.ui.components.MoodText
import com.example.meeqapp.ui.components.SubHeader
import com.example.meeqapp.ui.theme.Theme
import com.example.meeqapp.ui.thoughts.MediumHeader
import com.example.meeqapp.ui.viewmodel.SharedViewModel

@Composable
fun CheckUpSummaryScreen(
    navigation: NavController,
    viewModel: SharedViewModel
) {
    val checkup by remember { mutableStateOf(viewModel.checkup) }

    if (checkup == null) {
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .background(Theme.lightOffwhite)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                MediumHeader(
                    title = "Milestone Summary",
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                )
            }

            item {
                FloatingCard {
                    Column(
                        modifier = Modifier
                            .padding(bottom = 24.dp)
                    ) {
                        SubHeader(text = "Milestone date")
                        //Paragraph(text = dayjs().format("DD-MM-YYYY"))
                    }

                    Column(
                        modifier = Modifier
                            .padding(bottom = 24.dp)
                    ) {
                        SubHeader(text = "How things are going")
                        MoodText(checkup?.currentMood ?: null)
                    }

                    checkup?.note?.let { note ->
                        Column {
                            SubHeader(text = "Notes")
                            //Paragraph(text = note)
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            ActionButton(
                title = "Finish",
                style = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                onClick = { navigation.navigate(THOUGHT_SCREEN) }
            )
        }
    }
}
/*

@Preview(showBackground = true)
@Composable
fun CheckUpSummaryScreenPreview() {
    // Provide a mock ScreenProps object for the preview
    CheckUpSummaryScreen(ScreenProps(navigation = */
/* provide a mock navigation object *//*
))
}
*/

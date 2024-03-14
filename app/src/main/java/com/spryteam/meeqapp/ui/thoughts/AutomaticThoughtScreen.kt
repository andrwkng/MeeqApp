package com.spryteam.meeqapp.ui.thoughts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spryteam.meeqapp.R
import com.spryteam.meeqapp.ui.components.ActionButton
import com.spryteam.meeqapp.ui.components.GhostButton
import com.spryteam.meeqapp.ui.components.HintHeader
import com.spryteam.meeqapp.ui.components.MediumHeader
import com.spryteam.meeqapp.ui.theme.Theme

@Composable
fun AutomaticThoughtScreen(
    isEditing: Boolean,
    isNextDisable: Boolean,
    autoThoughtVal: String,
    onAutoThoughtChange: (String) -> Unit,
    onFinishPressed: () -> Unit,
    onNextPressed: () -> Unit,
    onCancelPressed: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(24.dp)
            .background(Theme.lightOffWhite)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            MediumHeader(text = stringResource(id = R.string.auto_thought))
            HintHeader("What's going on in your mind right now?")

            OutlinedTextField(
                value = autoThoughtVal,
                label = { Text("What's going on?") },
                onValueChange = onAutoThoughtChange,
                placeholder = { Text(text = stringResource(id = R.string.auto_thought_placeholder)) },
                singleLine = false,
                maxLines = 6,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 24.dp)
                    .heightIn(min = 100.dp),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                // Conditional rendering based on isEditing
                if (isEditing) {
                    ActionButton(
                        title = "Finish",
                        onClick = { onFinishPressed() },
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    GhostButton(
                        title = "Cancel",
                        onClick = { onCancelPressed() },
                        marginRight = 12.dp,
                    )

                    ActionButton(
                        title = "Next",
                        onClick = { onNextPressed() },
                        modifier = Modifier.weight(1f),
                        disabled = isNextDisable
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AutomaticThoughtScreenPreview() {
    AutomaticThoughtScreen(
        isEditing = false,
        isNextDisable = true,
        autoThoughtVal = "",
        onAutoThoughtChange = { /*TODO*/ },
        onFinishPressed = { /*TODO*/ },
        onNextPressed = { /*TODO*/ }) {
        
    }
}
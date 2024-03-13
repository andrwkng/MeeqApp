package com.sprytm.meeqapp.ui.thoughts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spryteam.meeqapp.R

import com.sprytm.meeqapp.ui.components.ActionButton
import com.sprytm.meeqapp.ui.components.GhostButton
import com.sprytm.meeqapp.ui.components.HintHeader
import com.sprytm.meeqapp.ui.components.MediumHeader
import com.sprytm.meeqapp.ui.theme.Theme
import com.sprytm.meeqapp.ui.viewmodel.SharedViewModel

@Composable
fun AlternativeThoughtRoute(
    onNavigateToFinished: () -> Unit,
    onNavigateToFeeling: () -> Unit,
    onNavigateToChallenge: () -> Unit,
    viewModel: SharedViewModel,
    thoughtViewModel: ThoughtViewModel
) {
    val altThought: String by thoughtViewModel.alternativeThought.collectAsState("")

    AlternativeThoughtScreen(
        isEditing = viewModel.isEditing,
        isNextDisabled = viewModel.isNextDisabled,
        altThoughtVal = altThought,
        onAltThoughtChange = thoughtViewModel::onAltThoughtChange,
        onNextPressed = { viewModel.onNext(onNavigateToFeeling) },
        onFinishPressed = onNavigateToFinished,
        onBackPressed = { viewModel.onNext(onNavigateToChallenge) }
    )
}

@Composable
fun AlternativeThoughtScreen(
    isEditing: Boolean,
    isNextDisabled: Boolean,
    altThoughtVal: String,
    onAltThoughtChange: (String) -> Unit,
    onNextPressed: () -> Unit,
    onFinishPressed: () -> Unit,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //thought?.let {
            MediumHeader(text = stringResource(R.string.alt_thought))
            HintHeader(
                text = "Given this situation again, what could you think instead?"
            )

            OutlinedTextField(
                value = altThoughtVal,
                onValueChange = onAltThoughtChange,
                //textStyle = MaterialTheme.typography.bodyMedium,
                //maxLines = 6,
                label = { Text("Alternative Thought") },
                placeholder = { Text(stringResource(R.string.alt_thought_placeholder)) },
                /*keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),*/
                /*colors = TextFieldDefaults.textFieldColors(
                    placeholderColor = textInputPlaceholderColor.toColor(),
                    backgroundColor = Color.Transparent,
                    cursorColor = theme.lightGray
                )*/
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.End
            ) {
                if (isEditing) {
                    ActionButton(
                        title = "Save",
                        onClick = { onFinishPressed() },
                        style = Modifier.fillMaxWidth()
                    )
                } else {
                    GhostButton(
                        borderColor = Theme.lightGray,
                        textColor = Theme.veryLightText,
                        title = "Back",
                        style = Modifier.weight(1f),
                        marginRight = 24.dp,
                        onClick = { onBackPressed() }
                    )
                    ActionButton(
                        title = "Next",
                        onClick = { onNextPressed() },
                        disabled = isNextDisabled,
                        style = Modifier.weight(1f)
                    )
                }
            }
        //}
    }
}


@Preview(showBackground = true)
@Composable
fun AlternativeScreenPreview() {
    AlternativeThoughtScreen(
        isEditing = false,
        isNextDisabled = true,
        altThoughtVal = "Alternative Thought",
        onAltThoughtChange = { /*TODO*/ },
        onNextPressed = { /*TODO*/ },
        onFinishPressed = { /*TODO*/ }) {
    }
}
package com.sprytm.meeqapp.ui.thoughts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun AutomaticThoughtRoute(
    onNavigateToFinished: () -> Unit,
    onNavigateToDistortion: () -> Unit,
    onNavigateToThought: () -> Unit,
    viewModel: SharedViewModel,
) {
   // val viewModel: AutomaticThoughtViewModel = viewModel()
    val autoThought: String by viewModel.automaticThought.collectAsState("")

    AutomaticThoughtScreen(
        isEditing = viewModel.isEditing,
        isNextDisable = viewModel.isNextDisabled,
        autoThoughtVal = autoThought,
        onAutoThoughtChange = viewModel::onChange,
        onFinishPressed = onNavigateToFinished,
        onNextPressed = { viewModel.onNext(onNavigateToDistortion) },
        onCancelPressed = onNavigateToThought

    )
}
@OptIn(ExperimentalMaterial3Api::class)
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

    LaunchedEffect(true) {
        //thought.value = newThought()
    }


    Surface(
        modifier = Modifier
            .padding(24.dp)
            .background(Theme.lightOffwhite)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            MediumHeader(text = stringResource(id = R.string.auto_thought))
            HintHeader("What's going on?")

            var eventLabel by remember { mutableStateOf("") }
            OutlinedTextField(
                value = autoThoughtVal,
                label = { Text("What's going on?") },
                onValueChange = onAutoThoughtChange,
                singleLine = false,
                maxLines = 6,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 24.dp),
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
                        style = Modifier.weight(1f)
                    )
                } else {
                    GhostButton(
                        title = "Cancel",
                        onClick = { onCancelPressed()/*navigation.navigate(THOUGHT_SCREEN)*/ },
                        marginRight = 12.dp,
                    )

                    ActionButton(
                        title = "Next",
                        onClick = { onNextPressed() },
                        style = Modifier.weight(1f),
                        disabled = isNextDisable
                        //disabled = thought.value?.automaticThought.isNullOrEmpty()
                    )
                }
            }
        }
    }
}

/*@Composable
fun MediumHeader(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        modifier = modifier.padding(bottom = 12.dp),
        fontSize = 20.sp,
        fontWeight = FontWeight.W900,
        color = Theme.darkText,
    )
}*/

/*@Composable
fun HintHeader(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        modifier = modifier,
        fontSize = 16.sp,
        fontWeight = FontWeight.W700,
        color = Theme.veryLightText,
    )
}*/

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
    //AutomaticThoughtScreen(rememberNavController(), /*viewModel = hiltViewModel()*/)
}
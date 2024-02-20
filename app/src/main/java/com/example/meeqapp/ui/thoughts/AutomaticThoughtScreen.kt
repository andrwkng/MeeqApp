package com.example.meeqapp.ui.thoughts

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meeqapp.R
import com.example.meeqapp.ui.DISTORTION_SCREEN
import com.example.meeqapp.ui.FINISHED_SCREEN
import com.example.meeqapp.ui.THOUGHT_SCREEN
import com.example.meeqapp.ui.components.ActionButton
import com.example.meeqapp.ui.components.GhostButton
import com.example.meeqapp.ui.components.HintHeader
import com.example.meeqapp.ui.theme.Theme
import com.example.meeqapp.ui.viewmodel.SharedViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutomaticThoughtScreen(
    navigation: NavController,
    isEditing: Boolean = false,
    viewModel: SharedViewModel,
) {
    val thought = remember { mutableStateOf(viewModel.thought) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        thought.value = newThought()
    }

    fun saveThought() {
        coroutineScope.launch {
            val nThought = thought.value
            if (nThought != null) {
                viewModel.saveThought(nThought)
            }
        }
    }

    fun onFinish() {
        saveThought()
        navigation.navigate(FINISHED_SCREEN)
    }

    fun onNext() {
        saveThought()
        navigation.navigate(DISTORTION_SCREEN)
    }

    fun onChange(label: String) {
        thought.value?.automaticThought = label
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
            MediumHeader(title = stringResource(id = R.string.auto_thought))
            HintHeader("What's going on?")

            var eventLabel by remember { mutableStateOf("") }
            TextField(
                value = eventLabel,
                onValueChange = {
                    eventLabel = it
                    onChange(it)
                },
                singleLine = false,
                maxLines = 6,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 24.dp),
            )

            // Equivalent of Row with justifyContent="flex-end"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                // Conditional rendering based on isEditing
                if (isEditing) {
                    ActionButton(
                        title = "Finished",
                        onClick = { onFinish() },
                        style = Modifier.weight(1f)
                    )
                } else {
                    GhostButton(
                        title = "Cancel",
                        onClick = { navigation.navigate(THOUGHT_SCREEN) },
                        marginRight = 12.dp,
                    )

                    ActionButton(
                        title = "Next",
                        onClick = { onNext() },
                        style = Modifier.weight(1f),
                        disabled = thought.value?.automaticThought.isNullOrEmpty()
                    )
                }
            }
        }
    }
}

@Composable
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
}

@Composable
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
}

@Preview(showBackground = true)
@Composable
fun AutomaticThoughtScreenPreview() {
    AutomaticThoughtScreen(rememberNavController(), viewModel = hiltViewModel())
}
package com.example.meeqapp.ui.thoughts

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutomaticThoughtScreen(
    navigation: NavController,
    thought: Thought? = null,
    isEditing: Boolean = false,
    sharedViewModel: SharedViewModel = viewModel(),
) {
    var thought = remember { mutableStateOf(thought) }
    val context = LocalContext.current

    LaunchedEffect(true) {
        thought.value = newThought()
    }

    fun onFinish() {
        GlobalScope.launch {
            sharedViewModel.thought.value = saveThought(thought.value!!, context)
        }
        navigation.navigate(FINISHED_SCREEN)
    }

    fun onNext() {
        GlobalScope.launch {
            sharedViewModel.thought.value = saveThought(thought.value!!, context)
            navigation.navigate(DISTORTION_SCREEN)
        }
    }

    fun onChange(label: String) {
        thought.value?.automaticThought = label
    }

    Surface(
        modifier = Modifier
            .padding(24.dp)
            //.background(lightOffwhite)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                //.padding(bottom = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (thought != null) {
                //Row {
                        MediumHeader(title = stringResource(id = R.string.auto_thought))
                        HintHeader("What's going on?")
               // }

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
                        //.padding(end = 12.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    // Conditional rendering based on isEditing
                    if (isEditing) {
                        // Equivalent of ActionButton
                        ActionButton(
                            title = "Finished",
                            onClick = { onFinish() },
                            style = Modifier.weight(1f)
                        )
                    } else {
                        // Equivalent of GhostButton
                        GhostButton(
                            title = "Cancel",
                            onClick = { navigation.navigate(THOUGHT_SCREEN) },
                            marginRight = 12.dp,
                        )

                        // Equivalent of ActionButton
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
}

@Composable
fun MediumHeader(
    title: String,
    style: Modifier = Modifier.padding(bottom = 12.dp),
    //allowFontScaling: Boolean = false
) {
    Text(
        text = title,
        modifier = style,
        fontSize = 20.sp,
        fontWeight = FontWeight.W900,
        color = Theme.darkText,
        //allowFontScaling = true
    )
}

@Composable
fun HintHeader(
    title: String,
    style: Modifier = Modifier.padding(bottom = 12.dp),
) {
    Text(
        text = title,
        modifier = style,
        fontSize = 16.sp,
        fontWeight = FontWeight.W700,
        color = Theme.veryLightText,
    )
}

@Preview(showBackground = true)
@Composable
fun AutomaticThoughtScreenPreview() {
    AutomaticThoughtScreen(rememberNavController())
}

/*@Composable
fun AutomaticThoughtScreen() {
    var thought by remember { mutableStateOf<Thought?>(null) }
    var isEditing by remember { mutableStateOf(false) }

    val automaticThoughtPlaceholder = stringResource(id = R.string.cbt_form_auto_thought_placeholder)

    Container(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Constants.statusBarHeight + 24),
        backgroundColor = theme.lightOffwhite
    ) {
        KeyboardAvoidingView(
            behavior = KeyboardViewBehavior.Position,
            modifier = Modifier.padding(bottom = 24)
        ) {
            thought?.let {
                Column {
                    Row {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            MediumHeader(text = stringResource(id = R.string.auto_thought))
                            HintHeader(text = stringResource(id = R.string.whats_going_on))
                        }
                    }

                    BasicTextField(
                        value = it.automaticThought,
                        onValueChange = { newText ->
                            thought = it.copy(automaticThought = newText)
                        },
                        textStyle = textInputStyle,
                        placeholder = { Text(text = automaticThoughtPlaceholder) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 6 * LocalDensity.current.density)
                            .padding(top = 8.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            stats.userFilledOutFormField("automatic")
                        })
                    )


                }
            }
        }
    }
}*/


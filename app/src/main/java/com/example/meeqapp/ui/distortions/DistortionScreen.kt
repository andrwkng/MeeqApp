package com.example.meeqapp.ui.distortions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.meeqapp.R
import com.example.meeqapp.ui.AUTOMATIC_THOUGHT_SCREEN
import com.example.meeqapp.ui.CHALLENGE_SCREEN
import com.example.meeqapp.ui.FINISHED_SCREEN
import com.example.meeqapp.ui.components.ActionButton
import com.example.meeqapp.ui.components.GhostButton
import com.example.meeqapp.ui.components.GhostButtonWithGuts
import com.example.meeqapp.ui.components.HintHeader
import com.example.meeqapp.ui.components.RoundedSelector
import com.example.meeqapp.ui.components.SubHeader
import com.example.meeqapp.ui.theme.Theme
import com.example.meeqapp.ui.thoughts.MediumHeader
import com.example.meeqapp.ui.thoughts.Thought
import com.example.meeqapp.ui.viewmodel.SharedViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun DistortionScreen(
    navigation: NavController,
    thought: Thought? = null,
    sharedViewModel: SharedViewModel = viewModel(),
) {
    val scrollState = rememberScrollState()
    var shouldShowPreviousThought by remember { mutableStateOf(false) }
    var shouldShowDistortions by remember { mutableStateOf(false) }
    val isEditing by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

     fun onPressSlug(selected: Any) {
        val cognitiveDistortions = thought?.cognitiveDistortions?.toMutableList()
        cognitiveDistortions?.let {
            val index = cognitiveDistortions.indexOfFirst { it.slug == selected }

            if (index != -1) {
                cognitiveDistortions[index].selected = !cognitiveDistortions[index].selected
                thought?.cognitiveDistortions = cognitiveDistortions.toList()
            }
        }
    }

    fun saveThought() {
        coroutineScope.launch {
            thought?.let {
                sharedViewModel.saveThought(it)
            }
        }
    }

    fun onFinish() {
        saveThought()
        navigation.navigate(FINISHED_SCREEN)
    }

    fun onNext() {
        saveThought()
        navigation.navigate(CHALLENGE_SCREEN)
    }

    fun onBackToThought() {
        saveThought()
        navigation.navigate(AUTOMATIC_THOUGHT_SCREEN)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.lightOffwhite)
    ) {
        // Content inside ScrollView
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp)
                .background(Theme.lightOffwhite)
                .verticalScroll(scrollState)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Theme.lightOffwhite)
                    .padding(16.dp)
            ) {
                thought.takeIf { it != null }?.let {
                    // Render components only if the thought is not null
                    // ...

                    MediumHeader(title = stringResource(id = R.string.cbt_form_cog_distortion))
                    HintHeader(text = "Is this thought logical?")

                    // Render other components...
                    Column(
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        SubHeader(text = "Your Thought")
                        GhostButtonWithGuts(
                            borderColor = Color.LightGray,
                            onClick = { onBackToThought() }
                        ) {
                            Text(text = thought?.automaticThought ?: "")
                        }

                        SubHeader(text = "Common Distortions")
                        HintHeader(
                            text = "Tap any of these that apply to your current situation."
                        )
                        // Assuming RoundedSelector is a custom Composable
                        // with similar functionality
                        RoundedSelector(
                            items = thought?.cognitiveDistortions, // pass your items here
                            onPress = ::onPressSlug // pass your onPress function here
                        )
                    }
                }
            }
        }

        // Action buttons based on editing state
        if (isEditing) {
            ActionButton(
                title = "Save",
                onClick = { onFinish() },
                style = Modifier.fillMaxWidth()
            )
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                GhostButton(
                    borderColor = Theme.lightGray,
                    textColor = Theme.veryLightText,
                    title = "Back",
                    style = Modifier.weight(1f),
                    onClick = { navigation.navigate(AUTOMATIC_THOUGHT_SCREEN) }
                )
                ActionButton(
                    title = "Next",
                    onClick = { onNext() },
                    style = Modifier.weight(1f)
                )
            }
        }
    }
}

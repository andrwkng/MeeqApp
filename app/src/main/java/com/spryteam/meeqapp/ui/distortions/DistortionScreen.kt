package com.sprytm.meeqapp.ui.distortions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprytm.meeqapp.ui.components.ActionButton
import com.sprytm.meeqapp.ui.components.GhostButton
import com.sprytm.meeqapp.ui.components.GhostButtonWithGuts
import com.sprytm.meeqapp.ui.components.HintHeader
import com.sprytm.meeqapp.ui.components.MediumHeader
import com.sprytm.meeqapp.ui.components.RoundedSelector
import com.sprytm.meeqapp.ui.components.SubHeader
import com.sprytm.meeqapp.ui.theme.Theme
import com.sprytm.meeqapp.ui.viewmodel.SharedViewModel
import com.spryteam.meeqapp.R

@Composable
fun DistortionRoute(
    onNavigateToFinished: () -> Unit,
    onNavigateToChallenge: () -> Unit,
    onNavigateToAutoThought: () -> Unit,
    viewModel: SharedViewModel,
) {
    val autoThought: String by viewModel.automaticThought.collectAsState("")
    val distortions: List<CognitiveDistortion> by viewModel.distortionList.collectAsState(emptyList())

    DistortionScreen(
        distortionList = distortions,
        isEditing = viewModel.isEditing,
        isNextDisabled = viewModel.isNextDisabled,
        onPressSlug = viewModel::onPressSlug,
        autoThoughtVal = autoThought,
        onFinishPressed = onNavigateToFinished,
        onNextPressed = { viewModel.onNext(onNavigateToChallenge) },
        onBackPressed = { viewModel.onNext(onNavigateToAutoThought) }
    )
}

@Composable
fun DistortionScreen(
    distortionList: List<CognitiveDistortion>,
    isEditing: Boolean,
    isNextDisabled: Boolean,
    autoThoughtVal: String,
    onPressSlug: (String) -> Unit,
    onFinishPressed: () -> Unit,
    onNextPressed: () -> Unit,
    onBackPressed: () -> Unit
) {
    val scrollState = rememberScrollState()
    var shouldShowPreviousThought by remember { mutableStateOf(false) }
    var shouldShowDistortions by remember { mutableStateOf(false) }

    fun saveThought() {
        /*coroutineScope.launch {
            thought?.let {
                sharedViewModel.saveThought(it)
            }
        }*/
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
        //.background(Theme.lightOffwhite)
        //.verticalScroll(scrollState)
    ) {
        // Content inside ScrollView
        Column(
            modifier = Modifier
                //.fillMaxSize()
                .background(Theme.lightOffwhite)
                .padding(16.dp)
                .verticalScroll(scrollState)
                .weight(weight = 1f, fill = false)
        ) {
            //thought.takeIf { it != null }?.let {
            // Render components only if the thought is not null
            // ...
            MediumHeader(text = stringResource(id = R.string.cbt_form_cog_distortion))
            HintHeader(text = "Is this thought logical?")

            // Render other components...
            Column(
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                SubHeader(text = "Your Thought")
                GhostButtonWithGuts(
                    borderColor = Color.LightGray,
                    onClick = { onBackPressed() }
                ){Text(autoThoughtVal)}

                SubHeader(text = "Common Distortions")
                HintHeader(
                    text = "Tap any of these that apply to your current situation."
                )
                // Assuming RoundedSelector is a custom Composable
                // with similar functionality
                RoundedSelector(
                    //items = thought?.cognitiveDistortions, // pass your items here
                    items = distortionList,
                    onPress = onPressSlug // pass your onPress function here
                )
            }
            //}
        }

        Row(
            modifier = Modifier
                .padding(12.dp)
                .background(Color.White),
                    horizontalArrangement = Arrangement.End
        ) {
            // Action buttons based on editing state
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
    }
}

@Preview(showBackground = true)
@Composable
fun DistortionScreenPreview() {
    val list = distortions
    list[0].selected = true
    list[2].selected = true
    DistortionScreen(
        distortionList = list,
        isEditing = false,
        isNextDisabled = true,
        autoThoughtVal = "Automatic Thought",
        onPressSlug = { /*TODO*/ },
        onFinishPressed = { /*TODO*/ },
        onNextPressed = { /*TODO*/ }) {

    }
}

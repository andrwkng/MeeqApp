package com.spryteam.meeqapp.ui.thoughts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.spryteam.meeqapp.ui.FeelingScreen
import com.spryteam.meeqapp.ui.FinishedScreen
import com.spryteam.meeqapp.ui.distortions.CognitiveDistortion
import com.spryteam.meeqapp.ui.distortions.DistortionScreen
import com.spryteam.meeqapp.ui.followup.FollowUpScreen

@Composable
fun ThoughtRoute(
    onNavigateToMain: () -> Unit,
    onSaveThought: (SavedThought) -> Unit,
) {
    val viewModel: ThoughtViewModel = hiltViewModel()

    val screeningData = viewModel.screeningData
    val autoThought: String by viewModel.automaticThought.collectAsState("")
    val challenge: String by viewModel.challenge.collectAsState("")
    val altThought: String by viewModel.alternativeThought.collectAsState("")
    val distortions: List<CognitiveDistortion> by viewModel.distortionList.collectAsState(emptyList())


    ThoughtScreen {

        when (screeningData.form) {

            Form.AUTOMATIC_THOUGHT -> {
                AutomaticThoughtScreen(
                    isEditing = viewModel.isEditing,
                    isNextDisable = viewModel.isNextDisabled,
                    autoThoughtVal = autoThought,
                    onAutoThoughtChange = viewModel::onAutoChange,
                    onFinishPressed = { viewModel.onNext(Form.FINISHED) },
                    onNextPressed = { viewModel.onNext(Form.DISTORTION) },
                    onCancelPressed = { onNavigateToMain() }
                )
            }

            Form.DISTORTION -> {
                DistortionScreen(
                    distortionList = distortions,
                    isEditing = viewModel.isEditing,
                    isNextDisabled = viewModel.isNextDisabled,
                    onPressSlug = viewModel::onPressSlug,
                    autoThoughtVal = autoThought,
                    onFinishPressed = { viewModel.onNext(Form.FINISHED) },
                    onNextPressed = { viewModel.onNext(Form.CHALLENGE) },
                    onBackPressed = { viewModel.onNext(Form.AUTOMATIC_THOUGHT) }
                )
            }

            Form.CHALLENGE -> {
                ChallengeScreen(
                    challengeVal = challenge,
                    onChallengeChange = viewModel::onChallengeChange,
                    isEditing = viewModel.isEditing,
                    isNextDisabled = viewModel.isNextDisabled,
                    autoThoughtVal = autoThought,
                    onNavigateToAutoThought = { viewModel.onNext(Form.AUTOMATIC_THOUGHT) },
                    onNextPressed = { viewModel.onNext(Form.ALTERNATIVE) },
                    onBackPressed = { viewModel.onNext(Form.DISTORTION) },
                    onFinishPressed = { viewModel.onNext(Form.FINISHED) },
                )
            }

            Form.ALTERNATIVE -> {
                AlternativeThoughtScreen(
                    isEditing = viewModel.isEditing,
                    isNextDisabled = viewModel.isNextDisabled,
                    altThoughtVal = altThought,
                    onAltThoughtChange = viewModel::onAltThoughtChange,
                    onNextPressed = { viewModel.onNext(Form.FEELING) },
                    onFinishPressed = { viewModel.onNext(Form.FINISHED) },
                    onBackPressed = { viewModel.onNext(Form.CHALLENGE) }
                )
            }

            Form.FEELING -> {
                FeelingScreen(
                    onFeltWorsePressed = { viewModel.onFeltWorse(Form.FOLLOWUP) },
                    onFeltTheSamePressed = { viewModel.onFeltTheSame(Form.FOLLOWUP) },
                    onFeltBetterPressed = { viewModel.onFeltBetter(Form.FOLLOWUP) }
                )
            }

            Form.FOLLOWUP -> {
                FollowUpScreen(
                    onContinue = { viewModel.onContinue() },
                    onSetCheckup = { viewModel.onSetCheckup() }
                )
            }

            Form.FINISHED -> {
                FinishedScreen(
                    automaticThought = autoThought,
                    challenge = challenge,
                    cognitiveDistortions = distortions,
                    alternativeThought = altThought,
                    followUpNote = viewModel.followUpNote ?: "",
                    followUpState = viewModel.followUpState(),
                    createdAt = viewModel.createdAt.toString(),
                    onAutoThoughtPressed = { viewModel.onNext(Form.AUTOMATIC_THOUGHT) },
                    onDistortionsPressed = { viewModel.onNext(Form.DISTORTION) },
                    onChallengePressed = { viewModel.onNext(Form.CHALLENGE) },
                    onAltThoughtPressed = { viewModel.onNext(Form.ALTERNATIVE) },
                    onFollowUpPressed = { viewModel.onFeltWorse(Form.FOLLOWUP) },
                    onDeletePressed = {
                        viewModel.deleteThought()
                        onNavigateToMain()
                    },
                    onRepeatPressed = { viewModel.onNext(Form.AUTOMATIC_THOUGHT) },
                    onFinishPressed = {
                        viewModel.onFinishedNext(onSaveThought)
                        onNavigateToMain()
                    }
                )
            }
        }

    }
}

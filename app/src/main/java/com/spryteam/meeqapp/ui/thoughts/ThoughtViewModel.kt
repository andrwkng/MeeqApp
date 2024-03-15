package com.spryteam.meeqapp.ui.thoughts

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spryteam.meeqapp.data.CheckupStore
import com.spryteam.meeqapp.data.ThoughtStore
import com.spryteam.meeqapp.data.newSavedThought
import com.spryteam.meeqapp.ui.distortions.newDistortions
import com.spryteam.meeqapp.ui.distortions.update
import com.spryteam.meeqapp.ui.viewmodel.getFollowUpTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ThoughtViewModel @Inject constructor(
    private val thoughtStore: ThoughtStore,
    private val checkupStore: CheckupStore
) : ViewModel() {
    private val _thought = mutableStateOf<SavedThoughtImpl?>(null)
    val thought: SavedThought?
        get() = _thought.value

    private val _createdAt = mutableStateOf(LocalDate.now())
    val createdAt: LocalDate
        get() = _createdAt.value


    // Feeling
    private val _checkup = mutableStateOf<ImmediateCheckup?>(null)

    fun onFeltWorse(form: Form) {
        saveCheckup(ImmediateCheckup.WORSE)
        _screeningData.value = createScreeningData(form)
    }

    fun onFeltTheSame(form: Form) {
        saveCheckup(ImmediateCheckup.SAME)
        _screeningData.value = createScreeningData(form)
    }

    fun onFeltBetter(form: Form) {
        saveCheckup(ImmediateCheckup.BETTER)
        _screeningData.value = createScreeningData(form)
    }

    private fun saveCheckup(feeling: ImmediateCheckup) {
        _checkup.value = feeling
    }

    // FollowUp
    private val _followUpCompleted = mutableStateOf<Boolean?>(null)
    private val followUpCompleted: Boolean?
        get() = _followUpCompleted.value

    private val _followUpNote = mutableStateOf<String?>(null)
    val followUpNote: String?
        get() = _followUpNote.value

    private val _followUpDate = mutableStateOf<String?>(null)
    private val followUpDate: String?
        get() = _followUpDate.value

    val navigateToFinished = mutableStateOf({})
    fun onContinue() {
        _screeningData.value = createScreeningData(Form.FINISHED)
    }

    fun onSetCheckup() {
        _followUpDate.value = getFollowUpTime()
        onContinue()
    }

    // Distortions
    private val _distortions = MutableStateFlow(newDistortions())
    val distortionList = _distortions.asStateFlow()

    fun onPressSlug(selectedSlug: String) {
        val list = _distortions.value
        list.let {
            val index = list.indexOfFirst { it.slug == selectedSlug }

            _distortions.value = list.update(index, selected = !list[index].selected)
        }
    }

    // Challenge
    private val _challenge = MutableStateFlow("")

    val challenge: StateFlow<String> = _challenge.asStateFlow()

    fun onChallengeChange(value: String) {
        _challenge.value = value
        _isNextDisabled.value = false
    }

    // Automatic Thought
    private val _isNextDisabled = mutableStateOf(true)
    val isNextDisabled: Boolean
        get() = _isNextDisabled.value

    private val _autoThought = MutableStateFlow("")
    val automaticThought: StateFlow<String> = _autoThought.asStateFlow()

    private val _isEditing = mutableStateOf(false)
    val isEditing: Boolean
        get() = _isEditing.value

    // Alternative Thought
    private val _altThought = MutableStateFlow("")
    val alternativeThought: StateFlow<String> = _altThought.asStateFlow()

    fun onAltThoughtChange(value: String) {
        _altThought.value = value
        _isNextDisabled.value = false
    }

    val shouldFadeIn = mutableStateOf(false)
    val shouldPromptCheckup = mutableStateOf(false)

    private val _immediateCheckup = mutableStateOf<ImmediateCheckup?>(null)
    private val _followUpCheckup = mutableStateOf<String?>(null)
    private val _uuid = mutableStateOf<String?>(null)

    private fun getCurrentThought(): SavedThought {
        return newSavedThought(
            automaticThought = automaticThought.value,
            alternativeThought = alternativeThought.value,
            cognitiveDistortions = distortionList.value,
            challenge = challenge.value,
            immediateCheckup = _immediateCheckup.value,
            followUpDate = followUpDate,
            followUpCompleted = followUpCompleted,
            followUpCheckup = _followUpCheckup.value,
            followUpNote = followUpNote,
        )
    }

    fun deleteThought() {}

    fun onAutoChange(value: String) {
        _autoThought.value = value
        _isNextDisabled.value = false
    }

    fun onLoad(loadExercises: () -> Unit) {
        viewModelScope.launch {
            loadExercises()
            loadShouldPromptCheckup()
            // Simulating setTimeout with delay
            delay(150)
            shouldFadeIn.value = true
        }
    }

    fun onNext(form: Form) {
        _screeningData.value = createScreeningData(form)
    }

    private fun loadShouldPromptCheckup() {
        viewModelScope.launch {
            val date = checkupStore.getNextCheckupDate()
            shouldPromptCheckup.value = LocalDate.parse(date.toString()).isAfter(LocalDate.now())
        }
    }

    fun onRepeat(navigate: () -> Unit) {
        navigate()
    }

    fun onFinishedNext(saveThought: (SavedThought) -> Unit) {
        if (followUpState() == FollowUpState.READY) {
            _followUpCompleted.value = true
        }

        saveThought(getCurrentThought())
    }

    fun followUpState(): FollowUpState {

        followUpDate?.let {
            val isInFuture = LocalDateTime.parse(followUpDate).isAfter(LocalDateTime.now())
            if (isInFuture) {
                return FollowUpState.SCHEDULED
            }

            followUpCompleted?.let {
                if (!isInFuture && !it) {
                    return FollowUpState.READY
                }
            }
        }


        return FollowUpState.NONE
    }

    fun navigateToViewerWithThought(
    ) {
        // Follow-ups
        when (followUpState()) {
            FollowUpState.READY -> {
                return
            }
            // Regular finished screen
            else -> {
            }
        }
    }

    suspend fun countThoughts(): Int {
        return thoughtStore.countThoughts()
    }

    suspend fun getOrderedThoughts(): List<SavedThought> {
        return thoughtStore.getOrderedThoughts()
    }


    private val _screeningData = mutableStateOf(createScreeningData())
    val screeningData: FormScreeningData
        get() = _screeningData.value

    private fun createScreeningData(form: Form = Form.AUTOMATIC_THOUGHT): FormScreeningData {
        return FormScreeningData(
            title = "",
            shouldShowPreviousButton = false,
            shouldShowDoneButton = false,
            form = form,
        )
    }
}

enum class Form {
    AUTOMATIC_THOUGHT,
    DISTORTION,
    CHALLENGE,
    ALTERNATIVE,
    FINISHED,
    FEELING,
    FOLLOWUP,
}

data class FormScreeningData(
    val title: String,
    val shouldShowPreviousButton: Boolean,
    val shouldShowDoneButton: Boolean,
    val form: Form,
)
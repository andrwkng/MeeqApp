package com.spryteam.meeqapp.ui.thoughts

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spryteam.meeqapp.data.CheckupStore
import com.spryteam.meeqapp.data.ThoughtStore
import com.spryteam.meeqapp.data.getThoughtKey
import com.spryteam.meeqapp.domain.ScheduleFollowUpUseCase
import com.spryteam.meeqapp.ui.distortions.newDistortions
import com.spryteam.meeqapp.ui.distortions.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ThoughtViewModel @Inject constructor(
    private val thoughtStore: ThoughtStore,
    private val checkupStore: CheckupStore,
    val getFollowUpTime: ScheduleFollowUpUseCase
) : ViewModel() {
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

    private val _followUpNote = MutableStateFlow("")
    val followUpNote: StateFlow<String>
        get() = _followUpNote.asStateFlow()

    fun onFollowUpNoteChange(value: String) {
        _followUpNote.value = value
        _isNextDisabled.value = false
    }

    private val _followUpDate = mutableStateOf<String?>(null)
    private val followUpDate: String?
        get() = _followUpDate.value

    val navigateToFinished = mutableStateOf({})
    fun onContinue() {
        _screeningData.value = createScreeningData(Form.FINISHED)
    }

    fun onSetCheckup() {
        _followUpDate.value = getFollowUpTime().format(DateTimeFormatter.ISO_DATE_TIME)
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
    private val _updatedAt = mutableStateOf<LocalDate>(LocalDate.now())
    private val _uuid = mutableStateOf(getThoughtKey(UUID.randomUUID().toString()))
    val uuid: String
        get() = _uuid.value

    fun getThought(): SavedThought {
        return SavedThoughtImpl(
            automaticThought = automaticThought.value,
            alternativeThought = alternativeThought.value,
            cognitiveDistortions = distortionList.value,
            challenge = challenge.value,
            immediateCheckup = _immediateCheckup.value,
            followUpDate = followUpDate,
            followUpCompleted = followUpCompleted,
            followUpCheckup = _followUpCheckup.value,
            followUpNote = followUpNote.value,
            createdAt = createdAt,
            updatedAt = _updatedAt.value,
            uuid = _uuid.value
        )
    }

     fun setThoughtItems(thought: SavedThought) {
        _autoThought.value = thought.automaticThought
        _altThought.value = thought.alternativeThought
        _distortions.value = thought.cognitiveDistortions
        _challenge.value = challenge.value
        _immediateCheckup.value = thought.immediateCheckup
        _followUpDate.value = thought.followUpDate
        _followUpCompleted.value = thought.followUpCompleted
        _followUpCheckup.value = thought.followUpCheckup
        _followUpNote.value = thought.followUpNote
        _createdAt.value = thought.createdAt
        _updatedAt.value = thought.updatedAt
        _uuid.value = thought.uuid
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

    fun onFinishedNext() {
        if (followUpState() == FollowUpState.READY) {
            _followUpCompleted.value = true
        }
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

    var navigateToThought: () -> Unit = {}

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
    FOLLOW_UP,
    FOLLOW_UP_NOTE
}

data class FormScreeningData(
    val title: String,
    val shouldShowPreviousButton: Boolean,
    val shouldShowDoneButton: Boolean,
    val form: Form,
)
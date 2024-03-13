package com.spryteam.meeqapp.ui.thoughts

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spryteam.meeqapp.data.CheckupStore
import com.spryteam.meeqapp.data.ThoughtStore
import com.spryteam.meeqapp.data.newSavedThought
import com.spryteam.meeqapp.ui.distortions.distortions
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
    //private val _thought = mutableStateOf<SavedThought?>(null)
    private val _thought = mutableStateOf<SavedThoughtImpl?>(null)
    val thought: SavedThought?
        get() = _thought.value

    private val _createdAt = mutableStateOf(LocalDate.now())
    val createdAt: LocalDate
        get() = _createdAt.value

    private val _updatedAt = mutableStateOf(LocalDate.now())
    val updatedAt: LocalDate
        get() = _updatedAt.value

    // Feeling
    private val _checkup = mutableStateOf<ImmediateCheckup?>(null)

    fun onFeltWorse(navigate: () -> Unit) {
        saveCheckup(ImmediateCheckup.WORSE)
        navigate()
    }

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
        //saveThought()
    }

    fun onFeltTheSame(navigate: () -> Unit) {
        saveCheckup(ImmediateCheckup.SAME)
        navigate()
    }

    fun onFeltBetter(navigate: () -> Unit) {
        saveCheckup(ImmediateCheckup.BETTER)
        navigate()
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
        Log.i("onSetCheckup() followUpDate:", _followUpDate.value.toString())
        //saveThought()
        onContinue()
    }

    // Distortions
    private val _distortions = MutableStateFlow(distortions)
    val distortionList = _distortions.asStateFlow()

    fun onPressSlug(selected: Any) {
        val list = _distortions.value
        list.let {
            val index = list.indexOfFirst { it.slug == selected }

            if (index != -1) {
                list[index].selected = !list[index].selected
                //distortionList = cognitiveDistortions.toList()
                _distortions.value = list
            }
        }
    }

    // Challenge
    private val _challenge = MutableStateFlow("")
    //val challenge: StateFlow<String> = MutableStateFlow(thought?.challenge).asStateFlow()

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
        //_isNextDisabled.value = false
    }

    val shouldFadeIn = mutableStateOf(false)
    val shouldPromptCheckup = mutableStateOf(false)

    private val _isExistingUser = MutableStateFlow(false)
    //val isExistingUser = _isExistingUser.asStateFlow()

    init {
        viewModelScope.launch {
            val userExists = thoughtStore.getIsExistingUser()
            userExists.collect {
                _isExistingUser.value = it
            }
        }
    }

    private val _immediateCheckup = mutableStateOf<ImmediateCheckup?>(null)
    private val _followUpCheckup = mutableStateOf<String?>(null)
    private val _uuid = mutableStateOf<String?>(null)

    /*private fun newThought() {
        _thought.value = newSavedThought()
        *//*val thought = _thought.value
        thought?.automaticThought = "'"
        _autoThought.value = thought.automaticThought
        _challenge.value = thought.challenge
        _altThought.value = thought.alternativeThought
        _distortions.value = thought.cognitiveDistortions
        _immediateCheckup.value = thought.immediateCheckup
        _followUpDate.value = thought.followUpDate
        _followUpCompleted.value = thought.followUpCompleted
        _followUpCheckup.value = thought.followUpCheckup
        _followUpNote.value = thought.followUpNote
        _createdAt.value = LocalDate.now()
        _updatedAt.value = LocalDate.now()
        _uuid.value = thought.uuid*//*

    }*/

    private fun getCurrentThought(): SavedThought {
        //return thought as SavedThought
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
            //createdAt = createdAt,
            //updatedAt = updatedAt,
            //uuid = _uuid.value as String
        )
    }

    /*fun newAutoThought(navigate: () -> Unit) {
        //newThought()
        //Log.i("newAutoThought: newThought()", _updatedAt.value.toString())
        navigate()
    }*/

    fun deleteThought() {}

    fun onAutoChange(value: String) {
        _autoThought.value = value
        _isNextDisabled.value = false
        //thought.value?.automaticThought = label
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
        //saveThought("New Thought")
        //navigation.navigate(DISTORTION_SCREEN)
        _screeningData.value = createScreeningData(form)
    }

    private fun loadShouldPromptCheckup() {
        viewModelScope.launch {
            val date = checkupStore.getNextCheckupDate()
            shouldPromptCheckup.value = LocalDate.parse(date.toString()).isAfter(LocalDate.now())
        }
    }

    fun onRepeat(navigate: () -> Unit) {
        //val newThought = NewThought(automaticThought = thought?.automaticThought!!)
        //updateThought(newThought)
        //navigation.navigate(AUTOMATIC_THOUGHT_SCREEN)
        navigate()
    }

    fun onFinishedNext(saveThought: (SavedThought) -> Unit) {
        if (followUpState() == FollowUpState.READY) {
            _followUpCompleted.value = true
        }
        //Log.i("onFinishedNext: saveThought()", _updatedAt.value.toString())

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
        //navigation: NavController,
        //viewModel: SharedViewModel = viewModel()
    ) {
        //viewModel.thought.value = thought
        // Follow-ups
        when (followUpState()) {
            FollowUpState.READY -> {
                //navigation.navigate(FOLLOW_UP_NOTE_SCREEN)
                return
            }
            // Regular finished screen
            else -> {
                //navigation.navigate(FINISHED_SCREEN)
            }
        }
    }

    fun setIsExistingUser() {
        viewModelScope.launch {
            thoughtStore.setIsExistingUser()
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

    private fun createScreeningData(form: Form = Form.AUTOMATIC_THOUGHT,): FormScreeningData {
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
package com.sprytm.meeqapp.ui.thoughts

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sprytm.meeqapp.data.CheckupStore
import com.sprytm.meeqapp.data.NewThought
import com.sprytm.meeqapp.data.ThoughtStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ThoughtViewModel @Inject constructor(
    private val thoughtStore: ThoughtStore,
    private val checkupStore: CheckupStore
) : ViewModel() {
    private val _thought = mutableStateOf<SavedThought?>(null)
    /*val thought: SavedThought?
        get() = _thought.value*/

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

    fun newThought() {
        _thought.value = NewThought()
    }

    fun getThought(): SavedThought {
        return NewThought(
            //automaticThought = automaticThought,
            alternativeThought = alternativeThought.value,
            //cognitiveDistortions = emptyList(),
            //challenge = "",
            //immediateCheckup = null,
            //followUpDate = null,
            //followUpCompleted = null,
            //followUpCheckup = null,
            //followUpNote = null,
            //createdAt = LocalDate,
            //updatedAt = LocalDate,
            //uuid = String
        )
    }

    fun deleteThought() {

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

    fun onFinishedNext(navigate: () -> Unit) {
        if (followUpState() == FollowUpState.READY) {
            //val oldThought = thought!!
            val oldThought = _thought.value
            //_followUpCompleted.value = true
            //saveThought(oldThought) }
        }
        navigate()
    }

    fun followUpState(): FollowUpState {
        val thought = _thought.value
        if (thought != null) {
            if (thought.followUpDate == null) {
                return FollowUpState.NONE
            }

            val isInFuture = LocalDate.parse(thought.followUpDate).isAfter(LocalDate.now())
            if (isInFuture) {
                return FollowUpState.SCHEDULED
            }

            if (!isInFuture && !thought.followUpCompleted!!) {
                return FollowUpState.READY
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
}
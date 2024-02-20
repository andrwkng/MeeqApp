package com.example.meeqapp.ui.thoughts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meeqapp.data.ThoughtStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThoughtViewModel @Inject constructor(
    private val  thoughtStore: ThoughtStore
): ViewModel() {
    private val _isExistingUser = MutableStateFlow(false)
    val isExistingUser = _isExistingUser.asStateFlow()

    init {
        viewModelScope.launch {
            val userExists = thoughtStore.getIsExistingUser()
            userExists.collect{
                _isExistingUser.value = it
            }
        }
    }

    fun setIsExistingUser() {
        viewModelScope.launch {
            thoughtStore.setIsExistingUser()
        }
    }

    suspend fun hasSeenPredictionOnboarding() {
        thoughtStore.setSeenPredictionOnboarding(true)
    }

    suspend fun countThoughts(): Int {
        return thoughtStore.countThoughts()
    }
}
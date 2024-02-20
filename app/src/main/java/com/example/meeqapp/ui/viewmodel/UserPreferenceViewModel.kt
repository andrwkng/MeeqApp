package com.example.meeqapp.ui.viewmodel

import com.example.meeqapp.data.Flag
import com.example.meeqapp.data.FlagStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import java.util.UUID

/*
class UserPreferenceViewModel() : ViewModel() {
        *//*fun hasSeenPredictionOnboardingFlow(context: Context): Flow<Boolean> {
            return FlagStore.getFlag(Flag.HAS_SEEN_PREDICTION_ONBOARDING, false, context)
        }*//*

    companion object {
        fun hasSeenPredictionOnboardingFlow(context: Context): Flow<Boolean> {
            return FlagStore.getFlag(Flag.HAS_SEEN_PREDICTION_ONBOARDING, false, context)
        }
    }
}*/

const val USER_ID_KEY = "@IDStore:userID"
const val CHECKUP_SCHEDULE_KEY = "@Quirk:next-checkup-date"

//val datastore = Application().applicationContext.dataStore

//class UserPreferenceViewModel(private val flagStore: FlagStore) : ViewModel() {
class UserPreferenceStore(private val flagStore: FlagStore) {
    //private val flagStore: FlagStore = FlagStore(datastore)
    val hasBeenSurveyed: Flow<Boolean> = flagStore.getFlag(Flag.HAS_BEEN_SURVEYED)

    val getCheckUpScheduleKey: Flow<String?> = flagStore.getFlag(CHECKUP_SCHEDULE_KEY)

    /*val hasSeenPredictionOnboardingFlow: Flow<Boolean> =
        flagStore.getFlag(Flag.HAS_SEEN_PREDICTION_ONBOARDING)*/

    val hasRatedFalse: Boolean? = flagStore.getFlag("has-rated", false)

    /*suspend fun setSeenPredictionOnboarding(bool: Boolean) {
        flagStore.setFlag(Flag.HAS_SEEN_PREDICTION_ONBOARDING, bool)
    }*/

    suspend fun setHasBeenSurveyedTrue() {
        flagStore.setTrue(Flag.HAS_BEEN_SURVEYED)
    }

    private suspend fun setUserID(id: String) {
        flagStore.setFlag(USER_ID_KEY, id)
    }

    private val getUserID: Flow<String?> = flagStore.getFlag(USER_ID_KEY)


    suspend fun getUserID(): Flow<String?> {
        var id = getUserID

        id.collect { value ->
            if (value.isNullOrEmpty()) {
                runBlocking {
                    identify()
                }
                id = getUserID
            }
        }

        return id
    }

    private suspend fun identify() {
        val userID = "user-" + UUID.randomUUID().toString()

        setUserID(userID)
    }

    /*companion object {
        @Composable
        fun Factory(
            flagStore: FlagStore = FlagStore(LocalContext.current),
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return UserPreferenceViewModel(flagStore) as T
            }
        }
    }*/

    /*companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                extras.createSavedStateHandle()
                val context = application.applicationContext

                return UserPreferenceViewModel(
                    FlagStore(context)
                ) as T
            }
        }

        *//*fun Factory(flagStore: FlagStore): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                return UserPreferenceViewModel(
                    flagStore
                ) as T
            }
        }*//*
    }*/


    /*companion object {
        @Composable
        fun provideFactory(
            flagStore: FlagStore = FlagStore(LocalContext.current.dataStore),
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return UserPreferenceViewModel(flagStore) as T
            }
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
            ): T {
                val appContext: Context = application.applicationContext
                return UserPreferenceViewModel(
                    FlagStore(LocalContext.current.dataStore)
                ) as T
            }
        }
    }*/

}

/*
class XUserPreferenceViewModel(
    private val dataStorePreferenceRepository: DataStorePreferenceRepository
) : ViewModel() {
    private val _preferences = MutableLiveData(1)
    var preferences: LiveData<Int> = _preferences

    init {
        viewModelScope.launch {
            dataStorePreferenceRepository.getLanguage.collect {
                _preferences.value = it
            }
        }
    }

    suspend fun saveLanguage(preferences: Int) {
        dataStorePreferenceRepository.setLanguage(preferences)
    }
}*/

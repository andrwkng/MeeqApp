package com.spryteam.meeqapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.spryteam.meeqapp.ui.predictions.Prediction
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

enum class Flag {
    START_HELP_BADGE,
    HAS_RATED,
    HAS_BEEN_SURVEYED,
    HAS_SEEN_PREDICTION_ONBOARDING
}

enum class HistoryButtonLabelSetting {
    ALTERNATIVE_THOUGHT,
    AUTOMATIC_THOUGHT
}

//class FlagStore(private val dataStore: DataStore<Preferences>) {
class FlagStore @Inject constructor(
    @ApplicationContext context: Context
) {
    private val dataStore = context.dataStore

    suspend fun setFlag(flag: Flag, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(flag.name)] = value
        }
    }

    suspend fun setFlag(flag: String, value: String = "") {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(flag)] = value
        }
    }

    fun getFlag(flag: Flag, defaultValue: Boolean = false): Flow<Boolean> {
        val key = booleanPreferencesKey(flag.name)
        return dataStore.data.map { preferences ->
            preferences[key] ?: defaultValue
        }
    }

    fun getFlag(flag: String, expected: Boolean): Boolean? {
        val key = booleanPreferencesKey(flag)
        var result :Boolean? = null
        dataStore.data.map { preferences ->
            result = preferences[key] == expected
        }
        return result
    }

    // fun getFlag(flag: String, defaultValue: String = ""): Flow<String> {
    fun getFlag(flag: String): Flow<String?> {
            val key = stringPreferencesKey(flag)
        return dataStore.data.map { preferences ->
            preferences[key] ?: null
        }
    }

    fun getFlags(flag: String): Flow<Boolean> {
        val list : List<Prediction>
        val key = stringPreferencesKey(flag)
        return dataStore.data.map { preferences ->
            preferences.contains(key)
        }
    }

    fun getAllFlags(): Flow<Preferences> {
        return dataStore.data
    }

    suspend fun setTrue(flag: Flag) {
        setFlag(flag, true)
    }
}

/*class FlagStore(private val dataStore: DataStore<Preferences>) {

    suspend fun setFlag(flag: Flag, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(flag.name)] = value
        }
    }

    fun getFlag(flag: Flag, defaultValue: Boolean): Flow<Boolean> {
        val key = booleanPreferencesKey(flag.name)
        return dataStore.data.map { preferences ->
            preferences[key] ?: defaultValue
        }
    }
}*/

/*class DataStorePreference(context: Context) {
    private val dataStore: DataStore<Preferences> = context.createDataStore(name = "LanguageData")
    private val defaultLanguage = 1

    companion object {
        val PREF_LANGUAGE = preferencesKey<Int>("language")
        private var INSTANCE: DataStorePreference? = null

        fun getInstance(context: Context): DataStorePreference {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return it
                }
                val instance = DataStorePreference(context)
                INSTANCE = instance
                instance
            }
        }
    }

    suspend fun setLanguage(language: Int) {
        dataStore.edit { preferences ->
            preferences[PREF_LANGUAGE] = language
        }
    }

    val getLanguage: Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[PREF_LANGUAGE] ?: defaultLanguage
        }
}*/

/*class FlagStore {

    companion object {
        suspend fun setFlag(flag: Flag, value: Boolean, context: Context) {
            context.dataStore.edit { preferences ->
                preferences[booleanPreferencesKey(flag.name)] = value
            }
        }

        fun getFlag(flag: Flag, defaultValue: Boolean, context: Context): Flow<Boolean> {
            val key = booleanPreferencesKey(flag.name)
            return context.dataStore.data.map { preferences ->
                preferences[key] ?: defaultValue
            }
        }

        suspend fun getSycFlag(flag: Flag, defaultValue: Boolean, context: Context): Boolean {
            return getFlag(flag, defaultValue, context).first()
        }

        fun setTrue(s: Flag) {

        }
    }
}*/

/*class FlagStorex(private val dataStore: DataStore<Preferences>) {

    suspend fun setFlag(flag: Flag, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(flag.name)] = value
        }
    }

    fun getFlag(flag: Flag, defaultValue: Boolean): Flow<Boolean> {
        val key = booleanPreferencesKey(flag.name)
        return dataStore.data.map { preferences ->
            preferences[key] ?: defaultValue
        }
    }
}*/


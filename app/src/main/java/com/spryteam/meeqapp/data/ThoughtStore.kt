package com.spryteam.meeqapp.data

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.spryteam.meeqapp.ui.distortions.CognitiveDistortion
import com.spryteam.meeqapp.ui.distortions.distortions
import com.spryteam.meeqapp.ui.thoughts.ImmediateCheckup
import com.spryteam.meeqapp.ui.thoughts.SavedThought
import com.spryteam.meeqapp.ui.thoughts.SavedThoughtImpl
import com.spryteam.meeqapp.ui.thoughts.Thought
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.time.LocalDate
import java.util.Date
import java.util.UUID
import javax.inject.Inject

val THOUGHTS_KEY_PREFIX = "@Meeq:thoughts:";
val EXISTING_USER_KEY = "@Meeq:existing-user";

class ThoughtStore @Inject constructor(
    @ApplicationContext appContext: Context,
) {
    private val dataStore = appContext.dataStore

    suspend fun setIsExistingUser() {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(EXISTING_USER_KEY)] = true
        }
    }


    suspend fun getOrderedThoughts(): List<SavedThought> {
        val data = getThoughts()
        val thoughts = parseThoughts(data)

        return thoughts.sortedByDescending {
            it.createdAt?.let { date -> Date(date as Long).time } ?: 0
        }
    }

    suspend fun saveThought(thought: Thought): Thought {
        var saveableThought: SavedThought

        val isSavedThought = (thought as? SavedThought)?.uuid == null
        if (isSavedThought) {
            saveableThought = SavedThoughtImpl(
                uuid = getThoughtKey(UUID.randomUUID().toString()),
                createdAt = LocalDate.now(),
                updatedAt = LocalDate.now(),
                alternativeThought = thought.alternativeThought,
                automaticThought = thought.automaticThought,
                challenge = thought.challenge,
                cognitiveDistortions = thought.cognitiveDistortions,
                immediateCheckup = thought.immediateCheckup,
                followUpNote = thought.followUpNote
            )
        } else {
            saveableThought = thought as SavedThought
            saveableThought.updatedAt = LocalDate.now()
        }

        return withContext(Dispatchers.IO) {
            try {
                val thoughtString = saveableThought.toString()

                // No matter what, we NEVER save bad data.
                if (thoughtString.isNullOrBlank()) {
                    return@withContext saveableThought
                }

                dataStore.edit { preferences ->
                    preferences[stringPreferencesKey(saveableThought.uuid)] = thoughtString
                }
                return@withContext saveableThought
            } catch (error: Throwable) {
                Log.e("saveThought", "Error saving thought", error)
                return@withContext saveableThought
            }
        }
    }

    suspend fun getThoughts(): List<String> {
        try {
            var keys = listOf<Any>()
            dataStore.data.map { preferences ->
                preferences.asMap().keys.filter { it.toString().startsWith(THOUGHTS_KEY_PREFIX) }
            }.collect {
                keys = it
            }

            val rows = dataStore.data.map { preferences ->
                preferences.asMap()
                    .filterKeys { keys.contains(it) }
                    .map { it.value.toString() }
            }

            // It's better to lose data than to brick the app
            // (though losing data is really bad too)
            return rows.firstOrNull() ?: emptyList()
        } catch (error: Exception) {
            Log.e("getThoughts", "Error retrieving thoughts", error)
            return emptyList()
        }
    }

    suspend fun countThoughts(): Int {
        val exercises = getThoughts()
        return exercises.size
    }
}

fun newSavedThought(
    automaticThought: String = "",
    challenge: String = "",
    alternativeThought: String = "",
    cognitiveDistortions: List<CognitiveDistortion> = distortions.map {
        CognitiveDistortion(
            label = it.label,
            slug = it.slug,
            description = it.description
        )
    },
    immediateCheckup: ImmediateCheckup? = null,
    followUpDate: String? = null,
    followUpCompleted: Boolean? = null,
    followUpCheckup: String? = null,
    followUpNote: String = "",
    createdAt: LocalDate = LocalDate.now(),
    updatedAt: LocalDate = LocalDate.now(),
    uuid: String = getThoughtKey(UUID.randomUUID().toString()),
): SavedThoughtImpl {
    return SavedThoughtImpl(
        automaticThought = automaticThought,
        cognitiveDistortions = cognitiveDistortions,
        challenge = challenge,
        alternativeThought = alternativeThought,
        immediateCheckup = immediateCheckup,
        followUpDate = followUpDate,
        followUpCompleted = followUpCompleted,
        followUpCheckup = followUpCheckup,
        followUpNote = followUpNote,
        createdAt = createdAt,
        updatedAt = updatedAt,
        uuid = uuid,
    )
}

fun getThoughtKey(info: String): String {
    return THOUGHTS_KEY_PREFIX + info
}

fun parseThoughts(data: List<String>): List<SavedThought> {

    return data
        //.map { value -> Json.decodeFromString<SavedThoughtImpl>(if(value.isNullOrEmpty()) value else "{}") } // Worst case scenario, if bad data gets in we don't show it.
        .mapNotNull { value ->
            try {
                Json.decodeFromString<SavedThoughtImpl>(value)
            } catch (e: Exception) {
                null
            }
        }
}

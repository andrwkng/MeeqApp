package com.spryteam.meeqapp.data

import androidx.datastore.preferences.core.stringPreferencesKey
import com.spryteam.meeqapp.ui.predictions.Prediction
import com.spryteam.meeqapp.ui.predictions.PredictionImpl
import com.spryteam.meeqapp.ui.predictions.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject

const val PREDICTION_KEY_PREFIX = "@Meeq:predictions:"

class PredictionStore @Inject constructor(
    private val flagStore: FlagStore
) {
    fun newPrediction(): Prediction {
        val date = LocalDate.now()
        val uuid = UUID.randomUUID().toString()
        return PredictionImpl(uuid, date, date)
    }

    suspend fun savePrediction(prediction: Prediction) {
        /*try {
            if (prediction.uuid == null) {
                throw Error("No uuid on prediction, not storing")
            }
            prediction.updatedAt = LocalDate.now()

            flagStore.setFlag(prediction.uuid, json.encodeToString(prediction as PredictionImpl))

        } catch (err: Throwable) {
            Log.e("savePrediction", "Error saving prediction", err)
        }*/
    }

    /*val getOrderedPredictions: Flow<Boolean> =
        flagStore.getFlags(PREDICTION_KEY_PREFIX)*/

    fun getOrderedPredictions(): List<Prediction> {
            val keys = flagStore.getAllFlags()
            val checkupKeys = keys.filter { key -> key.contains(stringPreferencesKey(
                PREDICTION_KEY_PREFIX
            )) }
        val predictions = mutableListOf<Prediction>()

        checkupKeys.onEach { result ->
            flagStore.getFlag(result.toString()).collect{value ->
                value?.let { predictions.add(json.decodeFromString(value)) }
            }
        }

        return predictions
    }

    val hasSeenPredictionOnboarding: Flow<Boolean> =
        flagStore.getFlag(Flag.HAS_SEEN_PREDICTION_ONBOARDING, false)

    suspend fun setSeenPredictionOnboarding(bool: Boolean) {
        flagStore.setFlag(Flag.HAS_SEEN_PREDICTION_ONBOARDING, bool)
    }
}




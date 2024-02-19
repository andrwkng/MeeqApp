package com.example.meeqapp.ui.predictions

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meeqapp.data.FlagStore
import kotlinx.serialization.encodeToString
import java.time.LocalDate
import java.util.UUID

fun newPrediction(): Prediction {
    val date = LocalDate.now()
    val uuid = UUID.randomUUID().toString()
    return PredictionImpl(uuid, date, date)
}

fun getOrderedPredictions(): List<Prediction> {
    /*return try {
        val keys = AsyncStorage.getAllKeys().await()
        val checkupKeys = keys.filter { key -> key.startsWith(PREDICTION_KEY_PREFIX) }

        val data = checkupKeys.map { key ->
            async(Dispatchers.IO) { key to AsyncStorage.getItem(key).await() }
        }.awaitAll()

        val exercises = data.map { (_, value) -> JSONObject(value).parsePrediction() }

        return exercises.sortedBy { prediction ->
            dayjs(prediction.date).isAfter(dayjs(prediction.date)) // Assuming Prediction has a "date" property
        }
    } catch (err: Throwable) {
        emptyList()
    }*/
    return emptyList()
}

const val PREDICTION_KEY_PREFIX = "@Meeq:predictions:"

class PredictionsViewModel(private val flagStore: FlagStore) : ViewModel() {

    suspend fun savePrediction(prediction: Prediction) {
        try {
            if (prediction.uuid == null) {
                throw Error("No uuid on prediction, not storing")
            }
            prediction.updatedAt = LocalDate.now()

            flagStore.setFlag(prediction.uuid, json.encodeToString(prediction as PredictionImpl))

        } catch (err: Throwable) {
            Log.e("savePrediction", "Error saving prediction", err)
        }
    }

/*val getOrderedPredictions: Flow<Boolean> =
        flagStore.getFlags(PREDICTION_KEY_PREFIX)

    fun getOrderedPredictions(): List<Prediction> {
//            val keys = flagStore.getAllFlags()
//            val checkupKeys = keys.filter { key -> key.contains(stringPreferencesKey(PREDICTION_KEY_PREFIX)) }

val data = checkupKeys.map { key ->
                val valueFlow: Flow<String> = flagStore.getFlag(key.toString())
                //var myValue : String
                viewModelScope.launch {
                    valueFlow.collect { value ->
                        key to value
                        value is Prediction

                    }
                }
            }


           // val exercises = data.map { (_, value) -> JSONObject(value).parsePrediction() }

        //val exercises = data.map { (_, value) -> parsePrediction(value) }

        return emptyList()

    }*/

    companion object {
        @Composable
        fun provideFactory(
            flagStore: FlagStore = FlagStore(LocalContext.current),
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PredictionsViewModel(flagStore) as T
            }
        }
    }
}



package com.example.meeqapp.ui.pulse

import androidx.lifecycle.ViewModel
import com.example.meeqapp.data.FlagStore
import kotlinx.coroutines.flow.first
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

const val KEY_BOOST_QUEUE = "@Quirk:pulse:boost-queue"

class PulseViewModel(private val flagStore: FlagStore) : ViewModel() {
    suspend fun scheduleBoost(boost: Boost) {
        var queue = getBoostQueue()
        queue += boost

        flagStore.setFlag(KEY_BOOST_QUEUE, Json.encodeToString(queue))
    }

    private suspend fun getBoostQueue(): Array<Boost> {
        val value: String = flagStore.getFlag(KEY_BOOST_QUEUE).first() ?: "[]"
        return Json.decodeFromString(value)
    }
}

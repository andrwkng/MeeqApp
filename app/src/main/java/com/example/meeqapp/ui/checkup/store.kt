package com.example.meeqapp.ui.checkup

suspend fun getOrderedCheckups(): List<Checkup> {
    /*return try {
        val keys = withContext(Dispatchers.IO) { AsyncStorage.getAllKeys() }
        val checkupKeys = keys.filter { it.startsWith(CHECKUP_KEY_PREFIX) }

        val data = withContext(Dispatchers.IO) { AsyncStorage.multiGet(checkupKeys) }
        val checkups = data.map { (_, value) -> JSONObject(value).parseCheckup() }

        return checkups.sortedByDescending { it.date }
    } catch (err: Exception) {
        emptyList()
    }*/
    return emptyList()
}
package com.compass.data

import android.content.SharedPreferences
import com.compass.domain.CompassCache
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CompassCacheImpl(
    private val sharedPreferences: SharedPreferences
): CompassCache {

    override suspend fun getEveryTenthCharacter(): List<String>? {
        return withContext(Dispatchers.IO) {
            val jsonString = sharedPreferences.getString(EVERY_TENTH_CHARACTER_KEY, null)
            val type = object : TypeToken<List<String>?>() {}.type
            return@withContext Gson().fromJson(jsonString, type)
        }
    }

    override suspend fun setEveryTenthCharacter(content: List<String>) {
        withContext(Dispatchers.IO) {
            val textList: List<String> = ArrayList(content)
            val jsonText = Gson().toJson(textList)
            sharedPreferences
                .edit()
                .putString(EVERY_TENTH_CHARACTER_KEY, jsonText)
                .commit()
        }
    }

    override suspend fun getWordCounter(): Map<String, Int>? {
        return withContext(Dispatchers.IO) {
            val jsonString = sharedPreferences.getString(WORD_COUNTER_KEY, null)
            val type = object : TypeToken<Map<String, Int>>() {}.type
            return@withContext Gson().fromJson(jsonString, type)
        }
    }

    override suspend fun setWordCounter(content: Map<String, Int>) {
        withContext(Dispatchers.IO) {
            val jsonString = Gson().toJson(content)
            sharedPreferences.edit().putString(WORD_COUNTER_KEY, jsonString).commit()
        }
    }

    companion object {
        private const val EVERY_TENTH_CHARACTER_KEY = "EVERY_TENTH_CHARACTER_KEY"
        private const val WORD_COUNTER_KEY = "WORD_COUNTER_KEY"
    }
}
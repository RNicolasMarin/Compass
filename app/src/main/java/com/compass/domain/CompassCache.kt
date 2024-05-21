package com.compass.domain

interface CompassCache {

    suspend fun getEveryTenthCharacter(): List<String>?

    suspend fun setEveryTenthCharacter(content: List<String>)

    suspend fun getWordCounter(): Map<String, Int>?

    suspend fun setWordCounter(content: Map<String, Int>)
}
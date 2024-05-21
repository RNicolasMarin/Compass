package com.compass.presentation

data class CompassState(
    val everyTenthCharacter: List<String> = emptyList(),
    val wordCounter: Map<String, Int> = emptyMap(),
    val isEveryTenthCharacterLoading: Boolean = false,
    val isWordCounterLoading: Boolean = false
)

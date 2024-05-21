package com.compass.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compass.domain.CompassCache
import com.compass.domain.CompassRepository
import com.compass.domain.ResponseConverter
import com.compass.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompassViewModel @Inject constructor(
    private val repository: CompassRepository,
    private val responseConverter: ResponseConverter,
    private val compassCache: CompassCache
): ViewModel() {

    var state by mutableStateOf(CompassState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                isEveryTenthCharacterLoading = true,
                isWordCounterLoading = true
            )
            val result1 = compassCache.getEveryTenthCharacter()
            val result2 = compassCache.getWordCounter()

            state = state.copy(
                everyTenthCharacter = result1 ?: emptyList(),
                wordCounter = result2 ?: emptyMap(),
                isEveryTenthCharacterLoading = false,
                isWordCounterLoading = false
            )
        }
    }


    fun makeRequests() {
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(isEveryTenthCharacterLoading = true)
            val everyTenthCharacter = repository.getPageContent()
            Log.d("Compass", "everyTenthCharacter: $everyTenthCharacter")

            if (everyTenthCharacter is Result.Success) {
                val converted = responseConverter.convertToEveryTenthCharacter(everyTenthCharacter.data)
                compassCache.setEveryTenthCharacter(converted)
                state = state.copy(
                    everyTenthCharacter = converted,
                    isEveryTenthCharacterLoading = false
                )
            }

        }
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(isWordCounterLoading = true)
            val wordCounter = repository.getPageContent()
            Log.d("Compass", "wordCounter: $wordCounter")

            if (wordCounter is Result.Success) {
                val converted = responseConverter.convertToWordCounter(wordCounter.data)
                compassCache.setWordCounter(converted)
                state = state.copy(
                    wordCounter = converted,
                    isWordCounterLoading = false
                )
            }
        }
    }
}
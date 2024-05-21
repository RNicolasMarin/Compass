package com.compass.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compass.data.CompassApi
import com.compass.domain.CompassRepository
import com.compass.domain.ResponseConverter
import com.compass.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class CompassViewModel @Inject constructor(
    private val repository: CompassRepository,
    private val responseConverter: ResponseConverter
): ViewModel() {

    var state by mutableStateOf(CompassState())
        private set


    fun makeRequests() {
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(isEveryTenthCharacterLoading = true)
            val everyTenthCharacter = repository.getPageContent()
            Log.d("Compass", "everyTenthCharacter: $everyTenthCharacter")

            if (everyTenthCharacter is Result.Success) {
                val converted = responseConverter.convertToEveryTenthCharacter(everyTenthCharacter.data)
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
                state = state.copy(
                    wordCounter = converted,
                    isWordCounterLoading = false
                )
            }
        }
    }
}
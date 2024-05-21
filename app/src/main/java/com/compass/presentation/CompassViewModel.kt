package com.compass.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compass.data.CompassApi
import com.compass.domain.CompassRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class CompassViewModel @Inject constructor(
    private val repository: CompassRepository
): ViewModel() {



    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getPageContent()
            Log.d("Compass", "Result: $result")
        }
    }
}
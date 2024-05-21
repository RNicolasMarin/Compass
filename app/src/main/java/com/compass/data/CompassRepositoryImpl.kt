package com.compass.data

import com.compass.domain.CompassRepository
import com.compass.domain.Result

class CompassRepositoryImpl(
    private val service: CompassApi
): CompassRepository {

    override suspend fun getPageContent(): Result<String> {
        return try {
            val result = service.getPageContent()
            if (result.isSuccessful) {
                Result.Success(result.body().toString())
            } else {
                Result.Error(result.errorBody().toString())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }
}
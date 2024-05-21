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
                val body = result.body().toString()
                Result.Success(body)
            } else {
                Result.Error(result.errorBody().toString())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }
}
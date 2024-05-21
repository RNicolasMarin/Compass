package com.compass.domain

interface CompassRepository {

    suspend fun getPageContent(): Result<String>

}
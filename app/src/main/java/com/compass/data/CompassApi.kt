package com.compass.data

import retrofit2.Response
import retrofit2.http.GET

interface CompassApi {

    @GET("about/")
    suspend fun getPageContent(): Response<String>
}
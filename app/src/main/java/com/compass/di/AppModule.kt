package com.compass.di

import com.compass.data.CompassApi
import com.compass.data.CompassRepositoryImpl
import com.compass.domain.CompassRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideCompassApi(
        gson: Gson
    ): CompassApi {
        return Retrofit.Builder()
            .baseUrl("https://www.compass.com/")//about/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CompassApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCompassRepository(
        service: CompassApi
    ): CompassRepository {
        return CompassRepositoryImpl(service)
    }

}
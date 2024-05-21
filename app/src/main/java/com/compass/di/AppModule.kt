package com.compass.di

import android.content.Context
import android.content.SharedPreferences
import com.compass.data.CompassApi
import com.compass.data.CompassCacheImpl
import com.compass.data.CompassRepositoryImpl
import com.compass.domain.CompassCache
import com.compass.domain.CompassRepository
import com.compass.domain.ResponseConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideResponseConverter(): ResponseConverter {
        return ResponseConverter()
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context): SharedPreferences {
        return app.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideCompassCache(pref: SharedPreferences): CompassCache {
        return CompassCacheImpl(pref)
    }

}
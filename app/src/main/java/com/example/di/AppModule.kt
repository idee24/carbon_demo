package com.example.di

import android.app.Application
import androidx.room.Room
import com.example.carbon_demo.api.MovieApi
import com.example.carbon_demo.api.Routes
import com.example.carbon_demo.data.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 *@Created by Yerimah on 10/11/2021.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Routes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(application: Application) : MovieDatabase =
        Room.databaseBuilder(application, MovieDatabase::class.java, "movie_database")
            .build()
}
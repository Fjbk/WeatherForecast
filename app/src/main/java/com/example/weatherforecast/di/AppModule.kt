package com.example.weatherforecast.di

import com.example.weatherforecast.network.WeatherApi
import com.example.weatherforecast.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module //dagger module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides //dagger provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //Så Retrofit kan konvertere JSON til de objekter der bruges
            .build()
            .create(WeatherApi::class.java)
    }




}
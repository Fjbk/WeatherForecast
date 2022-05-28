package com.example.weatherforecast.di

import android.content.Context
import androidx.room.Room
import com.example.weatherforecast.data.WeatherDao
import com.example.weatherforecast.data.WeatherDatabase
import com.example.weatherforecast.network.WeatherApi
import com.example.weatherforecast.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module //dagger module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao = weatherDatabase.weatherDao()

    //Opretter databasen i baggrunden
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): WeatherDatabase
        = Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_database")
            .fallbackToDestructiveMigration()
            .build()

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
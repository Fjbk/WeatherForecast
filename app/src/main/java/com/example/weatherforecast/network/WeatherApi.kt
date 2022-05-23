package com.example.weatherforecast.network

import com.example.weatherforecast.model.Weather
import com.example.weatherforecast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

//Singleton så den ikke begynder at lave flere versioner
@Singleton
interface WeatherApi {
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query(value = "q") query: String, //Til at hente fra API'en, da du efterspørger byen med q=
        @Query(value = "units") units: String = "imperial", //Til metric/imperial units. Imperial er default
        @Query(value = "appid") appid: String = Constants.API_KEY //Key
    ): Weather //Returnerer et WeatherObject
}
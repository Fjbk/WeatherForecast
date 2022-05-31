package com.example.weatherforecast.repository

import android.util.Log
import com.example.weatherforecast.data.DataOrException
import com.example.weatherforecast.model.Weather
import com.example.weatherforecast.network.WeatherApi
import javax.inject.Inject

//Giver adgang til WeatherApi klassen som bruges til at oprette repositoriet
class WeatherRepository @Inject constructor(private val api: WeatherApi) {
    suspend fun getWeather(cityQuery: String, units: String): DataOrException<Weather, Boolean, Exception> { //DataOrException klassen er oprettet til at hvad som helst kan wrappes ind i den
        val response = try {
            api.getWeather(query = cityQuery, units = units)
        }catch (e: Exception){
            Log.d("catch", "getWeather error: $e")
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}
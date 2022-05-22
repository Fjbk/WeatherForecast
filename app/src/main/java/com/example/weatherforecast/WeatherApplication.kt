package com.example.weatherforecast

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Hilt setup så Hilt kan benyttes i projektet - Navnet skal også sættes i AndroidManifests.xml
@HiltAndroidApp
class WeatherApplication: Application() {
}
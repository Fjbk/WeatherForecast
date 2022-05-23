package com.example.weatherforecast.screens.main

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforecast.data.DataOrException
import com.example.weatherforecast.model.Weather

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    ShowData(mainViewModel)
}


@Composable
fun ShowData(mainViewModel: MainViewModel){
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)){
        value = mainViewModel.getWeatherData(city = "Odense")
    }.value //For at gøre værdien af det ovenstående til weatherData

    if (weatherData.loading == true){
        CircularProgressIndicator()
    }else if (weatherData.data != null){
    Text("Main screen ${weatherData.data!!.toString()}")
    }

}
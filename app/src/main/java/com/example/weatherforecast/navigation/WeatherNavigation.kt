package com.example.weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherforecast.screens.main.MainScreen
import com.example.weatherforecast.screens.main.MainViewModel
import com.example.weatherforecast.screens.search.SearchScreen
import com.example.weatherforecast.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() { //Hoster alt den navigation der skal bruges
    val navController = rememberNavController()
    //Start destination
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name){
        composable(WeatherScreens.SplashScreen.name){ //Her at screenet rent faktisk kaldes
            WeatherSplashScreen(navController = navController)
        }

        composable(WeatherScreens.MainScreen.name){ //Her at screenet rent faktisk kaldes
            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController, mainViewModel)
        }

        composable(WeatherScreens.SearchScreen.name){
            SearchScreen(navController)
        }
    }
}
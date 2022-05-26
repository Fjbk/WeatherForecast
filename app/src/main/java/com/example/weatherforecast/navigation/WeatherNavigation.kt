package com.example.weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherforecast.screens.about.AboutScreen
import com.example.weatherforecast.screens.favourites.FavouritesScreen
import com.example.weatherforecast.screens.main.MainScreen
import com.example.weatherforecast.screens.main.MainViewModel
import com.example.weatherforecast.screens.search.SearchScreen
import com.example.weatherforecast.screens.settings.SettingsScreen
import com.example.weatherforecast.screens.splash.WeatherSplashScreen

@ExperimentalComposeUiApi
@Composable
fun WeatherNavigation() { //Hoster alt den navigation der skal bruges
    val navController = rememberNavController()

    //Start destination
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name){
        composable(WeatherScreens.SplashScreen.name){ //Her at screenet rent faktisk kaldes
            WeatherSplashScreen(navController = navController)
        }

        //Navigerer til MainScreen men henter en String fra søgebaren
        val route = WeatherScreens.MainScreen.name
        composable(
            "$route/{city}",
            arguments = listOf(navArgument(name = "city") { type = NavType.StringType })
        ) { navBack ->
            navBack.arguments?.getString("city").let { city ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController, mainViewModel,
                    city = city)
            }
        }

        composable(WeatherScreens.SearchScreen.name){
            SearchScreen(navController)
        }

        composable(WeatherScreens.FavouritesScreen.name){
            FavouritesScreen(navController)
        }
        composable(WeatherScreens.AboutScreen.name){
            AboutScreen(navController)
        }
        composable(WeatherScreens.SettingsScreen.name){
            SettingsScreen(navController)
        }
    }
}
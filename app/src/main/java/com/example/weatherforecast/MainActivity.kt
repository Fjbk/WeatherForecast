package com.example.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherforecast.navigation.WeatherNavigation
import com.example.weatherforecast.ui.theme.WeatherForecastTheme
import dagger.hilt.android.AndroidEntryPoint

//Entry point til alle dependencies som skal bruges i projektet
@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp()
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun WeatherApp(){
    WeatherForecastTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.onPrimary
        ) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                WeatherNavigation()
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherForecastTheme {

    }
}
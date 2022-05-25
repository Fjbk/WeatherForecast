package com.example.weatherforecast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.weatherforecast.R
import com.example.weatherforecast.model.WeatherItem
import com.example.weatherforecast.utils.formatDate
import com.example.weatherforecast.utils.formatDateTime
import com.example.weatherforecast.utils.formatDecimals


@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(painter = rememberImagePainter(imageUrl), contentDescription = "icon", modifier = Modifier.size(80.dp))
}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem){
    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
        Row(modifier = Modifier.padding(4.dp)){
            Icon(painter = painterResource(id = R.drawable.humidity), contentDescription = "Humidity", modifier = Modifier.size(20.dp))
            Text("${weather.humidity}%", style = MaterialTheme.typography.caption)
        }
        Row(modifier = Modifier.padding(4.dp)){
            Icon(painter = painterResource(id = R.drawable.pressure), contentDescription = "Pressure", modifier = Modifier.size(20.dp))
            Text("${weather.pressure}", style = MaterialTheme.typography.caption)
        }
        Row(modifier = Modifier.padding(4.dp)){
            Icon(painter = painterResource(id = R.drawable.wind), contentDescription = "Wind", modifier = Modifier.size(20.dp))
            Text("${weather.speed} m/s", style = MaterialTheme.typography.caption)
        }
    }
}
@Composable
fun SunriseSunsetRow(weather: WeatherItem) {
    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
        Row(modifier = Modifier.padding(4.dp)){
            Icon(painter = painterResource(id = R.drawable.sunrise), contentDescription = "Sunrise", modifier = Modifier.size(20.dp))
            Text(formatDateTime(weather.sunrise), style = MaterialTheme.typography.caption)
        }
        Row(modifier = Modifier.padding(4.dp)){
            Icon(painter = painterResource(id = R.drawable.sunset), contentDescription = "Sunset", modifier = Modifier.size(20.dp))
            Text(formatDateTime(weather.sunset), style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun WeatherDetailRow(weather: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"

    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp), bottomStart = CornerSize(6.dp)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 5.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //formatDate henter datoen (f.eks. Wed, May 1) og split adskiller efter komma
            //Split adskiller efter komma og laver det til to arrays - en før og en efter
            Text(
                formatDate(weather.dt).split(",")[0],
                modifier = Modifier.padding(start = 5.dp)
            )
            WeatherStateImage(imageUrl = imageUrl)
            Surface(
                modifier = Modifier.padding(2.dp),
                shape = CircleShape,
                color = Color(0xFFFFA200)
            ) {
                Text(
                    weather.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption
                )
            }
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        Color.Blue.copy(alpha = 0.7f),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(formatDecimals(weather.temp.max) + "°")
                }
                withStyle(
                    style = SpanStyle(
                        Color.LightGray.copy(alpha = 0.7f),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(formatDecimals(weather.temp.min) + "°")
                }
            })
        }
    }
}
package com.example.weatherforecast.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun WeatherAppBar(
    title: String,
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title, color = MaterialTheme.colors.onSecondary, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp))
        },
        actions = {
                  if (isMainScreen){
                      IconButton(onClick = {
                          onAddActionClicked.invoke()
                      }) {
                          Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon")
                      }
                      IconButton(onClick = { /*TODO*/ }) {
                          Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Options")
                      }
                  }else Box{}
        },
        navigationIcon = {
            if(icon != null){ //icon parameter fra WeatherAppBar - Hvis der angives et Icon parameter bliver ikonet clickable
                Icon(imageVector = icon, contentDescription = "Icon",
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier.clickable { //onButtonClicked fra WeatherAppBar, så der er callback
                        onButtonClicked.invoke()
                    })
            }
        },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )
}

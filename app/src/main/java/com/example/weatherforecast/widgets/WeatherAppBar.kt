package com.example.weatherforecast.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import com.example.weatherforecast.navigation.WeatherScreens

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
    val showDialog = remember { mutableStateOf(false)}

    if (showDialog.value){
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    }

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
                      IconButton(onClick = {
                        showDialog.value = true
                      }) {
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

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    var expanded by remember { mutableStateOf(true) }
    val items = listOf("Favourites", "Settings", "About")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier
                .width(150.dp)
                .background(Color.White)
        ) {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    showDialog.value = false
                }) {
                    Icon(
                        imageVector = when (text) {
                            "Favourites" -> Icons.Default.Favorite
                            "About" -> Icons.Default.Info
                            else -> Icons.Default.Settings
                        }, contentDescription = "", tint = Color.LightGray
                    )
                    Text(text, modifier = Modifier.clickable {
                        navController.navigate(
                            when (text){
                                "Favourites" -> WeatherScreens.FavouritesScreen.name
                                "About" -> WeatherScreens.AboutScreen.name
                                else -> WeatherScreens.SettingsScreen.name
                            }
                        )
                    }, fontWeight = FontWeight(300))
                }
            }
        }
    }
}

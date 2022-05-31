package com.example.weatherforecast.screens.favourites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforecast.model.Favourite
import com.example.weatherforecast.navigation.WeatherScreens
import com.example.weatherforecast.widgets.WeatherAppBar

@Composable
fun FavouritesScreen(
    navController: NavController,
    favouriteViewModel: FavouriteViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Favoritter",
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
            navController = navController
        ) {
            navController.popBackStack()
        }
    })
    {
        androidx.compose.material.Surface(modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()) {
            Column(modifier = Modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                val favList = favouriteViewModel.favList.collectAsState().value
                LazyColumn{
                    items(items = favList){
                        CityRow(it, navController = navController, favouriteViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun CityRow(
    favorite: Favourite,
    navController: NavController,
    favouriteViewModel: FavouriteViewModel
) {
    androidx.compose.material.Surface(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                navController.navigate(WeatherScreens.MainScreen.name + "/${favorite.city}") //Navigerer tilbage til MainScreen med den valgte favoritby
            },
        shape = CircleShape.copy(topEnd = CornerSize(10.dp)),
        color = Color(0xFF2196F3)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = favorite.city, modifier = Modifier.padding(start = 4.dp))
            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFD1E3E1)
            ) {
                Text(
                    text = favorite.country,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption
                )
            }
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete",
                modifier = Modifier.clickable { favouriteViewModel.deleteFavourite(favorite) },
                tint = Color.Red.copy(alpha = 0.3f)
            )
        }
    }
}

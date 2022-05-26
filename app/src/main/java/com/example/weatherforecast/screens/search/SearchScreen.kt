package com.example.weatherforecast.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherforecast.navigation.WeatherScreens
import com.example.weatherforecast.widgets.WeatherAppBar

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(navController: NavController){
    Scaffold(topBar = {
        WeatherAppBar(title = "Søg", navController = navController, icon = Icons.Default.ArrowBack, isMainScreen = false) {
            navController.popBackStack() //Hvis man trykker på tilbageknappen kaldes popBackStack() så man går tilbage
        }
    }) {
        Surface() {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                SearchBar(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)){ mCity ->
                        //Vender tilbage til MainScreen med den indtastede by
                        navController.navigate(WeatherScreens.MainScreen.name + "/$mCity")
                }
            }
        }
    }
}


@ExperimentalComposeUiApi //Til KeyboardController
@Composable
fun SearchBar(modifier: Modifier = Modifier, onSearch: (String) -> Unit = {}){
    //rememberSaveable gør at staten ikke glemmes når man roterer telefonen
    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) { searchQueryState.value.trim().isNotEmpty() } //returnerer true hvis den ikke er tom
    Column() {
        CommonTextField(
            valueState = searchQueryState,
            placeholder = "Søg",
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                else onSearch(searchQueryState.value.trim())
                searchQueryState.value = "" //Så værdien nulstilles efter der er blevet søgt
                keyboardController?.hide()
            }
        )
    }
}

@Composable
fun CommonTextField(valueState: MutableState<String>,
                    placeholder: String,
                    keyboardType: KeyboardType = KeyboardType.Text,
                    imeAction: ImeAction = ImeAction.Next, //Hvis du trykker next/enter er den default action at gå videre med den indtastede tekst
                    onAction: KeyboardActions = KeyboardActions.Default) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it}, //Hvadend der er indtastet i tekstfeltet gøres til valueState
        label = { Text(text = placeholder)},
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        //TextFieldDefaults værdier skal overwrites
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            cursorColor = Color.Black),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp))
}

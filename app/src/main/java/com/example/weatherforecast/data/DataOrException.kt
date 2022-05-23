package com.example.weatherforecast.data

//Så stort set hvad som helst kan wrappes i DataOrException klassen.
class DataOrException<T, Boolean, E: Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: E? = null
)
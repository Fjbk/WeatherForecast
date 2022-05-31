package com.example.weatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherforecast.model.Favourite
import com.example.weatherforecast.model.Unit

//Opretter en database med Favourite og Unit classen
@Database(entities = [Favourite::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase(){
    abstract fun weatherDao(): WeatherDao
}


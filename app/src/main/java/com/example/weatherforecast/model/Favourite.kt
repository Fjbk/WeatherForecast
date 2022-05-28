package com.example.weatherforecast.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_tbl") //ROOM entity
data class Favourite(
    @PrimaryKey
    @ColumnInfo(name = "city") //Primary Key er city som oprettes i en kolonne
    val city: String,

    @ColumnInfo(name = "country")
    val country: String
)

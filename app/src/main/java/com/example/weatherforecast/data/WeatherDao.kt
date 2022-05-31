package com.example.weatherforecast.data

import androidx.room.*
import com.example.weatherforecast.model.Favourite
import com.example.weatherforecast.model.Unit
import kotlinx.coroutines.flow.Flow

//Dao = Data Access Object --> Accesser Dao igennem objektet  (via repository)
@Dao
interface WeatherDao {
    //Favourites
    @Query("SELECT * from fav_tbl")
    fun getFavourites(): Flow<List<Favourite>>

    @Query(value = "Select * from fav_tbl where city = :city")
    suspend fun getFavById(city: String) : Favourite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: Favourite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavourite(favourite: Favourite)

    @Query("DELETE from fav_tbl")
    suspend fun deleteAllFavourites()

    @Delete
    suspend fun deleteFavourite(favourite: Favourite)

    //Unit
    @Query("SELECT * from settings_tbl")
    fun getUnits(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: Unit)

    @Query("DELETE from settings_tbl")
    suspend fun deleteAllUnits()

    @Delete
    suspend fun deleteUnit(unit: Unit)
}
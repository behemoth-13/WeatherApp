package by.afanasyeu.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.afanasyeu.weatherapp.model.City

@Dao
interface CityDao {
    @Query("SELECT * from City")
    fun getAll(): DataSource.Factory<Int, City>

    @Query("SELECT id from City")
    fun getIds(): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: City)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cities: List<City>)
}
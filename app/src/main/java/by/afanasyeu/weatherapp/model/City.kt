package by.afanasyeu.weatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey
    val id: Long,
    val name: String,
    val temp: Float? = null,
    val icon: String? = null
)
package by.afanasyeu.weatherapp.model.rest

data class CitiesResponse(
    val list: List<CityDto>
)

data class CityDto(
    val id: Long,
    val name: String,
    val main: MainDto,
    val weather: List<WeatherDto>,
    val sys: Sys
)

data class WeatherDto(
    val icon: String
)

data class MainDto(
    val temp: Float,
    val feels_like: Float
)

data class Sys(
    val country: String
)
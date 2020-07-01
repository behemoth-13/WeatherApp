package by.afanasyeu.weatherapp.network

import by.afanasyeu.weatherapp.model.rest.CitiesResponse
import by.afanasyeu.weatherapp.model.rest.CityDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CityService {

    @GET("group?units=metric&appid=a642b5069d7f32d50e0d21ef6ea61cf6&lang=ru")
    suspend fun getCurrent(@Query("id") ids: List<Long>): Response<CitiesResponse>

    @GET("find?type=like&sort=population&appid=a642b5069d7f32d50e0d21ef6ea61cf6&units=metric&lang=ru")
    suspend fun search(@Query("q") searchString: String): Response<CitiesResponse>

    @GET("weather?appid=a642b5069d7f32d50e0d21ef6ea61cf6&units=metric&lang=ru")
    suspend fun getInfo(@Query("id") cityId: Long): Response<CityDto>


}
package by.afanasyeu.weatherapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import by.afanasyeu.weatherapp.database.CityDao
import by.afanasyeu.weatherapp.model.City
import by.afanasyeu.weatherapp.model.rest.CitiesResponse
import by.afanasyeu.weatherapp.model.rest.CityDto
import by.afanasyeu.weatherapp.network.CityService
import by.afanasyeu.weatherapp.network.HttpResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject

private const val TAG = "CityRepository"

class CityRepository @Inject constructor(
    private val cityDao: CityDao,
    private val cityService: CityService
) {

    val list: LiveData<PagedList<City>> = cityDao.getAll().toLiveData(pageSize = 20)

    suspend fun updateCities(): HttpResult<Unit> {
        return try {
            withContext(Dispatchers.IO) {
                val ids = cityDao.getIds()
                val response = cityService.getCurrent(ids)
                if (response.isSuccessful) {
                    val cities = response.body()!!.list.map { City(it.id, it.name, it.main.temp, it.weather[0].icon) }
                    cityDao.insert(cities)
                }
            }
            HttpResult.Success(Unit)
        } catch (e: UnknownHostException) {
            Log.w(TAG, e)
            HttpResult.NoInternet
        } catch (e: Exception) {
            Log.e(TAG, "cities not updated", e)
            HttpResult.Error
        }
    }

    suspend fun searchCity(searchString: String): HttpResult<List<CityDto>> {
        return try {
            val response = cityService.search(searchString)
            val cities = response.body()!!.list
            HttpResult.Success(cities)
        } catch (e: UnknownHostException) {
            Log.w(TAG, e)
            HttpResult.NoInternet
        } catch (e: Exception) {
            Log.e(TAG, "cities not updated", e)
            HttpResult.Error
        }
    }

    suspend fun save(city: City) {
        withContext(Dispatchers.IO) {
            cityDao.insert(city)
        }
    }

    suspend fun getInfo(cityId: Long): HttpResult<CityDto> {
        return try {
            val response = cityService.getInfo(cityId)
            val city = response.body()!!
            HttpResult.Success(city)
        } catch (e: UnknownHostException) {
            Log.w(TAG, e)
            HttpResult.NoInternet
        } catch (e: Exception) {
            Log.e(TAG, "cities not updated", e)
            HttpResult.Error
        }
    }
}
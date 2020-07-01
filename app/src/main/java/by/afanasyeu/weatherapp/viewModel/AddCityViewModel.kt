package by.afanasyeu.weatherapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.afanasyeu.weatherapp.model.City
import by.afanasyeu.weatherapp.model.rest.CityDto
import by.afanasyeu.weatherapp.network.HttpResult
import by.afanasyeu.weatherapp.repository.CityRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddCityViewModel @Inject constructor(
    private val repository: CityRepository
) : ViewModel() {
    val searchResult = MutableLiveData<HttpResult<List<CityDto>>>()

    suspend fun search(searchString: String) {
        searchResult.value = repository.searchCity(searchString)
    }

    fun save(city: City) {
        viewModelScope.launch {
            repository.save(city)
        }
    }
}
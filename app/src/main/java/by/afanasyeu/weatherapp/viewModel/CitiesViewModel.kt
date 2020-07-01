package by.afanasyeu.weatherapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import by.afanasyeu.weatherapp.model.City
import by.afanasyeu.weatherapp.network.HttpResult
import by.afanasyeu.weatherapp.repository.CityRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CitiesViewModel @Inject constructor(
    private val repository: CityRepository
) : ViewModel() {
    val list: LiveData<PagedList<City>> = repository.list
    val updated = MutableLiveData<HttpResult<Unit>>()

    fun updateCities() =
        viewModelScope.launch {
            updated.value = repository.updateCities()
        }
}
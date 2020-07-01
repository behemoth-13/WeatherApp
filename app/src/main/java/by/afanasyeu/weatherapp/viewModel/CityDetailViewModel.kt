package by.afanasyeu.weatherapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.afanasyeu.weatherapp.model.rest.CityDto
import by.afanasyeu.weatherapp.network.HttpResult
import by.afanasyeu.weatherapp.repository.CityRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CityDetailViewModel @Inject constructor(
    private val repository: CityRepository
) : ViewModel() {
    val info = MutableLiveData<HttpResult<CityDto>>()

    fun updateInfo(cityId: Long) =
        viewModelScope.launch {
            info.value = repository.getInfo(cityId)
        }
}
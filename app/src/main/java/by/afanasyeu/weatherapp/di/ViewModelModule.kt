package by.afanasyeu.weatherapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.afanasyeu.weatherapp.viewModel.AddCityViewModel
import by.afanasyeu.weatherapp.viewModel.CitiesViewModel
import by.afanasyeu.weatherapp.viewModel.CityDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CitiesViewModel::class)
    abstract fun citiesViewModel(viewModel: CitiesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddCityViewModel::class)
    abstract fun addCityViewModel(viewModel: AddCityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CityDetailViewModel::class)
    abstract fun cityDetailViewModel(viewModel: CityDetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}
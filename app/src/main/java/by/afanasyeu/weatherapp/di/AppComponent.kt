package by.afanasyeu.weatherapp.di

import by.afanasyeu.weatherapp.fragment.AddCityFragment
import by.afanasyeu.weatherapp.fragment.CitiesFragment
import by.afanasyeu.weatherapp.fragment.details.CityDetailFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        ViewModelModule::class,
        RoomModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(citiesFragment: CitiesFragment)
    fun inject(addCityFragment: AddCityFragment)
    fun inject(addCityFragment: CityDetailFragment)
}
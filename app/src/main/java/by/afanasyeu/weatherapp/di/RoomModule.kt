package by.afanasyeu.weatherapp.di

import by.afanasyeu.weatherapp.WeatherApp
import by.afanasyeu.weatherapp.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun provideDatabase(): AppDatabase = AppDatabase.getDatabase(WeatherApp.application)

    @Provides
    @Singleton
    fun provideCityDao(database: AppDatabase) = database.cityDao()
}
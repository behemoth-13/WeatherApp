package by.afanasyeu.weatherapp

import android.app.Application
import by.afanasyeu.weatherapp.di.AppComponent
import by.afanasyeu.weatherapp.di.DaggerAppComponent

class WeatherApp: Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
        appComponent = DaggerAppComponent.create()
    }

    companion object {
        lateinit var application: Application
        lateinit var appComponent: AppComponent
    }
}
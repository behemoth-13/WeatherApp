package by.afanasyeu.weatherapp.network

sealed class HttpResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : HttpResult<T>()
    object Error : HttpResult<Nothing>()
    object NoInternet : HttpResult<Nothing>()
}
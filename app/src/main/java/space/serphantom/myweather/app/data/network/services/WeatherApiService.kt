package space.serphantom.myweather.app.data.network.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import space.serphantom.myweather.app.data.entity.weather.CurrentWeather

/**
 * @see <a href="https://www.weatherapi.com/docs/">Официальная документация WeatherAPI</a>
 */
interface WeatherApiService {

    @GET("current.json")
    fun loadCurrentWeather(
        @Query("q") cityName: String,
        @Query("lang") language: String,
    ): CurrentWeather
}

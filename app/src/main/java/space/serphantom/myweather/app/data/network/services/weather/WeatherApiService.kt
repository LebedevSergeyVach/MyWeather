package space.serphantom.myweather.app.data.network.services.weather

import retrofit2.http.GET
import retrofit2.http.Query
import space.serphantom.myweather.app.data.entity.weather.CurrentWeather
import space.serphantom.myweather.app.data.network.response.ApiResponse

/**
 * @see <a href="https://www.weatherapi.com/docs/">Официальная документация WeatherAPI</a>
 */
interface WeatherApiService {

    @GET("current.json")
    fun loadCurrentWeather(
        @Query("q") cityName: String,
        @Query("lang") language: String,
    ): ApiResponse<CurrentWeather?>
}
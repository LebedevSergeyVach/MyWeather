package space.serphantom.myweather.app.data.network.repositories.weather

import space.serphantom.myweather.app.data.entity.weather.CurrentWeather
import space.serphantom.myweather.app.data.network.executors.ApiRequestExecutor
import space.serphantom.myweather.app.data.network.executors.results.ExecutionResult
import space.serphantom.myweather.app.data.network.services.weather.WeatherApiService

class WeatherRepository(
    private val weatherApiService: WeatherApiService,
) {

    private val apiRequestExecutor = ApiRequestExecutor()

    suspend fun loadCurrentWeather(
        cityName: String,
        language: String,
    ): ExecutionResult<CurrentWeather?> {
        return apiRequestExecutor.execute {
            weatherApiService.loadCurrentWeather(cityName, language).data
        }
    }
}
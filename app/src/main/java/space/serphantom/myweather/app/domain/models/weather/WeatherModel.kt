package space.serphantom.myweather.app.domain.models.weather

import space.serphantom.myweather.app.data.entity.weather.CurrentWeather
import space.serphantom.myweather.app.data.network.executors.results.ExecutionResult

interface WeatherModel {

    suspend fun loadCurrentWeather(cityName: String): ExecutionResult<CurrentWeather?>
}

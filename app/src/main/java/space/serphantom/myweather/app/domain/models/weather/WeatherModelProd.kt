package space.serphantom.myweather.app.domain.models.weather

import space.serphantom.myweather.app.data.entity.weather.CurrentWeather
import space.serphantom.myweather.app.data.network.executors.results.ExecutionResult
import space.serphantom.myweather.app.data.network.repositories.weather.WeatherRepository

class WeatherModelProd(private val weatherRepository: WeatherRepository) : WeatherModel {

    override suspend fun loadCurrentWeather(cityName: String): ExecutionResult<CurrentWeather?> {
        return weatherRepository.loadCurrentWeather(cityName, "ru")
    }
}

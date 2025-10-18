package space.serphantom.myweather.app.data.network.repositories

import space.serphantom.myweather.app.data.network.services.WeatherApiService

class WeatherRepository(
    private val weatherApiService: WeatherApiService,
) {}

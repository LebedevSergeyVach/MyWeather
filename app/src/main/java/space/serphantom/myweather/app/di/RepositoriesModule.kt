package space.serphantom.myweather.app.di

import org.koin.dsl.module
import space.serphantom.myweather.app.data.network.repositories.weather.WeatherRepository

val repositoriesModule = module {

    factory {
        WeatherRepository(weatherApiService = get())
    }
}

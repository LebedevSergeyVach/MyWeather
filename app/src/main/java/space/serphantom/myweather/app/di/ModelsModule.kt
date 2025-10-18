package space.serphantom.myweather.app.di

import org.koin.dsl.module
import space.serphantom.myweather.app.domain.models.weather.WeatherModel
import space.serphantom.myweather.app.domain.models.weather.WeatherModelProd

val modelsModule = module {

    factory<WeatherModel> {
        WeatherModelProd(weatherRepository = get())
    }
}

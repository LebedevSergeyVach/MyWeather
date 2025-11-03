package space.serphantom.myweather.app.di

import org.koin.dsl.module
import space.serphantom.myweather.app.data.network.repositories.weather.WeatherRepository
import space.serphantom.myweather.app.data.network.services.weather.WeatherApiService

/**
 * `Koin` модуль для регистрации репозиториев приложения.
 * Содержит `factory` определения для создания экземпляров репозиториев.
 *
 * @property [WeatherRepository] Фабрика для создания [WeatherRepository] с зависимостью от [WeatherApiService]
 *
 * @see module
 * @see factory
 */
val repositoriesModule = module {

    /**
     * Регистрирует фабрику для создания экземпляров WeatherRepository.
     * Каждый запрос будет создавать новый экземпляр репозитория.
     *
     * @param [WeatherApiService] Зависимость [WeatherApiService], которая будет внедрена автоматически
     * @return Новый экземпляр [WeatherRepository]
     *
     * @see WeatherRepository
     * @see get
     */
    factory {
        WeatherRepository(weatherApiService = get())
    }
}

package space.serphantom.myweather.app.di

import org.koin.dsl.module
import space.serphantom.myweather.app.data.network.repositories.weather.WeatherRepository
import space.serphantom.myweather.app.domain.models.weather.WeatherModel
import space.serphantom.myweather.app.domain.models.weather.WeatherModelProd


/**
 * Koin модуль для регистрации моделей данных (`business logic layer`).
 * Содержит `factory` определения для создания бизнес-моделей приложения.
 *
 * @property WeatherModel Фабрика для создания продакшен реализации [WeatherModel]
 *
 * @see module
 * @see factory
 */
val modelsModule = module {

    /**
     * Регистрирует фабрику для создания экземпляров [WeatherModel].
     * Использует продакшен реализацию [WeatherModelProd].
     *
     * @param [WeatherRepository] Зависимость [WeatherRepository], которая будет внедрена автоматически
     * @return Новый экземпляр [WeatherModelProd] как реализация [WeatherModel] интерфейса
     *
     * @see WeatherModel
     * @see WeatherModelProd
     * @see get
     */
    factory<WeatherModel> {
        WeatherModelProd(weatherRepository = get())
    }
}

package space.serphantom.myweather.data.network.services

import retrofit2.Call
import retrofit2.HttpException
import retrofit2.http.GET
import retrofit2.http.Query

import space.serphantom.myweather.data.entity.weather.CurrentWeather
import space.serphantom.myweather.data.network.repositories.WeatherRepository

import java.io.IOException

/**
 * Сервис для работы с API погоды.
 *
 * Предоставляет методы для получения текущих погодных данных с внешнего сервера.
 * Все методы являются suspend функциями и должны вызываться из корутины.
 *
 * @see <a href="https://www.weatherapi.com/docs/">Официальная документация WeatherAPI</a>
 * @author Ваше Имя
 * @since 1.0
 */
interface WeatherApiService {

    /**
     * Загружает текущую погоду для указанного города.
     *
     * Выполняет **GET** запрос к конечной точке `current.json` API.
     *
     * @param cityName Название города (на английском или русском языке). Не может быть `null` или пустым.
     * @param language Двухбуквенный код языка (например, "ru" для русского),
     * на котором возвращаются текстовые описания погоды.
     *
     * @return Объект типа [CurrentWeather], содержащий актуальные погодные условия.
     *
     * @throws [IOException] Если возникла проблема с сетью, например, таймаут соединения.
     * @throws [HttpException] Если API вернуло ошибку (коды ответа 4xx, 5xx).
     *
     * @sample [loadCurrentWeather][WeatherRepository.loadCurrentWeather]
     */
    @GET("current.json")
    fun loadCurrentWeather(
        @Query("q") cityName: String,
        @Query("lang") language: String,
    ): Call<CurrentWeather>
}

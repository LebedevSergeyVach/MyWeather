package space.serphantom.myweather.data.network.repositories

import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Headers
import space.serphantom.myweather.data.entity.weather.CurrentWeather
import space.serphantom.myweather.data.network.services.WeatherApiService
import space.serphantom.myweather.data.result.base.ApiCallback
import space.serphantom.myweather.data.result.base.ErrorResponse
import space.serphantom.myweather.data.result.base.ExecutionResult
import space.serphantom.myweather.extensions.coroutines.resumeIfActive

/**
 * Репозиторий для управления погодными данными.
 *
 * Этот класс служит **прослойкой** между источником данных (сетевой API) и слоем представления (ViewModel).
 * Он инкапсулирует логику выполнения запросов и обработки ошибок.
 *
 * @property weatherApiService Сервис для выполнения сетевых запросов к API погоды.
 * @constructor Создает новый репозиторий для работы с погодными данными.
 */
class WeatherRepository(
    private val weatherApiService: WeatherApiService,
) {

    /**
     * Загружает текущую погоду для города.
     *
     * Этот метод пытается асинхронно получить данные о погоде. Он обрабатывает различные типы исключений
     * и возвращает результат в виде экземпляра [ExecutionResult].
     *
     * @param cityName Название города, для которого запрашивается погода.
     * @param language Язык, на котором должны возвращаться описания.
     *
     * @return [ExecutionResult.Success], содержащий объект [CurrentWeather] в случае успеха,
     * или [ExecutionResult.Error], если произошла ошибка.
     *
     * @see WeatherApiService.loadCurrentWeather
     * @see ExecutionResult
     *
     * ```
     * val repository = WeatherRepository(apiService)
     * val result = repository.loadCurrentWeather("Москва", "ru")
     * if (result is ExecutionResult.Success) {
     *     println("Температура: ${result.data.temperature}")
     * }
     * ```
     */
    suspend fun loadCurrentWeather(
        cityName: String,
        language: String,
    ): ExecutionResult<CurrentWeather?> {
        val call = weatherApiService.loadCurrentWeather(cityName, language)

        return suspendCancellableCoroutine { continuation ->
            val callback = object : ApiCallback<CurrentWeather?> {
                override fun onSuccess(response: CurrentWeather?) {
                    continuation.resumeIfActive(ExecutionResult.Success(response))
                }

                override fun onError(error: ErrorResponse?) {
                    continuation.resumeIfActive(ExecutionResult.Error(error?.message))
                }
            }

            call.enqueue(callback)
            continuation.invokeOnCancellation { if (call.isCanceled.not()) call.cancel() }
        }
    }
}

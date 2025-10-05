package space.serphantom.myweather.data.result.base

import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException

/**
 * Специализированный callback для обработки ответов API с поддержкой парсинга структурированных ошибок.
 *
 * Расширяет [NetworkCallback] добавляя возможность парсить ошибки в формате [ErrorResponse]
 * используя Kotlin Serialization вместо Gson.
 *
 * ## Особенности:
 * - Автоматический парсинг ошибок из тела HTTP ответа
 * - Поддержка кастомных десериализаторов для ErrorResponse
 * - Обработка ошибок сериализации
 * - Предоставляет методы для работы со структурированными ошибками
 *
 * @param T Тип данных успешного ответа от API.
 *
 * @see NetworkCallback Базовый интерфейс сетевых колбэков
 * @see ErrorResponse Модель данных для ошибок API
 * @see Json Kotlin Serialization для парсинга JSON
 */
interface ApiCallback<T> : NetworkCallback<T> {

    /**
     * Обрабатывает ошибку HTTP запроса с парсингом структурированного ответа.
     *
     * Пытается распарсить тело ошибки в формате [ErrorResponse] используя Kotlin Serialization.
     * Если парсинг неуспешен, используется сообщение об ошибке по умолчанию.
     *
     * @param response Ответ от сервера с ошибкой. Если `null`, обработка не выполняется.
     *
     * @see getErrorForCode Извлечение и парсинг ошибки из ответа
     * @see convertStringToErrorResponse Конвертация строки в ErrorResponse
     */
    override fun handleError(response: Response<T>?) {
        if (response == null) {
            return
        }

        val errorResponse = getErrorForCode(response)
        val defaultServerErrorMessage = "Server error" // TODO Замените на ресурс

        if (errorResponse == null) {
            onError(defaultServerErrorMessage)
            return
        }

        val errorMessage = errorResponse.getDisplayMessage()

        if (errorMessage.isBlank()) {
            errorResponse.copy(message = defaultServerErrorMessage)
        }

        onError(errorResponse)
        onError(errorResponse, response.code())
    }

    /**
     * Обрабатывает ошибку в текстовом формате, преобразуя ее в [ErrorResponse].
     *
     * Создает объект [ErrorResponse] из строкового сообщения и передает его в методы обработки ошибок.
     *
     * @param error Текстовое сообщение об ошибке.
     */
    override fun onError(error: String) {
        val errorMessage = ErrorResponse(message = error)
        onError(errorMessage)
        onError(errorMessage, null)
    }

    /**
     * Извлекает и парсит ошибку из HTTP ответа.
     *
     * Читает тело ошибки из [Response.errorBody] и пытается распарсить его в [ErrorResponse].
     *
     * @param response HTTP ответ содержащий ошибку.
     * @return Объект [ErrorResponse] если парсинг успешен, иначе `null`.
     *
     * @see Response.errorBody Тело ошибки HTTP ответа
     * @see convertStringToErrorResponse Конвертация строки в ErrorResponse
     */
    fun getErrorForCode(response: Response<T>): ErrorResponse? {
        val body = try {
            val responseBody: ResponseBody? = response.errorBody()
            responseBody?.string()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

        if (body == null) {
            return null
        }

        return convertStringToErrorResponse(body)
    }

    /**
     * Конвертирует JSON строку в объект [ErrorResponse] используя Kotlin Serialization.
     *
     * Использует [Json] для десериализации строки в объект [ErrorResponse].
     * Обрабатывает исключения парсинга и возвращает `null` в случае ошибки.
     *
     * @param body JSON строка для парсинга.
     * @return Объект [ErrorResponse] если парсинг успешен, иначе `null`.
     *
     * @see Json.decodeFromString Десериализация JSON строки
     * @see kotlinx.serialization.SerializationException Исключения парсинга
     */
    fun convertStringToErrorResponse(body: String): ErrorResponse? {
        return try {
            Json.decodeFromString<ErrorResponse>(body)
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }

    /**
     * Вызывается при ошибке запроса со структурированным объектом ошибки.
     *
     * @param error Объект [ErrorResponse] содержащий детализированную информацию об ошибке.
     */
    fun onError(error: ErrorResponse?) {}

    /**
     * Вызывается при ошибке запроса со структурированным объектом ошибки и кодом ответа.
     *
     * @param error Объект [ErrorResponse] содержащий детализированную информацию об ошибке.
     * @param responseCode HTTP статус-код ответа от сервера.
     */
    fun onError(error: ErrorResponse?, responseCode: Int?) {}
}

package space.serphantom.myweather.data.result.base

import okhttp3.Headers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Базовый интерфейс для обработки сетевых запросов Retrofit.
 *
 * Предоставляет стандартную реализацию методов [Callback.onResponse] и [Callback.onFailure]
 * с дополнительной обработкой успешных ответов и ошибок.
 *
 * ## Основные возможности:
 * - Автоматическая проверка успешности HTTP ответа
 * - Централизованная обработка сетевых ошибок
 * - Поддержка различных форматов успешных ответов (с заголовками и без)
 *
 * @param T Тип данных, ожидаемый в успешном ответе от API.
 *
 * @see Callback Базовый интерфейс Retrofit
 * @see ApiCallback Специализированный интерфейс для обработки ошибок API
 */
interface NetworkCallback<T> : Callback<T> {

    /**
     * Обрабатывает ответ от сервера.
     *
     * Автоматически определяет успешность ответа и вызывает соответствующие методы:
     * - [onSuccess] для успешных ответов (HTTP 200-299)
     * - [handleError] для неуспешных ответов
     *
     * @param call Исходный [Call] объект, который был выполнен.
     * @param response Ответ от сервера, содержащий данные или ошибку.
     *
     * @see Response.isSuccessful Проверка успешности HTTP ответа
     * @see handleError Обработка неуспешных ответов
     */
    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            onSuccess(response.body())
            onSuccess(response.body(), response.headers())
        } else {
            handleError(response)
        }
    }

    /**
     * Обрабатывает ошибки выполнения сетевого запроса.
     *
     * Вызывается когда запрос не смог дойти до сервера или произошла ошибка на уровне сети.
     * Игнорирует отмененные запросы ([Call.isCanceled]).
     *
     * @param call Исходный [Call] объект, который вызвал ошибку.
     * @param throwable Исключение, содержащее информацию об ошибке.
     *
     * @see Call.isCanceled Проверка отмены запроса
     * @see ErrorMessageHelper.getErrorMessage Получение читаемого сообщения об ошибке
     */
    override fun onFailure(call: Call<T>, throwable: Throwable) {
        throwable.printStackTrace()

        if (call.isCanceled()) {
            return
        }

        val errorMessage = ErrorMessageHelper.getErrorMessage(throwable)
        if (errorMessage != null) {
            onError(errorMessage)
        }
    }

    /**
     * Обрабатывает ошибку HTTP запроса.
     *
     * Базовая реализация для обработки неуспешных HTTP ответов.
     * По умолчанию вызывает [onDefaultError] для стандартной обработки ошибок.
     *
     * @param response Ответ от сервера с ошибкой. Если `null`, ошибка игнорируется.
     *
     * @see onDefaultError Стандартная обработка ошибок
     */
    fun handleError(response: Response<T>?) {
        if (response == null) {
            return
        }
        onDefaultError(response)
    }

    /**
     * Стандартная обработка ошибки HTTP запроса.
     *
     * Создает сообщение об ошибке по умолчанию и передает его в методы [onError].
     * Может быть переопределен для кастомной логики обработки ошибок.
     *
     * @param response Ответ от сервера с ошибкой.
     */
    fun onDefaultError(response: Response<T>) {
        val errorMessage = "Server error occurred" // TODO Замените на ресурс из ResourcesHelper
        onError(errorMessage)
        onError(errorMessage, response.code())
    }

    /**
     * Вызывается при успешном выполнении запроса.
     *
     * @param response Тело успешного ответа от сервера. Может быть `null` для пустых ответов.
     */
    fun onSuccess(response: T?) {}

    /**
     * Вызывается при успешном выполнении запроса с заголовками ответа.
     *
     * @param response Тело успешного ответа от сервера. Может быть `null` для пустых ответов.
     * @param headers Заголовки HTTP ответа. Могут содержать мета-информацию (пагинация, токены и т.д.).
     *
     * @see Headers Документация по заголовкам OkHttp
     */
    fun onSuccess(response: T?, headers: Headers) {}

    /**
     * Вызывается при ошибке выполнения запроса.
     *
     * @param error Сообщение об ошибке в текстовом формате.
     */
    fun onError(error: String) {}

    /**
     * Вызывается при ошибке выполнения запроса с кодом ответа.
     *
     * @param error Сообщение об ошибке в текстовом формате.
     * @param responseCode HTTP статус-код ответа от сервера.
     */
    fun onError(error: String, responseCode: Int?) {}
}

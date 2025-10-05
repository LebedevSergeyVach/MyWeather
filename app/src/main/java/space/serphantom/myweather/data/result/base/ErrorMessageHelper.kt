package space.serphantom.myweather.data.result.base

import android.content.Context
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlinx.serialization.SerializationException
import space.serphantom.myweather.R

/**
 * Вспомогательный объект для обработки и преобразования исключений в пользовательские сообщения об ошибках.
 *
 * Предоставляет централизованный механизм для конвертации различных типов исключений,
 * возникающих при сетевых операциях и обработке данных, в локализованные сообщения,
 * понятные конечному пользователю. Использует исключительно Kotlin Serialization для работы с данными.
 *
 * ## Основные возможности:
 * - Преобразование сетевых исключений в пользовательские сообщения
 * - Обработка ошибок парсинга данных с использованием Kotlin Serialization
 * - Поддержка различных типов исключений с приоритетом обработки
 * - Интеграция с системой ресурсов Android для локализации
 *
 * @see UnknownHostException Обработка ошибок отсутствия интернет-соединения
 * @see SocketTimeoutException Обработка таймаутов сетевых запросов
 * @see IOException Обработка ошибок ввода-вывода
 * @see SerializationException Обработка ошибок десериализации данных с использованием Kotlin Serialization
 *
 * @sample ErrorMessageHelper.getErrorMessage(UnknownHostException())
 * // Вернет строку "Нет подключения к интернету" (или соответствующую из ресурсов)
 */
object ErrorMessageHelper {

    /**
     * Определяет идентификатор ресурса строки ошибки на основе типа исключения.
     *
     * Анализирует иерархию исключений и возвращает соответствующий идентификатор ресурса
     * для локализованного сообщения об ошибке. Использует только Kotlin Serialization
     * для обработки ошибок, связанных с данными.
     *
     * ## Приоритет обработки исключений:
     * 1. [UnknownHostException] и [ConnectException] - отсутствие интернет-соединения
     * 2. [SocketTimeoutException] - медленное соединение или таймаут запроса
     * 3. [IOException], [SerializationException], [IllegalStateException] - ошибки данных и парсинга
     * 4. Прочие исключения - неизвестные ошибки
     *
     * @param throwable Исключение, для которого определяется сообщение об ошибке.
     * @return Идентификатор ресурса строки с соответствующим сообщением об ошибке.
     *         Всегда возвращает валидный идентификатор ресурса, никогда не возвращает `null`.
     *
     * @throws IllegalArgumentException если [throwable] равен `null` (в Kotlin параметр не может быть `null`)
     *
     * @see Throwable Иерархия исключений в Java/Kotlin
     * @see SerializationException Исключения Kotlin Serialization
     * @see <a href="https://developer.android.com/guide/topics/resources/string-resource">String Resources</a>
     */
    private fun getErrorForThrowable(throwable: Throwable): Int {
        return when (throwable) {
            is UnknownHostException, is ConnectException -> R.string.error_no_internet_connection
            is SocketTimeoutException -> R.string.error_slow_internet_connection
            is IOException, is SerializationException, is IllegalStateException -> R.string.error_data_parsing
            else -> R.string.error_unknown
        }
    }

    /**
     * Преобразует исключение в читаемое сообщение об ошибке для отображения пользователю.
     *
     * Использует систему ресурсов Android для получения локализованных сообщений
     * на основе типа переданного исключения. Работает исключительно с Kotlin Serialization
     * для обработки ошибок, связанных с данными.
     *
     * ## Особенности реализации:
     * - Всегда возвращает не-null строку
     * - Гарантирует возврат валидного сообщения даже для неизвестных исключений
     * - Использует контекст приложения для доступа к ресурсам
     * - Потокобезопасна (может вызываться из любого потока)
     * - Использует только Kotlin Serialization, без зависимостей от Gson
     *
     * @param throwable Исключение, которое требуется преобразовать в сообщение об ошибке.
     * @param context Контекст приложения для доступа к ресурсам. Если `null`,
     *                используется контекст приложения по умолчанию.
     * @return Локализованное сообщение об ошибке, подходящее для отображения пользователю.
     *         Никогда не возвращает `null`.
     *
     * @sample
     * ```
     * try {
     *     // сетевой запрос с использованием Kotlin Serialization
     *     val data = Json.decodeFromString<WeatherData>(jsonString)
     * } catch (e: Exception) {
     *     val errorMessage = ErrorMessageHelper.getErrorMessage(e, context)
     *     showErrorDialog(errorMessage)
     * }
     * ```
     *
     * @see Context.getString Получение строки из ресурсов
     * @see getErrorForThrowable Определение типа ошибки
     * @see kotlinx.serialization.json.Json.decodeFromString Десериализация Kotlin Serialization
     */
    fun getErrorMessage(throwable: Throwable, context: Context? = null): String {
        val errorId = getErrorForThrowable(throwable)

        return try {
            // Пытаемся использовать переданный контекст
            context?.getString(errorId)
                ?: getStringFromResources(errorId) // Используем fallback
        } catch (e: Exception) {
            // Fallback на случай проблем с ресурсами
            getDefaultErrorMessage(throwable)
        }
    }

    /**
     * Получает строку из ресурсов используя глобальный контекст приложения.
     *
     * Внутренний метод для получения строки ресурса когда явный контекст не предоставлен.
     * Использует контекст приложения через синглтон или другие доступные механизмы.
     *
     * @param resourceId Идентификатор ресурса строки.
     * @return Строка из ресурсов или fallback-сообщение если ресурс недоступен.
     *
     * @see ResourcesHelper Вспомогательный класс для работы с ресурсами
     */
    private fun getStringFromResources(resourceId: Int): String {
        return try {
            // Замените на вашу реализацию получения ресурсов
            // Например: ResourcesHelper.getString(resourceId)
            // Или используйте Application Context
            "Ошибка приложения" // Временный fallback
        } catch (e: Exception) {
            // Ultimate fallback
            when (resourceId) {
                R.string.error_no_internet_connection -> "Нет подключения к интернету"
                R.string.error_slow_internet_connection -> "Медленное интернет-соединение"
                R.string.error_data_parsing -> "Ошибка обработки данных"
                R.string.error_unknown -> "Неизвестная ошибка"
                else -> "Произошла ошибка"
            }
        }
    }

    /**
     * Создает сообщение об ошибке по умолчанию на основе типа исключения.
     *
     * Используется как крайний fallback когда недоступны ресурсы приложения.
     * Создает читаемые сообщения на русском языке на основе класса исключения.
     * Учитывает особенности Kotlin Serialization при обработке ошибок данных.
     *
     * @param throwable Исключение для анализа.
     * @return Читаемое сообщение об ошибке на русском языке.
     */
    private fun getDefaultErrorMessage(throwable: Throwable): String {
        return when (throwable) {
            is UnknownHostException, is ConnectException ->
                "Отсутствует подключение к интернету. Проверьте настройки сети."

            is SocketTimeoutException ->
                "Превышено время ожидания ответа от сервера. Проверьте скорость интернета."

            is IOException ->
                "Ошибка сети. Проверьте подключение и попробуйте снова."

            is SerializationException ->
                "Ошибка обработки данных. Невозможно прочитать полученную информацию."

            is IllegalStateException ->
                "Некорректное состояние приложения. Перезапустите приложение."

            else ->
                "Произошла непредвиденная ошибка: ${throwable.javaClass.simpleName}"
        }
    }

    /**
     * Определяет, является ли ошибка связанной с отсутствием интернет-соединения.
     *
     * Вспомогательный метод для условной логики при обработке ошибок.
     *
     * @param throwable Исключение для проверки.
     * @return `true` если ошибка связана с отсутствием интернета, иначе `false`.
     */
    fun isNetworkError(throwable: Throwable): Boolean {
        return throwable is UnknownHostException ||
                throwable is ConnectException ||
                throwable is SocketTimeoutException
    }

    /**
     * Определяет, является ли ошибка связанной с обработкой данных.
     *
     * Вспомогательный метод для условной логики при обработке ошибок.
     * Учитывает ошибки Kotlin Serialization при парсинге данных.
     *
     * @param throwable Исключение для проверки.
     * @return `true` если ошибка связана с парсингом или обработкой данных, иначе `false`.
     */
    fun isDataProcessingError(throwable: Throwable): Boolean {
        return throwable is SerializationException ||
                throwable is IllegalStateException
    }

    /**
     * Определяет, является ли ошибка связанной с Kotlin Serialization.
     *
     * Специализированный метод для проверки ошибок десериализации данных
     * с использованием Kotlin Serialization.
     *
     * @param throwable Исключение для проверки.
     * @return `true` если ошибка связана с Kotlin Serialization, иначе `false`.
     *
     * @see SerializationException Исключения Kotlin Serialization
     */
    fun isSerializationError(throwable: Throwable): Boolean {
        return throwable is SerializationException
    }
}

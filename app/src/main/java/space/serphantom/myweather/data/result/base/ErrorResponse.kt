package space.serphantom.myweather.data.result.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Модель данных для представления ошибок, возвращаемых API сервером.
 *
 * Используется для стандартизированного парсинга ошибок, возвращаемых бэкенд-сервисами.
 * Поддерживает различные форматы ошибок, включая простые сообщения, списки уведомлений и карты ошибок.
 *
 * @property title Заголовок ошибки, обычно краткое описание проблемы.
 * @property message Детальное сообщение об ошибке для отображения пользователю.
 * @property code Код ошибки, возвращаемый сервером (может быть HTTP статус-код или кастомный код API).
 * @property notices Список дополнительных уведомлений или подсказок, связанных с ошибкой.
 * @property mapErrors Карта ошибок валидации, где ключ - название поля, а значение - описание ошибки.
 *
 * @see ApiCallback Для использования в обработке ошибок API
 * @see NetworkCallback Базовый интерфейс для сетевых колбэков
 *
 * @sample ErrorResponse Пример создания объекта ошибки:
 *
 * ```
 * val error = ErrorResponse(
 *     title = "Validation Failed",
 *     message = "Please check your input",
 *     code = 422,
 *     notices = listOf(Notice("email", "Invalid email format")),
 *     mapErrors = mapOf("email" to "Must be valid email")
 * )
 * ```
 */
@Serializable
data class ErrorResponse(

    /**
     * Заголовок ошибки.
     *
     * Используется для краткого описания типа ошибки. Может быть `null`, если сервер не предоставляет заголовок.
     *
     * @sample "Authentication Failed"
     * @sample "Validation Error"
     */
    @SerialName("title")
    val title: String? = null,

    /**
     * Детальное сообщение об ошибке.
     *
     * Основное сообщение, которое должно отображаться пользователю. Всегда должен быть заполнен.
     *
     * @sample "Invalid email or password"
     * @sample "City not found"
     */
    @SerialName("message")
    val message: String? = null,

    /**
     * Код ошибки от сервера.
     *
     * Может содержать HTTP статус-код или кастомный код ошибки API.
     * Если `null`, используется код HTTP ответа.
     *
     * @sample 404
     * @sample 1001
     */
    @SerialName("code")
    val code: Int? = null,

    /**
     * Список дополнительных уведомлений.
     *
     * Используется для передачи множественных ошибок или предупреждений.
     * Каждый элемент списка представляет отдельное уведомление.
     *
     * @see Notice Класс для представления отдельного уведомления
     */
    @SerialName("notices")
    val notices: List<Notice>? = null,

    /**
     * Карта ошибок валидации.
     *
     * Используется для ошибок валидации форм, где ключ - название поля формы,
     * а значение - описание ошибки для этого поля.
     *
     * @sample mapOf("email" to "Invalid format", "password" to "Too short")
     */
    @SerialName("errors")
    val mapErrors: Map<String, String>? = null
) {
    /**
     * Возвращает сообщение об ошибке или заголовок, если сообщение отсутствует.
     *
     * @return Основное сообщение об ошибке для отображения пользователю.
     *         Если [message] равно `null`, возвращает [title].
     *         Если оба равны `null`, возвращает строку по умолчанию.
     */
    fun getDisplayMessage(): String {
        return message ?: title ?: "Unknown error occurred"
    }
}

/**
 * Модель для представления отдельного уведомления в ошибке.
 *
 * Используется для передачи структурированных уведомлений от сервера.
 *
 * @property field Название поля, к которому относится уведомление.
 * @property message Текст уведомления.
 */
@Serializable
data class Notice(
    @SerialName("field")
    val field: String? = null,
    @SerialName("message")
    val message: String? = null
)

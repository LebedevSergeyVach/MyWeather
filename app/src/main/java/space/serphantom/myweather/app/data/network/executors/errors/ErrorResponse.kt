package space.serphantom.myweather.app.data.network.executors.errors

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Ответ сетевого запроса, завершившегося ошибкой
 */
@Serializable
data class ErrorResponse(
    val message: String?,
    @SerialName("error_code")
    val code: String?
)
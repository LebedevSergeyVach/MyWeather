package space.serphantom.myweather.app.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val data: T?,
    val message: String?,
    @SerialName("code")
    val errorCode: String?,
)

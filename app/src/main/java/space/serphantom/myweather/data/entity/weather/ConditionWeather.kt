package space.serphantom.myweather.data.entity.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConditionWeather(
    @SerialName("text")
    val text: String?,
    @SerialName("icon")
    val iconUrl: String?,
    @SerialName("code")
    val code: Int?,
)

package space.serphantom.myweather.data.entity.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    @SerialName("last_updated_epoch")
    val lastUpdatedDateId: Int?,
    @SerialName("last_updated")
    val lastUpdatedDate: String?,
    @SerialName("temp_c")
    val temperatureInCelsius: Double?,
    @SerialName("temp_f")
    val temperatureInFahrenheit: Double?,
    @SerialName("is_day")
    val dayOfWeek: Int?,
    @SerialName("condition")
    val condition: ConditionWeather?,
    @SerialName("wind_mph")
    val windInMPH: Float?,
    @SerialName("wind_kph")
    val windInKPH: Float?,
)
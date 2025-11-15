package space.serphantom.myweather.app.ui.compose.data.entity.current_weather_summary

import androidx.compose.runtime.Immutable
import java.time.LocalDate

/**
 * `Data class` представляющий данные для отображения в карточке погоды
 *
 * @property [feelsLike] температура "ощущается как"
 * @property [date] дата для отображения
 * @property [humidity] влажность в процентах
 * @property [windSpeed] скорость ветра
 * @property [chanceOfRain] вероятность осадков в процентах
 * @property [disclaimer] опциональный дисклеймер
 * @property [temperatureUnit] единица измерения температуры (по умолчанию "°")
 * @property [humidityUnit] единица измерения влажности (по умолчанию "%")
 * @property [windSpeedUnit] единица измерения скорости ветра (по умолчанию "м/с")
 * @property [chanceOfRainUnit] единица измерения вероятности осадков (по умолчанию "%")
 */
@Immutable
data class CurrentWeatherSummaryData(
    val feelsLike: Int,
    val date: LocalDate,
    val humidity: Int,
    val windSpeed: Double,
    val chanceOfRain: Int,
    val disclaimer: String? = null,
    val temperatureUnit: String = CurrentWeatherSummaryConstants.DEFAULT_TEMPERATURE_UNIT,
    val humidityUnit: String = CurrentWeatherSummaryConstants.DEFAULT_HUMIDITY_UNIT,
    val windSpeedUnit: String = CurrentWeatherSummaryConstants.DEFAULT_WIND_SPEED_UNIT,
    val chanceOfRainUnit: String = CurrentWeatherSummaryConstants.DEFAULT_CHANCE_OF_RAIN_UNIT,
)

private object CurrentWeatherSummaryConstants {
    const val DEFAULT_HUMIDITY_UNIT = "%"
    const val DEFAULT_WIND_SPEED_UNIT = "м/с"
    const val DEFAULT_CHANCE_OF_RAIN_UNIT = "%"
    const val DEFAULT_TEMPERATURE_UNIT = "°"
}

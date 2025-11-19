package space.serphantom.myweather.app.ui.compose.data.entity.daily_forecast

import androidx.compose.runtime.Immutable
import java.time.LocalDate


/**
 * `Data class` представляющий данные прогноза погоды для одного дня
 *
 * @property [date] дата прогноза
 * @property [weatherIconUrl] `URL` иконки погодных условий
 * @property [precipitationChance] вероятность осадков в процентах
 * @property [precipitationLabel] текстовая метка для осадков
 * @property [minTemperature] минимальная температура
 * @property [maxTemperature] максимальная температура
 * @property [temperatureUnit] единица измерения температуры
 *
 * @see DailyForecastData
 */
@Immutable
data class DailyForecastItemData(
    val date: LocalDate,
    val weatherIconUrl: String?,
    val precipitationChance: Int?,
    val precipitationLabel: String,
    val minTemperature: Int,
    val maxTemperature: Int,
    val temperatureUnit: String,
)

package space.serphantom.myweather.app.ui.compose.data.entity.daily_forecast

import androidx.compose.runtime.Immutable

/**
 * `Data class` представляющий данные для компонента прогноза по дням
 *
 * @property [days] список данных по дням для отображения прогноза
 *
 * @see DailyForecastItemData
 */
@Immutable
data class DailyForecastData(
    val days: List<DailyForecastItemData>,
)

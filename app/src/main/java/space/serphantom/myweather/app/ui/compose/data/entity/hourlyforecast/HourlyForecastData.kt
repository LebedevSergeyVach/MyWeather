package space.serphantom.myweather.app.ui.compose.data.entity.hourlyforecast

import androidx.compose.runtime.Immutable

/**
 * Data class, представляющий данные одного элемента почасового прогноза погоды.
 *
 * @property [time] Время прогноза в формате строки (например, "14:00", "Сейчас")
 * @property [temperature] Температура в указанный час с единицей измерения (например, "23°C")
 * @property [weatherIconUrl] `URL` иконки погодных условий для загрузки
 * @property [weatherCondition] Текстовое описание погодных условий (например, "Солнечно", "Облачно")
 *
 * @see Immutable
 */
@Immutable
data class HourlyForecastData(
    val time: String,
    val temperature: String,
    val weatherIconUrl: String?,
    val weatherCondition: String?,
)

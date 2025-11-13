package space.serphantom.myweather.app.ui.compose.entity.appbar

import androidx.compose.runtime.Immutable

/**
 * `Data class` содержащий все необходимые данные для отображения погоды в `toolbar`
 *
 * @property cityName Название города
 * @property disclaimer Описание погодных условий
 * @property temperature Текущая температура
 * @property minTemperature Минимальная температура
 * @property maxTemperature Максимальная температура
 * @property unitMeasurement Единица измерения температуры
 * @property weatherIconUrl URL иконки погоды
 */
@Immutable
data class MainWeatherTopAppBarData(
    val cityName: String,
    val disclaimer: String?,
    val temperature: String,
    val minTemperature: String,
    val maxTemperature: String,
    val unitMeasurement: String,
    val weatherIconUrl: String?,
)

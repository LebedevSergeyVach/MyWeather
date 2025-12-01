package space.serphantom.myweather.app.ui.compose.components.weather_metrics.wind_speed_metric

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import space.serphantom.myweather.app.ui.compose.components.weather_metrics.WeatherMetricCardComponent
import space.serphantom.myweather.app.ui.compose.extensions.common.getDegreesFromWindDirection

/**
 * Компонент карточки для отображения метрики скорости и направления ветра.
 * Объединяет базовую карточку метрики с индикатором направления ветра.
 *
 * @param [windSpeed] Скорость ветра в км/ч
 * @param [windDirection] Направление в константах
 * @param [modifier] Модификатор для кастомизации внешнего вида и поведения
 */
@Composable
fun WindSpeedMetricComponent(
    windSpeed: Int,
    windDirection: String,
    modifier: Modifier = Modifier,
) {
    WeatherMetricCardComponent(
        title = "Максимальная\nскорость ветра",
        value = "$windDirection, $windSpeed км/ч",
        description = "Средняя скорость ветра",
        modifier = modifier,
    ) {
        WindDirectionIndicatorComponent(
            windDirectionDegrees = getDegreesFromWindDirection(windDirection)
        )
    }
}

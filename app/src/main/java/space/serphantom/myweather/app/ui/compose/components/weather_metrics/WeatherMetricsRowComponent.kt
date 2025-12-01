package space.serphantom.myweather.app.ui.compose.components.weather_metrics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import space.serphantom.myweather.app.ui.compose.components.weather_metrics.humidity_metric.HumidityMetricComponent
import space.serphantom.myweather.app.ui.compose.components.weather_metrics.wind_speed_metric.WindSpeedMetricComponent

/**
 * Компонент ряда для отображения метрик погоды в горизонтальном расположении.
 * Гарантирует равное распределение пространства между метриками.
 *
 * @param [windSpeed] Скорость ветра в км/ч
 * @param [windDirection] Направление в константах
 * @param [humidity] Уровень влажности в процентах (`0-100`)
 * @param [modifier] Модификатор для кастомизации внешнего вида и поведения
 *
 * @see WindSpeedMetricComponent
 * @see HumidityMetricComponent
 */
@Composable
fun WeatherMetricsRowComponent(
    windSpeed: Int,
    windDirection: String,
    humidity: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(METRICS_ROW_SPACING)
    ) {
        WindSpeedMetricComponent(
            windSpeed = windSpeed,
            windDirection = windDirection,
            modifier = Modifier.weight(WEIGHT_RATIO)
        )

        HumidityMetricComponent(
            humidity = humidity,
            modifier = Modifier.weight(WEIGHT_RATIO)
        )
    }
}

private const val WEIGHT_RATIO = 1f
private val METRICS_ROW_SPACING = 8.dp

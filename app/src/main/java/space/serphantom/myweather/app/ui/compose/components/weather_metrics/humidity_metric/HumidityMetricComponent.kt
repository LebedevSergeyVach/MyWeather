package space.serphantom.myweather.app.ui.compose.components.weather_metrics.humidity_metric

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import space.serphantom.myweather.app.ui.compose.components.weather_metrics.WeatherMetricCardComponent

/**
 * Компонент карточки для отображения метрики влажности.
 * Объединяет базовую карточку метрики с индикатором уровня влажности.
 *
 * @param [humidity] Уровень влажности в процентах (`0-100`)
 * @param [modifier] Модификатор для кастомизации внешнего вида и поведения
 */
@Composable
fun HumidityMetricComponent(
    humidity: Int,
    modifier: Modifier = Modifier,
) {
    WeatherMetricCardComponent(
        title = "Средняя дневная влажность",
        value = "$humidity%",
        description = "Высокая влажность",
        modifier = modifier,
    ) {
        HumidityIndicatorComponent(humidity = humidity)
    }
}

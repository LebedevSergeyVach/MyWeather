package space.serphantom.myweather.app.ui.compose.components.current_weather_summary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudCircle
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WindPower
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import space.serphantom.myweather.app.ui.compose.data.entity.current_weather_summary.CurrentWeatherSummaryData
import space.serphantom.myweather.app.ui.compose.extensions.cards.AppFilledCard
import space.serphantom.myweather.app.ui.compose.extensions.dividers.HorizontalDividerComponent
import space.serphantom.myweather.app.ui.compose.theme.AppTheme
import space.serphantom.myweather.utils.DateUtils
import java.time.LocalDate

/**
 * Компонент для отображения текущей погодной сводки
 *
 * Представляет собой основную информационную панель с ключевыми метеорологическими данными
 * на текущий момент времени. Используется в качестве центрального элемента на главном экране
 * приложения между `TopAppBar` и блоками с прогнозами.
 *
 * @param [weatherData] данные о текущей погоде для отображения
 * @param [modifier] модификатор для кастомизации внешнего вида и поведения компонента
 *
 * @sample [CurrentWeatherSummaryComponentPreview]
 *
 * @see AppFilledCard
 */
@Composable
fun CurrentWeatherSummaryComponent(
    onClick: () -> Unit = {},
    weatherData: CurrentWeatherSummaryData,
    modifier: Modifier = Modifier,
) {
    AppFilledCard(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = CONTENT_SPACING,
                alignment = Alignment.CenterVertically,
            ),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(CARD_PADDING),
        ) {
            DateTextComponent(weatherData.date)
            WeatherContentComponent(weatherData)
            DisclaimerComponent(weatherData.disclaimer)
        }
    }
}

/**
 * Компонент для отображения даты
 *
 * @param [date] дата для отображения
 *
 * @see DateUtils
 */
@Composable
private fun DateTextComponent(date: LocalDate) {
    Text(
        text = DateUtils.formatDateWithDayOfWeek(date),
        style = AppTheme.typography.titleMedium,
    )
}

/**
 * Компонент основного контента с температурой и параметрами погоды
 *
 * @param [weatherData] данные погоды
 */
@Composable
private fun WeatherContentComponent(weatherData: CurrentWeatherSummaryData) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.fillMaxWidth(),
    ) {
        TemperatureComponent(
            feelsLike = weatherData.feelsLike,
            temperatureUnit = weatherData.temperatureUnit,
        )

        WeatherParametersComponent(
            humidity = weatherData.humidity,
            windSpeed = weatherData.windSpeed,
            chanceOfRain = weatherData.chanceOfRain,
            humidityUnit = weatherData.humidityUnit,
            windSpeedUnit = weatherData.windSpeedUnit,
            chanceOfRainUnit = weatherData.chanceOfRainUnit,
        )
    }
}

/**
 * Компонент для отображения температуры
 *
 * @param [feelsLike] значение температуры "ощущается как"
 * @param [temperatureUnit] единица измерения температуры
 */
@Composable
private fun TemperatureComponent(
    feelsLike: Int,
    temperatureUnit: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = CONTENT_SPACING / 2,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.wrapContentSize(),
    ) {
        Text(
            text = "$feelsLike$temperatureUnit",
            style = AppTheme.typography.displaySmall,
        )

        // TODO strings
        Text(
            text = "Ощущается как",
            style = AppTheme.typography.bodySmall,
        )
    }
}

/**
 * Компонент для отображения параметров погоды
 *
 * @param [humidity] влажность
 * @param [windSpeed] скорость ветра
 * @param [chanceOfRain] вероятность осадков
 * @param [humidityUnit] единица измерения влажности
 * @param [windSpeedUnit] единица измерения скорости ветра
 * @param [chanceOfRainUnit] единица измерения вероятности осадков
 */
@Composable
private fun WeatherParametersComponent(
    humidity: Int,
    windSpeed: Double,
    chanceOfRain: Int,
    humidityUnit: String,
    windSpeedUnit: String,
    chanceOfRainUnit: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = PARAMETER_ROW_SPACING,
            alignment = Alignment.Top,
        ),
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        WeatherParameterRowComponent(
            imageVector = Icons.Default.WaterDrop,
            label = "Влажность",
            value = humidity,
            unit = humidityUnit,
        )

        WeatherParameterRowComponent(
            imageVector = Icons.Default.WindPower,
            label = "Ветер",
            value = windSpeed,
            unit = windSpeedUnit,
        )

        WeatherParameterRowComponent(
            imageVector = Icons.Default.CloudCircle,
            label = "Осадки",
            value = chanceOfRain,
            unit = chanceOfRainUnit,
        )
    }
}

/**
 * Компонент строки с параметром погоды
 *
 * @param [imageVector] иконка параметра
 * @param [label] название параметра
 * @param [value] числовое значение параметра
 * @param [unit] единица измерения параметра
 */
@Composable
private fun WeatherParameterRowComponent(
    imageVector: ImageVector,
    label: String,
    value: Number,
    unit: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = PARAMETER_INTERNAL_SPACING,
            alignment = Alignment.Start,
        ),
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = AppTheme.color.iconTintColor,
            modifier = Modifier.size(ICON_SIZE),
        )

        Text(
            text = "$label:",
            style = AppTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = "$value$unit",
            style = AppTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}

/**
 * Компонент для отображения дисклеймера
 *
 * @param [disclaimer] текст дисклеймера
 */
@Composable
private fun DisclaimerComponent(disclaimer: String?) {
    disclaimer?.let { text ->
        HorizontalDividerComponent()

        Text(
            text = text,
            style = AppTheme.typography.titleDisclaimer,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

private val CARD_PADDING = 16.dp
private val CONTENT_SPACING = 8.dp
private val PARAMETER_ROW_SPACING = 8.dp
private val ICON_SIZE = 16.dp
private val PARAMETER_INTERNAL_SPACING = 8.dp

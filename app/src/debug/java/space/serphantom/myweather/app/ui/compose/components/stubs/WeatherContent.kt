package space.serphantom.myweather.app.ui.compose.components.stubs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import space.serphantom.myweather.app.ui.compose.components.CurrentWeatherSummaryComponent
import space.serphantom.myweather.app.ui.compose.components.weatherhours.HourlyForecastComponent
import space.serphantom.myweather.app.ui.compose.data.entity.current_weather_summary.CurrentWeatherSummaryData
import space.serphantom.myweather.app.ui.compose.data.entity.hourlyforecast.HourlyForecastData
import space.serphantom.myweather.app.ui.compose.extensions.cards.AppCard
import space.serphantom.myweather.app.ui.compose.extensions.cards.StyledCard
import java.time.LocalDate

internal fun createTestWeatherHourDataList(count: Int = 24): List<HourlyForecastData> {
    val testList = mutableListOf<HourlyForecastData>()
    for (i in 0 until count) {
        val hour = i
        val temperature = "${15 + (i % 10)}°" // Пример изменения температуры
        val description = if (i % 3 == 0) "Солнечно" else if (i % 3 == 1) "Облачно" else "Дождь"

        // Пример URL для иконок. В реальном приложении это будут ссылки на CDN.
        // Здесь используем заглушки или сервисы, предоставляющие тестовые изображения.
        // Например, https://openweathermap.org/img/wn/10d@2x.png для реальных иконок OpenWeatherMap
        val iconUrl = when (i % 3) {
            0 -> "https://openweathermap.org/img/wn/01d@2x.png" // Солнечно
            1 -> "https://openweathermap.org/img/wn/04d@2x.png" // Облачно
            else -> "https://openweathermap.org/img/wn/09d@2x.png" // Дождь
        }

        testList.add(
            HourlyForecastData(
                time = if (i == 0) "сейчас" else "$hour:00",
                temperature = temperature,
                weatherIconUrl = iconUrl,
                weatherCondition = description
            )
        )
    }
    return testList
}

@Composable
fun WeatherContent(
    scrollState: LazyListState,
    paddingValues: PaddingValues,
    onCityClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = paddingValues,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
//        item {
//            CurrentWeatherCard(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 64.dp)
//            )
//        }

        item {
            CurrentWeatherSummaryComponent(
                weatherData = CurrentWeatherSummaryData(
                    feelsLike = 34,
                    date = LocalDate.now(),
                    humidity = 65,
                    windSpeed = 3.2,
                    chanceOfRain = 15,
                    disclaimer = "Ближайшие дни ожидаются ураганы и плохая погода. Телефон 112."
                ),
                modifier = Modifier.padding(top = 64.dp)
            )
        }

        item {
            val lazyListState = rememberLazyListState()
            HourlyForecastComponent(
                hourlyForecastData = createTestWeatherHourDataList(),
                lazyListState = lazyListState,
            )
        }

        items(20) { index ->
            WeatherDayItem(
                day = "Пн, ${12 + index} июн",
                highTemp = "${25 + index}°C",
                lowTemp = "${15 + index}°C",
                condition = if (index % 2 == 0) "Солнечно" else "Облачно",
                onClick = onCityClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// CurrentWeatherCard.kt
@Composable
fun CurrentWeatherCard(modifier: Modifier = Modifier) {
    StyledCard(
        style = AppCard.noneElevationStyle(),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Сейчас",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "23°",
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "Ощущается как 25°",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Влажность: 45%",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Ветер: 5 м/с",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

// WeatherDayItem.kt
@Composable
fun WeatherDayItem(
    day: String,
    highTemp: String,
    lowTemp: String,
    condition: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = day,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = condition,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1f)
            )

            Row {
                Text(
                    text = highTemp,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = lowTemp,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// HourlyForecastSection.kt
@Composable
fun HourlyForecastSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Почасовой прогноз",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(24) { hour ->
                HourlyWeatherItem(
                    time = "${hour}:00",
                    temperature = "${20 + hour % 5}°",
                    icon = Icons.Default.WbSunny
                )
            }
        }
    }
}

// HourlyWeatherItem.kt
@Composable
fun HourlyWeatherItem(
    time: String,
    temperature: String,
    icon: ImageVector,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(60.dp)
            .padding(8.dp)
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(4.dp))

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = temperature,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

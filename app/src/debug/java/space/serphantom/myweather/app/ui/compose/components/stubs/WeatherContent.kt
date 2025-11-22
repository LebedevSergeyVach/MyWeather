package space.serphantom.myweather.app.ui.compose.components.stubs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import space.serphantom.myweather.app.ui.compose.components.current_weather_summary.CurrentWeatherSummaryComponent
import space.serphantom.myweather.app.ui.compose.components.daily_forecast.DailyForecastComponent
import space.serphantom.myweather.app.ui.compose.components.weatherhours.HourlyForecastComponent
import space.serphantom.myweather.app.ui.compose.data.entity.current_weather_summary.CurrentWeatherSummaryData
import space.serphantom.myweather.app.ui.compose.data.entity.daily_forecast.DailyForecastData
import space.serphantom.myweather.app.ui.compose.data.entity.daily_forecast.DailyForecastItemData
import space.serphantom.myweather.app.ui.compose.data.entity.hourlyforecast.HourlyForecastData
import java.time.LocalDate

@Composable
fun WeatherContent(
    scrollState: LazyListState,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    var showColorPalette by remember { mutableStateOf(false) }
    var useDynamicColors by remember { mutableStateOf(false) }

    val handleDismiss = { showColorPalette = false }

    val handleToggleDynamicColors = { useDynamicColors = useDynamicColors.not() }

    if (showColorPalette) {
        ColorPaletteBottomSheet(
            useDynamicColors = useDynamicColors,
            onDismiss = handleDismiss,
            onCheckedChange = { _ -> handleToggleDynamicColors() },
        )
    }

    LazyColumn(
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = paddingValues,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
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

        item {
            DailyForecastComponent(
                onShowMoreClick = { showColorPalette = true },
                onShowMoreForDateClick = {},
                forecastData = DailyForecastData(days = createTestDailyForecastData()),
                initialVisibleItems = 7,
            )
        }

        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
            )
        }
    }
}

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

/**
 * Создает список тестовых данных для прогноза погоды по дням
 *
 * Генерирует 20 элементов с различными значениями температур, осадков и иконок погоды.
 * Используется для предварительного просмотра и тестирования компонентов прогноза погоды.
 *
 * @param startDate начальная дата для генерации прогноза (по умолчанию текущая дата)
 * @return список из 20 элементов [DailyForecastItemData] с тестовыми данными
 */
internal fun createTestDailyForecastData(startDate: LocalDate = LocalDate.now()): List<DailyForecastItemData> {
    val weatherIcons = listOf(
        "https://openweathermap.org/img/wn/01d@2x.png", // ясно
        "https://openweathermap.org/img/wn/02d@2x.png", // малооблачно
        "https://openweathermap.org/img/wn/03d@2x.png", // облачно
        "https://openweathermap.org/img/wn/04d@2x.png", // пасмурно
        "https://openweathermap.org/img/wn/09d@2x.png", // ливень
        "https://openweathermap.org/img/wn/10d@2x.png", // дождь
        "https://openweathermap.org/img/wn/11d@2x.png", // гроза
        "https://openweathermap.org/img/wn/13d@2x.png", // снег
        "https://openweathermap.org/img/wn/50d@2x.png"  // туман
    )

    val precipitationLabels = listOf(
        "Ясно",
        "Малооблачно",
        "Облачно",
        "Пасмурно",
        "Ливень",
        "Дождь",
        "Гроза",
        "Снег",
        "Туман",
        "Переменная облачность",
        "Небольшой дождь",
        "Снегопад"
    )

    return List(20) { index ->
        val date = startDate.plusDays(index.toLong())
        val iconIndex = index % weatherIcons.size
        val labelIndex = index % precipitationLabels.size
        val precipitationChance = when {
            index % 5 == 0 -> null
            index % 3 == 0 -> (10..30).random()
            index % 2 == 0 -> (40..60).random()
            else -> (70..90).random()
        }

        val baseTemp = when (index % 4) {
            0 -> 15 // теплые дни
            1 -> 10 // прохладные дни
            2 -> 5  // холодные дни
            else -> 20 // жаркие дни
        }

        val tempVariation = (3..8).random()

        DailyForecastItemData(
            date = date,
            weatherIconUrl = weatherIcons[iconIndex],
            precipitationChance = precipitationChance,
            precipitationLabel = "",
            minTemperature = baseTemp - tempVariation,
            maxTemperature = baseTemp + tempVariation,
            temperatureUnit = "°"
        )
    }
}

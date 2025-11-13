/**
 * Preview компоненты для [MainWeatherTopAppBarComponent].
 * Этот файл компилируется только в debug-сборке и не попадает в production.
 *
 * @see Preview
 * @see [MainWeatherTopAppBarComponent]
 */
package space.serphantom.myweather.app.ui.compose.components.appbar

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import dev.chrisbanes.haze.ExperimentalHazeApi
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import space.serphantom.myweather.app.ui.compose.components.stubs.WeatherContent
import space.serphantom.myweather.app.ui.compose.entity.appbar.MainWeatherTopAppBarData
import space.serphantom.myweather.app.ui.compose.extensions.modifiers.hapticScrollEdge
import space.serphantom.myweather.app.ui.compose.theme.AppTheme

/**
 * Образец данных для `preview` компонентов.
 * Содержит тестовые данные для отображения в `Android Studio` [Preview].
 */
private val sampleWeatherData = MainWeatherTopAppBarData(
    cityName = "Новосибирск",
    disclaimer = "Сегодня пасмурная погода весь день",
    temperature = "23",
    minTemperature = "15",
    maxTemperature = "28",
    unitMeasurement = "°C",
    weatherIconUrl = "https://cdn-icons-png.flaticon.com/128/7512/7512545.png"
)

/**
 * [Preview] развернутого состояния [MainWeatherTopAppBarComponent].
 * Отображает компонент в полностью развернутом состоянии (`collapsedFraction = 0f`).
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalHazeApi::class)
@Preview(
    name = "Weather Top App Bar - Expanded",
    group = "Weather Components",
    showBackground = true,
    backgroundColor = 0xFFF5F5F5,
    device = Devices.PIXEL_7,
    showSystemUi = true,
)
@Composable
private fun MainWeatherTopAppBarComponentPreview() {
    val scrollState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val hazeState = rememberHazeState()

    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    MainWeatherTopAppBarComponent(
                        scrollBehavior = scrollBehavior,
                        hazeState = hazeState,
                        weatherData = sampleWeatherData,
                    )
                },
            ) { paddingValues ->
                WeatherContent(
                    scrollState = scrollState,
                    paddingValues = paddingValues,
                    onCityClick = { },
                    modifier = Modifier
                        .hazeSource(state = hazeState)
                        .hapticScrollEdge(lazyListState = scrollState),
                )
            }
        }
    }
}

/**
 * [Preview] свернутого состояния [MainWeatherTopAppBarComponent].
 * Отображает компонент в полностью свернутом состоянии (`collapsedFraction = 1f`).
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalHazeApi::class)
@Preview(
    name = "Weather Top App Bar - Expanded",
    group = "Weather Components",
    showBackground = true,
    backgroundColor = 0xFFF5F5F5,
    device = Devices.PIXEL_7,
    showSystemUi = true,
)
@Composable
private fun WeatherTopAppBarCollapsedPreview() {
    val scrollState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val hazeState = rememberHazeState()

    scrollBehavior.state.heightOffset = scrollBehavior.state.heightOffsetLimit

    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    MainWeatherTopAppBarComponent(
                        scrollBehavior = scrollBehavior,
                        hazeState = hazeState,
                        weatherData = sampleWeatherData,
                    )
                },
            ) { paddingValues ->
                WeatherContent(
                    scrollState = scrollState,
                    paddingValues = paddingValues,
                    onCityClick = { },
                    modifier = Modifier
                        .hazeSource(state = hazeState)
                        .hapticScrollEdge(lazyListState = scrollState),
                )
            }
        }
    }
}

/**
 * [Preview] темной темы для [MainWeatherTopAppBarComponent].
 * Демонстрирует отображение компонента в темной теме.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalHazeApi::class)
@Preview(
    name = "Weather Top App Bar - Dark Theme",
    group = "Weather Components",
    showBackground = true,
    backgroundColor = 0xFF121212,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_7,
    showSystemUi = true,
)
@Composable
private fun WeatherTopAppBarDarkPreview() {
    val scrollState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val hazeState = rememberHazeState()

    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    MainWeatherTopAppBarComponent(
                        scrollBehavior = scrollBehavior,
                        hazeState = hazeState,
                        weatherData = sampleWeatherData,
                    )
                },
            ) { paddingValues ->
                WeatherContent(
                    scrollState = scrollState,
                    paddingValues = paddingValues,
                    onCityClick = { },
                    modifier = Modifier
                        .hazeSource(state = hazeState)
                        .hapticScrollEdge(lazyListState = scrollState),
                )
            }
        }
    }
}

/**
 * [Preview] компонента с длинным текстом.
 * Тестирует отображение компонента с экстремально длинными текстовыми значениями.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalHazeApi::class)
@Preview(
    name = "Weather Top App Bar - Long Text",
    group = "Weather Components - Edge Cases",
    showBackground = true,
    device = Devices.PIXEL_7,
    showSystemUi = true,
)
@Composable
private fun WeatherTopAppBarLongTextPreview() {
    val longTextData = MainWeatherTopAppBarData(
        cityName = "Очень длинное название города которое не помещается",
        disclaimer = "Очень длинное описание погодных условий, которое должно занимать несколько строк и проверять как компонент обрабатывает многострочный текст",
        temperature = "-100",
        minTemperature = "-150",
        maxTemperature = "50",
        unitMeasurement = "°C",
        weatherIconUrl = null
    )

    val scrollState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val hazeState = rememberHazeState()

    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    MainWeatherTopAppBarComponent(
                        scrollBehavior = scrollBehavior,
                        hazeState = hazeState,
                        weatherData = longTextData,
                    )
                },
            ) { paddingValues ->
                WeatherContent(
                    scrollState = scrollState,
                    paddingValues = paddingValues,
                    onCityClick = { },
                    modifier = Modifier
                        .hazeSource(state = hazeState)
                        .hapticScrollEdge(lazyListState = scrollState),
                )
            }
        }
    }
}

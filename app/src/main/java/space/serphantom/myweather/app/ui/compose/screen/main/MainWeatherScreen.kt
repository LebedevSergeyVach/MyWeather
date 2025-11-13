package space.serphantom.myweather.app.ui.compose.screen.main

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import space.serphantom.myweather.app.ui.compose.components.appbar.MainWeatherTopAppBarComponent
import space.serphantom.myweather.app.ui.compose.entity.appbar.MainWeatherTopAppBarData
import space.serphantom.myweather.app.ui.compose.components.stubs.WeatherContent
import space.serphantom.myweather.app.ui.compose.extensions.modifiers.hapticScrollEdge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainWeatherScreen() {
    val scrollState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val hazeState = rememberHazeState()

    val weatherData = MainWeatherTopAppBarData(
        cityName = "Новосибирск",
        disclaimer = "Сегодня пасмурная погода весь день",
        temperature = "37",
        minTemperature = "-7",
        maxTemperature = "0",
        unitMeasurement = "°",
        weatherIconUrl = "https://cdn-icons-png.flaticon.com/128/7512/7512545.png"
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MainWeatherTopAppBarComponent(
                scrollBehavior = scrollBehavior,
                hazeState = hazeState,
                weatherData = weatherData,
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

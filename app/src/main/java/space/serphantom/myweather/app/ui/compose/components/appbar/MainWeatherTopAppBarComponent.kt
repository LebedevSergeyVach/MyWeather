package space.serphantom.myweather.app.ui.compose.components.appbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import space.serphantom.myweather.R
import space.serphantom.myweather.app.ui.compose.components.images.ImageComponent
import space.serphantom.myweather.app.ui.compose.data.entity.appbar.MainWeatherTopAppBarData
import space.serphantom.myweather.app.ui.compose.extensions.modifiers.blockAllGestures
import space.serphantom.myweather.app.ui.compose.theme.AppTheme

/**
 * Главный компонент верхней панели приложения погоды с поддержкой скролла и эффектом размытия
 *
 * @param [scrollBehavior] Поведение скролла для синхронизации с контентом
 * @param [hazeState] Состояние эффекта размытия фона
 * @param [weatherData] Данные о погоде для отображения
 * @param [modifier] Базовый модификатор для настройки внешнего вида
 * @param [hazeStyle] Стиль эффекта размытия фона
 *
 * @see TopAppBarScrollBehavior
 * @see HazeState
 * @see MainWeatherTopAppBarData
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalHazeMaterialsApi::class)
@Composable
fun MainWeatherTopAppBarComponent(
    scrollBehavior: TopAppBarScrollBehavior,
    hazeState: HazeState,
    weatherData: MainWeatherTopAppBarData,
    modifier: Modifier = Modifier,
    hazeStyle: HazeStyle = HazeMaterials.ultraThin(AppTheme.color.appBarColor),
) {
    val collapsedFraction = scrollBehavior.state.collapsedFraction

    val hazeModifier = remember(collapsedFraction) {
        if (collapsedFraction > MainWeatherTopAppBarConstants.COLLAPSE_THRESHOLD) {
            modifier.hazeEffect(
                state = hazeState,
                style = hazeStyle,
            )
        } else modifier
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .blockAllGestures(),
    ) {
        LargeTopAppBar(
            title = {
                WeatherTitleComponent(
                    weatherData = weatherData,
                    collapsedFraction = collapsedFraction,
                )
            },
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent,
            ),
            scrollBehavior = scrollBehavior,
            modifier = hazeModifier.fillMaxWidth(),
        )
    }
}

/**
 * Основной компонент заголовка с двумя состояниями: развернутым и свернутым
 *
 * @param [weatherData] Данные о погоде для отображения
 * @param [collapsedFraction] Доля свернутости (`0f` - полностью развернут, `1f` - полностью свернут)
 * @param [modifier] Базовый модификатор для настройки внешнего вида
 */
@Composable
private fun WeatherTitleComponent(
    weatherData: MainWeatherTopAppBarData,
    collapsedFraction: Float,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = MainWeatherTopAppBarConstants.TITLE_END_PADDING.dp),
        contentAlignment = Alignment.Center,
    ) {
        CollapsedWeatherComponent(
            weatherData = weatherData,
            collapsedFraction = collapsedFraction,
        )

        ExpandedWeatherComponent(
            weatherData = weatherData,
            collapsedFraction = collapsedFraction,
        )
    }
}

/**
 * Компонент отображения в свернутом состоянии (показывается при скролле вниз)
 *
 * @param [weatherData] Данные о погоде для отображения
 * @param [collapsedFraction] Доля свернутости для управления прозрачностью
 * @param [modifier] Базовый модификатор для настройки внешнего вида
 */
@Composable
private fun CollapsedWeatherComponent(
    weatherData: MainWeatherTopAppBarData,
    collapsedFraction: Float,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .alpha(1f - collapsedFraction)
            .fillMaxWidth()
    ) {
        TemperatureDisplayComponent(
            temperature = weatherData.temperature,
            unitMeasurement = weatherData.unitMeasurement,
        )

        Text(
            text = weatherData.cityName,
            maxLines = MainWeatherTopAppBarConstants.MAX_LINES_CITY_COLLAPSED,
            style = AppTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/**
 * Компонент отображения в развернутом состоянии (показывается при скролле вверх)
 *
 * @param [weatherData] Данные о погоде для отображения
 * @param [collapsedFraction] Доля свернутости для управления прозрачностью
 * @param [modifier] Базовый модификатор для настройки внешнего вида
 */
@Composable
private fun ExpandedWeatherComponent(
    weatherData: MainWeatherTopAppBarData,
    collapsedFraction: Float,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .alpha(collapsedFraction),
    ) {
        CityTemperatureRowComponent(
            cityName = weatherData.cityName,
            temperature = weatherData.temperature,
            unitMeasurement = weatherData.unitMeasurement,
            weatherIconUrl = weatherData.weatherIconUrl,
        )

        TemperatureRangeComponent(
            minTemperature = weatherData.minTemperature,
            maxTemperature = weatherData.maxTemperature,
            unitMeasurement = weatherData.unitMeasurement,
        )

        WeatherDisclaimerComponent(disclaimer = weatherData.disclaimer)
    }
}

/**
 * Компонент отображения диапазона температур (`мин`/`макс`)
 *
 * @param [minTemperature] Значение минимальной температуры
 * @param [maxTemperature] Значение максимальной температуры
 * @param [unitMeasurement] Единица измерения температуры
 */
@Composable
private fun TemperatureRangeComponent(
    minTemperature: String,
    maxTemperature: String,
    unitMeasurement: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = MainWeatherTopAppBarConstants.TEMPERATURE_SPACING.dp,
            alignment = Alignment.CenterHorizontally,
        ),
        modifier = modifier.wrapContentSize(),
    ) {
        TemperatureItemComponent(
            value = minTemperature,
            unit = unitMeasurement,
            labelResId = R.string.min_weather_value,
        )

        TemperatureItemComponent(
            value = maxTemperature,
            unit = unitMeasurement,
            labelResId = R.string.max_weather_value,
        )
    }
}

/**
 * Компонент отображения отдельного значения температуры с единицей измерения
 * Использует [ConstraintLayout] для точного контроля позиционирования
 *
 * @param [value] Значение температуры
 * @param [unit] Единица измерения
 * @param [labelResId] Resource `ID` для форматирования текста
 */
@Composable
private fun TemperatureItemComponent(
    value: String,
    unit: String,
    labelResId: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = MainWeatherTopAppBarConstants.UNIT_MARGIN.dp,
            alignment = Alignment.CenterHorizontally,
        ),
        modifier = modifier.wrapContentSize(),
    ) {
        Text(
            text = stringResource(labelResId, value),
            style = AppTheme.typography.titleMedium,
        )

        Text(
            text = unit,
            style = AppTheme.typography.titleLarge,
        )
    }
}

/**
 * Компонент отображения описания погодных условий
 *
 * @param [disclaimer] Текст описания погодных условий
 */
@Composable
private fun WeatherDisclaimerComponent(disclaimer: String?, modifier: Modifier = Modifier) {
    disclaimer?.let {
        Text(
            text = it,
            maxLines = MainWeatherTopAppBarConstants.MAX_LINES_DISCLAIMER,
            style = AppTheme.typography.titleDisclaimer,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.fillMaxWidth(),
        )
    }
}

/**
 * Компонент отображения основной температуры с крупным шрифтом
 *
 * @param [temperature] Значение температуры
 * @param [unitMeasurement] Единица измерения температуры
 * @param [modifier] Базовый модификатор для настройки внешнего вида
 */
@Composable
private fun TemperatureDisplayComponent(
    temperature: String,
    unitMeasurement: String,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (temperatureRef, unitRef) = remember { createRefs() }

        Text(
            text = temperature,
            style = AppTheme.typography.displayLarge,
            modifier = Modifier.constrainAs(temperatureRef) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )

        Text(
            text = unitMeasurement,
            style = AppTheme.typography.displayLarge,
            modifier = Modifier.constrainAs(unitRef) {
                start.linkTo(
                    anchor = temperatureRef.end,
                    margin = MainWeatherTopAppBarConstants.TEMPERATURE_UNIT_MARGIN.dp
                )
                top.linkTo(temperatureRef.top)
            }
        )
    }
}

/**
 * Компонент отображения строки с названием города, температурой и иконкой погоды
 *
 * @param [cityName] Название города
 * @param [temperature] Значение температуры
 * @param [unitMeasurement] Единица измерения температуры
 * @param [weatherIconUrl] URL иконки погоды
 * @param [modifier] Базовый модификатор для настройки внешнего вида
 */
@Composable
private fun CityTemperatureRowComponent(
    cityName: String,
    temperature: String,
    unitMeasurement: String,
    weatherIconUrl: String?,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (cityNameRef, weatherIconRef) = remember { createRefs() }
        val (temperatureRef, unitRef) = remember { createRefs() }
        val margin = MainWeatherTopAppBarConstants.DEFAULT_MARGIN.dp

        WeatherIconComponent(
            url = weatherIconUrl,
            modifier = Modifier
                .size(MainWeatherTopAppBarConstants.WEATHER_ICON_SIZE.dp)
                .constrainAs(weatherIconRef) {
                    end.linkTo(anchor = cityNameRef.start, margin = margin)
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = parent.bottom)
                }
        )

        Text(
            text = cityName,
            maxLines = MainWeatherTopAppBarConstants.MAX_LINES_CITY_EXPANDED,
            style = AppTheme.typography.titleLarge,
            modifier = Modifier.constrainAs(cityNameRef) {
                start.linkTo(anchor = parent.start)
                end.linkTo(anchor = parent.end)
                top.linkTo(anchor = parent.top)
                bottom.linkTo(anchor = parent.bottom)
            }
        )

        Text(
            text = temperature,
            style = AppTheme.typography.titleLarge,
            modifier = Modifier.constrainAs(temperatureRef) {
                start.linkTo(anchor = cityNameRef.end, margin = margin)
                top.linkTo(anchor = parent.top)
                bottom.linkTo(anchor = parent.bottom)
            }
        )

        Text(
            text = unitMeasurement,
            style = AppTheme.typography.headlineSmall,
            modifier = Modifier.constrainAs(unitRef) {
                start.linkTo(
                    anchor = temperatureRef.end,
                    margin = MainWeatherTopAppBarConstants.TEMPERATURE_UNIT_MARGIN_WITCH_CITY.dp,
                )
                top.linkTo(anchor = parent.top)
            }
        )
    }
}

/**
 * Компонент отображения иконки погоды
 *
 * @param [url] URL изображения иконки погоды
 * @param [modifier] Базовый модификатор для настройки размера и позиционирования
 */
@Composable
private fun WeatherIconComponent(
    url: String?,
    modifier: Modifier = Modifier,
) {
    ImageComponent(
        url = url,
        modifier = modifier,
    )
}

private object MainWeatherTopAppBarConstants {
    const val COLLAPSE_THRESHOLD = 0.5f
    const val WEATHER_ICON_SIZE = 24
    const val DEFAULT_MARGIN = 8
    const val TEMPERATURE_UNIT_MARGIN_WITCH_CITY = 4
    const val TEMPERATURE_UNIT_MARGIN = 6
    const val TITLE_END_PADDING = 16
    const val TEMPERATURE_SPACING = 4
    const val UNIT_MARGIN = 4
    const val MAX_LINES_DISCLAIMER = 2
    const val MAX_LINES_CITY_COLLAPSED = 2
    const val MAX_LINES_CITY_EXPANDED = 1
}

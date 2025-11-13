package space.serphantom.myweather.app.ui.compose.components.weatherhours

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import space.serphantom.myweather.R
import space.serphantom.myweather.app.ui.compose.components.images.ImageComponent
import space.serphantom.myweather.app.ui.compose.components.settings.ImageComponentSettings
import space.serphantom.myweather.app.ui.compose.entity.hourlyforecast.HourlyForecastData
import space.serphantom.myweather.app.ui.compose.extensions.modifiers.hapticScrollEdge
import space.serphantom.myweather.app.ui.compose.theme.AppTheme
import space.serphantom.myweather.app.ui.compose.extensions.HorizontalDividerComponent
import space.serphantom.myweather.app.ui.compose.extensions.cards.AppCard
import space.serphantom.myweather.app.ui.compose.extensions.cards.StyledCard

/**
 * Главный компонент для отображения карточки с почасовым прогнозом погоды.
 * Состоит из заголовка, разделительной линии и горизонтального списка прогнозов по часам.
 *
 * @param [hourlyForecastData] Список данных почасового прогноза для отображения
 * @param [lazyListState] Состояние lazy-списка для управления скроллом и отслеживания позиции
 * @param [modifier] Базовый модификатор для настройки внешнего вида компонента
 *
 * @see StyledCard
 * @see HourlyForecastData
 * @see LazyListState
 */
@Composable
fun HourlyForecastComponent(
    hourlyForecastData: List<HourlyForecastData>,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier,
) {
    StyledCard(
        style = AppCard.lowElevationStyle(),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = HourlyForecastConstants.CARD_HORIZONTAL_PADDING.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = HourlyForecastConstants.CARD_VERTICAL_PADDING.dp),
        ) {
            HourlyForecastHeaderComponent()
            HorizontalDividerComponent()
            HourlyForecastListComponent(
                lazyListState = lazyListState,
                hourlyForecastData = hourlyForecastData,
                modifier = Modifier.hapticScrollEdge(lazyListState),
            )
        }
    }
}

/**
 * Компонент заголовка для почасового прогноза погоды.
 * Отображает иконку часов и текст ["Почасовой прогноз"][R.string.hourly_forecast_title].
 *
 * @param [modifier] Базовый модификатор для настройки внешнего вида компонента
 */
@Composable
private fun HourlyForecastHeaderComponent(
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = HourlyForecastConstants.HEADER_SPACING.dp,
            alignment = Alignment.Start,
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = HourlyForecastConstants.HEADER_HORIZONTAL_PADDING.dp,
                vertical = HourlyForecastConstants.HEADER_VERTICAL_PADDING.dp,
            ),
    ) {
        Icon(
            imageVector = Icons.Default.Schedule,
            contentDescription = null,
            modifier = Modifier.size(HourlyForecastConstants.HEADER_ICON_SIZE.dp),
            tint = AppTheme.color.iconsTintColor,
        )

        Text(
            text = stringResource(R.string.hourly_forecast_title),
            style = AppTheme.typography.titleMedium,
        )
    }
}

/**
 * Компонент горизонтального списка с почасовыми прогнозами погоды.
 * Использует `LazyRow` для эффективного отображения большого количества элементов.
 *
 * @param [lazyListState] Состояние `lazy`-списка для управления скроллом
 * @param [hourlyForecastData] Список данных почасового прогноза для отображения
 * @param [modifier] Базовый модификатор для настройки внешнего вида компонента
 *
 * @see LazyListState
 * @see HourlyForecastData
 */
@Composable
private fun HourlyForecastListComponent(
    lazyListState: LazyListState,
    hourlyForecastData: List<HourlyForecastData>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        state = lazyListState,
        horizontalArrangement = Arrangement.spacedBy(
            space = HourlyForecastConstants.LIST_ITEM_SPACING.dp,
        ),
        contentPadding = PaddingValues(
            horizontal = HourlyForecastConstants.LIST_CONTENT_PADDING.dp,
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = HourlyForecastConstants.LIST_VERTICAL_PADDING.dp),
    ) {
        items(
            items = hourlyForecastData,
            key = { it.time },
        ) { forecastData ->
            HourlyForecastItemComponent(forecastData = forecastData)
        }
    }
}

/**
 * Компонент элемента почасового прогноза для отображения в списке.
 * Отображает температуру, иконку погоды и время в вертикальной колонке.
 *
 * @param [forecastData] Данные прогноза для отображения
 * @param [modifier] Базовый модификатор для настройки внешнего вида компонента
 *
 * @see HourlyForecastData
 */
@Composable
private fun HourlyForecastItemComponent(
    forecastData: HourlyForecastData,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = HourlyForecastConstants.ITEM_VERTICAL_SPACING.dp,
            alignment = Alignment.CenterVertically
        ),
    ) {
        Text(
            text = forecastData.temperature,
            style = AppTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        WeatherIconComponent(
            weatherIconUrl = forecastData.weatherIconUrl,
            weatherCondition = forecastData.weatherCondition,
        )

        Text(
            text = forecastData.time,
            style = AppTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/**
 * Компонент для отображения иконки погодных условий.
 * Использует ImageComponent с настройками для корректного отображения погодных иконок.
 *
 * @param [weatherIconUrl] URL иконки погоды для загрузки
 * @param [weatherCondition] Описание погодных условий, используемое как `contentDescription`
 * @param [modifier] Базовый модификатор для настройки внешнего вида компонента
 *
 * @see ImageComponent
 * @see ImageComponentSettings
 */
@Composable
private fun WeatherIconComponent(
    weatherIconUrl: String?,
    weatherCondition: String?,
    modifier: Modifier = Modifier,
) {
    ImageComponent(
        url = weatherIconUrl,
        settings = ImageComponentSettings(
            contentDescription = weatherCondition,
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center,
        ),
        modifier = modifier.size(HourlyForecastConstants.WEATHER_ICON_SIZE.dp),
    )
}

/**
 * Объект, содержащий константы для компонента почасового прогноза погоды.
 * Включает значения размеров, отступов, радиусов скругления и другие параметры `UI`.
 */
private object HourlyForecastConstants {
    // Card константы
    const val CARD_CORNER_RADIUS = 16
    const val CARD_ELEVATION = 0
    const val CARD_HORIZONTAL_PADDING = 16
    const val CARD_VERTICAL_PADDING = 8

    // Header константы
    const val HEADER_ICON_SIZE = 16
    const val HEADER_SPACING = 8
    const val HEADER_HORIZONTAL_PADDING = 16
    const val HEADER_VERTICAL_PADDING = 8

    // List константы
    const val LIST_ITEM_SPACING = 16
    const val LIST_CONTENT_PADDING = 16
    const val LIST_VERTICAL_PADDING = 8

    // Item константы
    const val ITEM_VERTICAL_SPACING = 4
    const val WEATHER_ICON_SIZE = 48
}

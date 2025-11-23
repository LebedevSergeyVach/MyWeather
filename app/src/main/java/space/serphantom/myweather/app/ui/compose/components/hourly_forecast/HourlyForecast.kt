package space.serphantom.myweather.app.ui.compose.components.hourly_forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import space.serphantom.myweather.R
import space.serphantom.myweather.app.ui.compose.components.images.ImageComponent
import space.serphantom.myweather.app.ui.compose.components.settings.ImageComponentSettings
import space.serphantom.myweather.app.ui.compose.data.entity.hourlyforecast.HourlyForecastData
import space.serphantom.myweather.app.ui.compose.extensions.cards.AppFilledCard
import space.serphantom.myweather.app.ui.compose.extensions.dividers.HorizontalDividerComponent
import space.serphantom.myweather.app.ui.compose.extensions.modifiers.hapticScrollEdge
import space.serphantom.myweather.app.ui.compose.theme.AppTheme

/**
 * Главный компонент для отображения карточки с почасовым прогнозом погоды.
 * Состоит из заголовка, разделительной линии и горизонтального списка прогнозов по часам.
 *
 * @param [hourlyForecastData] Список данных почасового прогноза для отображения
 * @param [lazyListState] Состояние lazy-списка для управления скроллом и отслеживания позиции
 * @param [modifier] Базовый модификатор для настройки внешнего вида компонента
 *
 * @see AppFilledCard
 * @see HourlyForecastData
 * @see LazyListState
 */
@Composable
fun HourlyForecastComponent(
    hourlyForecastData: List<HourlyForecastData>,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier,
) {
    AppFilledCard(
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = CARD_VERTICAL_PADDING.dp),
        ) {
            HourlyForecastHeaderComponent()
            HorizontalDividerComponent(horizontalPaddingDp = 16.dp)
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
            space = HEADER_SPACING.dp,
            alignment = Alignment.Start,
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = HEADER_HORIZONTAL_PADDING.dp),
    ) {
        Icon(
            imageVector = Icons.Default.Schedule,
            tint = AppTheme.color.iconTintColor,
            contentDescription = null,
            modifier = Modifier.size(HEADER_ICON_SIZE.dp),
        )

        Text(
            text = stringResource(R.string.hourly_forecast_title),
            style = AppTheme.typography.titleMedium,
        )
    }
}

/**
 * Компонент горизонтального списка с почасовыми прогнозами погоды.
 * Автоматически рассчитывает ширину элементов для отображения [VISIBLE_ITEMS_COUNT] компонентов.
 * Использует [BoxWithConstraints] для адаптации к различным размерам экрана.
 * Использует `LazyRow` для эффективного отображения большого количества элементов.
 *
 * @param [lazyListState] Состояние `lazy`-списка для управления скроллом
 * @param [hourlyForecastData] Список данных почасового прогноза для отображения
 * @param [modifier] Базовый модификатор для настройки внешнего вида компонента
 *
 * @see LazyListState
 * @see HourlyForecastData
 * @see BoxWithConstraints
 */
@Suppress("COMPOSE_APPLIER_CALL_MISMATCH")
@Composable
private fun HourlyForecastListComponent(
    lazyListState: LazyListState,
    hourlyForecastData: List<HourlyForecastData>,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val itemSpacing = ITEM_SPACING.dp
        val horizontalPadding = LIST_CONTENT_PADDING.dp

        val itemWidth = calculateItemWidth(
            availableWidth = maxWidth,
            horizontalPadding = horizontalPadding,
            itemSpacing = itemSpacing,
            visibleItemsCount = VISIBLE_ITEMS_COUNT
        )

        LazyRow(
            state = lazyListState,
            horizontalArrangement = Arrangement.spacedBy(itemSpacing),
            contentPadding = PaddingValues(horizontal = horizontalPadding),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                items = hourlyForecastData,
                key = { it.time }
            ) { forecastData ->
                HourlyForecastItemComponent(
                    forecastData = forecastData,
                    modifier = Modifier.width(itemWidth),
                )
            }
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
            space = ITEM_VERTICAL_SPACING.dp,
            alignment = Alignment.CenterVertically
        ),
    ) {
        Text(
            text = forecastData.temperature,
            style = AppTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
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
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
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
    )
}

/**
 * Рассчитывает оптимальную ширину элемента для отображения заданного количества компонентов.
 * Учитывает доступную ширину, отступы и промежутки между элементами.
 *
 * @param [availableWidth] Доступная ширина для размещения элементов
 * @param [horizontalPadding] Горизонтальные отступы контейнера
 * @param [itemSpacing] Промежуток между элементами
 * @param [visibleItemsCount] Количество видимых элементов (может быть дробным для частичного отображения)
 * @return Рассчитанная ширина элемента с ограничениями
 * [min][ITEM_MIN_WIDTH]/[max][ITEM_MAX_WIDTH]
 *
 * @see VISIBLE_ITEMS_COUNT
 */
@Composable
private fun calculateItemWidth(
    availableWidth: Dp,
    horizontalPadding: Dp,
    itemSpacing: Dp,
    visibleItemsCount: Float,
): Dp {
    val minWidth = ITEM_MIN_WIDTH.dp
    val maxWidth = ITEM_MAX_WIDTH.dp

    val totalHorizontalPadding = horizontalPadding * 2
    val totalSpacing = itemSpacing * (visibleItemsCount - 1).toInt()
    val calculatedWidth =
        (availableWidth - totalHorizontalPadding - totalSpacing) / visibleItemsCount

    return calculatedWidth.coerceIn(minimumValue = minWidth, maximumValue = maxWidth)
}

// Card константы
private const val CARD_VERTICAL_PADDING = 16

// Header константы
private const val HEADER_ICON_SIZE = 16
private const val HEADER_SPACING = 8
private const val HEADER_HORIZONTAL_PADDING = 16

// List константы
private const val LIST_CONTENT_PADDING = 16
private const val ITEM_SPACING = 16

// Адаптивные константы для отображения 5.5 элементов
private const val VISIBLE_ITEMS_COUNT = 5.5f

// Минимальная и максимальная ширина элемента
private const val ITEM_MIN_WIDTH = 32
private const val ITEM_MAX_WIDTH = 100

// Item константы
private const val ITEM_VERTICAL_SPACING = 4

package space.serphantom.myweather.app.ui.compose.components.daily_forecast

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import space.serphantom.myweather.app.ui.compose.components.images.ImageComponent
import space.serphantom.myweather.app.ui.compose.data.entity.daily_forecast.DailyForecastData
import space.serphantom.myweather.app.ui.compose.data.entity.daily_forecast.DailyForecastItemData
import space.serphantom.myweather.app.ui.compose.extensions.buttons.AppFilledButton
import space.serphantom.myweather.app.ui.compose.extensions.cards.AppCard
import space.serphantom.myweather.app.ui.compose.extensions.cards.StyledCard
import space.serphantom.myweather.app.ui.compose.extensions.dividers.HorizontalDividerComponent
import space.serphantom.myweather.app.ui.compose.theme.AppTheme
import space.serphantom.myweather.utils.DateUtils
import java.time.LocalDate

/**
 * Главный компонент для отображения прогноза погоды по дням
 *
 * Отображает карточку с заголовком и списком дней, каждый из которых содержит информацию
 * о дате, погодных условиях и температурном диапазоне. Поддерживает ограниченное количество
 * `initially` отображаемых элементов с возможностью показа остальных через `callback`.
 *
 * @param [onShowMoreClick] `callback` при нажатии на кнопку "Подробнее" для загрузки дополнительных дней
 * @param [onShowMoreForDateClick] `callback` при нажатии на конкретный день для детальной информации
 * @param [forecastData] данные для отображения прогноза по дням
 * @param [initialVisibleItems] количество `initially` отображаемых элементов
 * @param [modifier] модификатор для настройки внешнего вида и поведения компонента
 */
@Composable
fun DailyForecastComponent(
    onShowMoreClick: () -> Unit,
    onShowMoreForDateClick: (LocalDate) -> Unit,
    forecastData: DailyForecastData,
    initialVisibleItems: Int = DailyForecastConstants.INITIAL_VISIBLE_ITEMS_DEFAULT,
    @SuppressLint("ModifierParameter")
    modifier: Modifier = Modifier,
) {
    StyledCard(
        cardStyle = AppCard.noneElevationStyle(),
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = DailyForecastConstants.CONTENT_SPACING,
                alignment = Alignment.CenterVertically,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(DailyForecastConstants.CARD_PADDING),
        ) {
            ForecastHeaderComponent()
            HorizontalDividerComponent()
            ForecastDaysListComponent(
                onShowMoreForDateClick = onShowMoreForDateClick,
                days = forecastData.days,
                initialVisibleItems = initialVisibleItems,
            )
            MoreButtonComponent(onShowMoreClick = onShowMoreClick)
        }
    }
}

/**
 * Компонент заголовка для блока прогноза погоды
 *
 * Отображает иконку календаря и текст "Недельный прогноз". Используется как заголовок
 * для всего компонента прогноза по дням.
 *
 * @param [modifier] модификатор для настройки внешнего вида и поведения компонента
 */
@Composable
private fun ForecastHeaderComponent(
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = DailyForecastConstants.HEADER_SPACING,
            alignment = Alignment.Start,
        ),
        modifier = modifier.fillMaxWidth(),
    ) {
        Icon(
            imageVector = Icons.Outlined.CalendarMonth,
            tint = AppTheme.color.iconTintColor,
            contentDescription = null,
            modifier = Modifier.size(DailyForecastConstants.ICON_HEADER_SIZE),
        )

        Text(
            text = "Недельный прогноз",
            style = AppTheme.typography.titleMedium,
        )
    }
}

/**
 * Компонент списка дней с прогнозом погоды
 *
 * Отображает ограниченное количество дней прогноза с возможностью показа дополнительных
 * элементов через кнопку "Подробнее". Каждый день разделен горизонтальным разделителем.
 *
 * @param [onShowMoreForDateClick] `callback` при нажатии на конкретный день
 * @param [days] список данных по дням для отображения
 * @param [initialVisibleItems] количество `initially` отображаемых элементов
 */
@Composable
private fun ForecastDaysListComponent(
    onShowMoreForDateClick: (LocalDate) -> Unit,
    days: List<DailyForecastItemData>,
    initialVisibleItems: Int,
    modifier: Modifier = Modifier,
) {
    val visibleDays = remember(days, initialVisibleItems) {
        days.take(initialVisibleItems)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        visibleDays.fastForEach { dayData ->
            DailyForecastItemComponent(
                dayData = dayData,
                onShowMoreForDateClick = { onShowMoreForDateClick(dayData.date) },
            )

            HorizontalDividerComponent(
                verticalPaddingDp = DailyForecastConstants.CONTENT_SPACING / 2,
                thicknessDp = DailyForecastConstants.DIVIDER_THICKNESS
            )
        }
    }
}

/**
 * Компонент элемента прогноза погоды на один день
 *
 * Отображает информацию о конкретном дне: дату, иконку погоды, вероятность осадков
 * и температурный диапазон. Весь элемент является кликабельным для перехода к детальной информации.
 *
 * @param [onShowMoreForDateClick] `callback` при нажатии на элемент дня
 * @param [dayData] данные для отображения одного дня
 * @param [modifier] модификатор для настройки внешнего вида и поведения компонента
 */
@Composable
private fun DailyForecastItemComponent(
    onShowMoreForDateClick: () -> Unit,
    dayData: DailyForecastItemData,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = onShowMoreForDateClick,
                indication = null,
                interactionSource = interactionSource
            ),
    ) {
        val (dateRef, weatherIconRef, precipitationRef, tempRangeRef) = createRefs()

        DateAndDayComponent(
            date = dayData.date,
            modifier = Modifier.constrainAs(dateRef) {
                top.linkTo(anchor = parent.top)
                bottom.linkTo(anchor = parent.bottom)
                start.linkTo(anchor = parent.start)
                end.linkTo(anchor = weatherIconRef.start)
                centerVerticallyTo(other = parent)
                width = Dimension.fillToConstraints
            }
        )

        ImageComponent(
            url = dayData.weatherIconUrl,
            modifier = Modifier
                .size(DailyForecastConstants.WEATHER_ICON_SIZE)
                .constrainAs(weatherIconRef) {
                    centerHorizontallyTo(
                        other = parent,
                        bias = DailyForecastConstants.WEATHER_ICON_BIAS,
                    )
                    centerVerticallyTo(other = parent)
                }
        )

        dayData.precipitationChance?.let { precipitation ->
            PrecipitationChanceComponent(
                precipitationChance = precipitation,
                modifier = Modifier.constrainAs(precipitationRef) {
                    start.linkTo(
                        anchor = weatherIconRef.end,
                        margin = DailyForecastConstants.PRECIPITATION_SPACING,
                    )
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = parent.bottom)
                    centerVerticallyTo(other = parent)
                }
            )
        }

        TemperatureRangeComponent(
            minTemperature = dayData.minTemperature,
            maxTemperature = dayData.maxTemperature,
            temperatureUnit = dayData.temperatureUnit,
            modifier = Modifier.constrainAs(tempRangeRef) {
                end.linkTo(anchor = parent.end)
                top.linkTo(anchor = parent.top)
                bottom.linkTo(anchor = parent.bottom)
                centerVerticallyTo(other = parent)
            }
        )
    }
}

/**
 * Компонент для отображения даты и дня недели
 *
 * Отображает число с месяцем и день недели в одной строке с правильным форматированием.
 * Используется в элементах списка прогноза погоды.
 *
 * @param [date] дата для отображения и форматирования
 * @param [modifier] модификатор для настройки внешнего вида и поведения компонента
 */
@Composable
private fun DateAndDayComponent(
    date: LocalDate,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DailyForecastConstants.DATE_DAY_SPACING),
        modifier = modifier,
    ) {
        Text(
            text = DateUtils.formatShortDate(date),
            style = AppTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = DateUtils.formatDayOfWeekForForecast(date),
            style = AppTheme.typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/**
 * Компонент для отображения вероятности осадков
 *
 * Отображает процентное значение вероятности осадков с жирным начертанием.
 * Используется рядом с иконкой погодных условий.
 *
 * @param [precipitationChance] вероятность осадков в процентах
 * @param [modifier] модификатор для настройки внешнего вида и поведения компонента
 */
@Composable
private fun PrecipitationChanceComponent(
    precipitationChance: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "$precipitationChance%",
        style = AppTheme.typography.bodyMedium,
        fontWeight = FontWeight.Medium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
    )
}

/**
 * Компонент для отображения температурного диапазона
 *
 * Отображает минимальную и максимальную температуру с разделителем `/`.
 * Максимальная температура выделяется жирным начертанием.
 *
 * @param [minTemperature] минимальная температура
 * @param [maxTemperature] максимальная температура
 * @param [temperatureUnit] единица измерения температуры
 * @param [modifier] модификатор для настройки внешнего вида и поведения компонента
 */
@Composable
private fun TemperatureRangeComponent(
    minTemperature: Int,
    maxTemperature: Int,
    temperatureUnit: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = DailyForecastConstants.TEMPERATURE_SPACING,
            alignment = Alignment.CenterHorizontally,
        ),
        modifier = modifier,
    ) {
        Text(
            text = "$minTemperature$temperatureUnit",
            style = AppTheme.typography.bodyMedium,
        )

        Text(
            text = "/",
            style = AppTheme.typography.bodySmall,
            color = AppTheme.color.mainDarkColorText,
        )

        Text(
            text = "$maxTemperature$temperatureUnit",
            style = AppTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
        )
    }
}

/**
 * Компонент кнопки для показа дополнительных элементов
 *
 * Отображает кнопку "Подробнее" для загрузки и отображения дополнительных дней прогноза.
 * Используется когда количество элементов превышает `initially` отображаемое значение.
 *
 * @param [onShowMoreClick] `callback` при нажатии на кнопку
 * @param [modifier] модификатор для настройки внешнего вида и поведения компонента
 */
@Composable
private fun MoreButtonComponent(
    onShowMoreClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AppFilledButton(
        text = "Подробнее",
        onClick = onShowMoreClick,
        style = AppTheme.buttons.filled.small,
        modifier = modifier.fillMaxWidth(),
    )
}

/**
 * Константы для компонента прогноза погоды по дням
 */
private object DailyForecastConstants {
    val ICON_HEADER_SIZE = 16.dp
    val CARD_PADDING = 16.dp
    val CONTENT_SPACING = 8.dp
    val HEADER_SPACING = 8.dp
    val WEATHER_ICON_SIZE = 32.dp
    val PRECIPITATION_SPACING = 4.dp
    val DATE_DAY_SPACING = 4.dp
    val TEMPERATURE_SPACING = 4.dp
    const val WEATHER_ICON_BIAS = 0.55f
    val DIVIDER_THICKNESS = 0.2.dp
    const val INITIAL_VISIBLE_ITEMS_DEFAULT = 7
}

package space.serphantom.myweather.app.ui.compose.components.weather_metrics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import space.serphantom.myweather.app.ui.compose.extensions.cards.AppFilledCard
import space.serphantom.myweather.app.ui.compose.theme.AppTheme

/**
 * Базовый компонент карточки для отображения погодных метрик.
 * Предоставляет единообразный внешний вид для всех метрик приложения.
 * Использует [Column] для предотвращения наложения элементов при увеличенном шрифте.
 *
 * @param [title] Заголовок карточки (ровно 2 строки)
 * @param [value] Основное значение для отображения в нижней части
 * @param[description] Дополнительное краткое описание для отображения в нижней части
 * @param [modifier] Модификатор для кастомизации внешнего вида и поведения
 * @param [content] Composable контент, отображаемый в центре карточки
 */
@Composable
fun WeatherMetricCardComponent(
    title: String,
    value: String,
    description: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    AppFilledCard(
        modifier = modifier.aspectRatio(ASPECT_RATIO),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = CONTENT_PADDING.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = title,
                style = AppTheme.typography.labelMedium,
                maxLines = TITLE_MAX_LINES,
                minLines = TITLE_MIN_LINES,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
            )

            Box(
                modifier = Modifier
                    .weight(CONTENT_WEIGHT)
                    .padding(all = ICON_METRICS_PADDING.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) { content() }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = value,
                    style = AppTheme.typography.bodyMedium,
                    maxLines = VALUE_MAX_LINES,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = description,
                    style = AppTheme.typography.bodySmall,
                    maxLines = VALUE_MAX_LINES,
                    color = AppTheme.color.mainDarkColorText,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

private const val ASPECT_RATIO = 0.9f
private const val TITLE_MIN_LINES = 2
private const val TITLE_MAX_LINES = 2
private const val VALUE_MAX_LINES = 1
private const val CONTENT_WEIGHT = 1f
private const val CONTENT_PADDING = 16
private const val ICON_METRICS_PADDING = 6

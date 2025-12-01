package space.serphantom.myweather.app.ui.compose.components.weather_metrics.wind_speed_metric

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import space.serphantom.myweather.app.ui.compose.theme.AppTheme

/**
 * Компонент для отображения направления ветра с вращающейся стрелкой.
 * Поддерживает 8 основных направлений (`С`, `СВ`, `В`, `ЮВ`, `Ю`, `ЮЗ`, `З`, `СЗ`).
 *
 * @param [windDirectionDegrees] Направление ветра в градусах (`0-360`)
 * @param [modifier] Модификатор для кастомизации внешнего вида и поведения
 */
@Composable
fun WindDirectionIndicatorComponent(
    windDirectionDegrees: Float,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(CONTENT_RATIO)
                .background(
                    color = AppTheme.color.filledButtonColors.containerColor,
                    shape = CircleShape,
                )
        )

        Icon(
            imageVector = Icons.Filled.ArrowUpward,
            contentDescription = "Направление ветра",
            modifier = Modifier
                .matchParentSize()
                .rotate(windDirectionDegrees)
                .padding(ICON_PADDING.dp),
            tint = AppTheme.color.filledButtonColors.contentColor,
        )
    }
}

private const val CONTENT_RATIO = 1f
private const val ICON_PADDING = 10

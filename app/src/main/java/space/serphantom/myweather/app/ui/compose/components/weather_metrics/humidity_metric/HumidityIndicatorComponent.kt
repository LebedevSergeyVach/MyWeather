package space.serphantom.myweather.app.ui.compose.components.weather_metrics.humidity_metric

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import space.serphantom.myweather.app.ui.compose.theme.AppTheme

/**
 * Компонент для визуализации уровня влажности в виде вертикального столбца.
 * Отображает анимированное заполнение столбца в соответствии с процентом влажности.
 * Высота заполненной части адаптируется к размеру родительского контейнера.
 *
 * @param [humidity] Уровень влажности в процентах (`0-100`)
 * @param [modifier] Модификатор для кастомизации внешнего вида и поведения
 */
@Composable
fun HumidityIndicatorComponent(
    humidity: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier
            .width(HUMIDITY_BAR_WIDTH.dp)
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(HUMIDITY_BAR_CORNER_RADIUS.dp),
            ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = AppTheme.color.filledButtonColors.containerColor,
                    shape = RoundedCornerShape(HUMIDITY_BAR_CORNER_RADIUS.dp),
                ),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(humidity / MAX_HUMIDITY_PERCENTAGE)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            AppTheme.color.filledButtonColors.contentColor
                                .copy(alpha = ALPHA_BRUSH_COLOR),
                            AppTheme.color.filledButtonColors.contentColor,
                        )
                    ),
                    shape = RoundedCornerShape(HUMIDITY_BAR_CORNER_RADIUS.dp),
                ),
        )
    }
}

private const val MAX_HUMIDITY_PERCENTAGE = 100f
private const val HUMIDITY_BAR_CORNER_RADIUS = 16
private const val HUMIDITY_BAR_WIDTH = 30
private const val ALPHA_BRUSH_COLOR = 0.5f

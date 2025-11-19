package space.serphantom.myweather.app.ui.compose.extensions.dividers

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import space.serphantom.myweather.app.ui.compose.theme.AppTheme

/**
 * Компонент горизонтальной разделительной линии.
 * Используется для визуального разделения заголовка и контента.
 *
 * @param [verticalPaddingDp] Вертикальные отступы сверху и снизу разделителя
 * @param [horizontalPaddingDp] Горизонтальные отступы слева и справа разделителя
 * @param [thicknessDp] Толщина разделительной линии
 * @param [color] Цвет разделительной линии
 * @param [modifier] Базовый модификатор для настройки внешнего вида компонента
 */
@Composable
fun HorizontalDividerComponent(
    verticalPaddingDp: Dp = 0.dp,
    horizontalPaddingDp: Dp = 0.dp,
    thicknessDp: Dp = 0.5.dp,
    color: Color = AppTheme.color.iconTintColor,
    @SuppressLint("ModifierParameter")
    modifier: Modifier = Modifier,
) {
    HorizontalDivider(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = verticalPaddingDp)
            .padding(horizontal = horizontalPaddingDp),
        thickness = thicknessDp,
        color = color,
    )
}

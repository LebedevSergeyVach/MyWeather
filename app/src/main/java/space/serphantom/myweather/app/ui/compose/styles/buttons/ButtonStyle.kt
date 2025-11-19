package space.serphantom.myweather.app.ui.compose.styles.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Полный стиль кнопки, содержащий все параметры для отрисовки.
 * Определяет внешний вид кнопки: форму, цвета, текст, размеры и эффекты.
 *
 * @property [shape] Форма кнопки (скругление углов)
 * @property [colors] Цветовая схема кнопки для различных состояний
 * @property [textStyle] Стиль текста кнопки
 * @property [minHeight] Минимальная высота кнопки
 * @property [iconSize] Размер иконки в кнопке
 * @property [contentPadding] Внутренние отступы содержимого
 * @property [elevation] Настройки тени и `elevation` кнопки
 */
@Immutable
data class ButtonStyle(
    val shape: Shape,
    val colors: ButtonColors,
    val textStyle: TextStyle,
    val minHeight: Dp,
    val iconSize: Dp,
    val contentPadding: PaddingValues,
    val elevation: ButtonElevation?,
) {

    companion object {

        /**
         * Неопределенный стиль кнопки, используемый как fallback значение.
         * Все свойства имеют значения [Dp.Unspecified], [Color.Unspecified] или аналоги.
         */
        @Stable
        val Unspecified = ButtonStyle(
            shape = RoundedCornerShape(size = Dp.Unspecified),
            colors = ButtonColors.Unspecified,
            textStyle = TextStyle.Default,
            minHeight = Dp.Unspecified,
            iconSize = Dp.Unspecified,
            contentPadding = PaddingValues(0.dp),
            elevation = null
        )
    }
}

package space.serphantom.myweather.app.ui.compose.styles.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Полный стиль кнопки, содержащий все параметры для отрисовки.
 * Определяет внешний вид кнопки: форму, цвета, текст, размеры и эффекты.
 *
 * @property [shape] Форма кнопки (скругление углов) - [Shape]
 * @property [colors] Цветовая схема кнопки для различных состояний - [ButtonColors]
 * @property [textStyle] Стиль текста кнопки - [TextStyle]
 * @property [minHeight] Минимальная высота кнопки - [Dp]
 * @property [iconSize] Размер иконки в кнопке- [Dp]
 * @property [contentPadding] Внутренние отступы содержимого - [PaddingValues]
 * @property [elevation] Настройки тени и `elevation` кнопки - [ButtonElevation]
 * @property [hapticFeedbackType] Тип тактильного отклика при нажатии - [HapticFeedbackType]
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
    val hapticFeedbackType: HapticFeedbackType?,
) {

    companion object {

        /**
         * Неопределенный стиль кнопки, используемый как fallback значение.
         * Все свойства имеют значения [Dp.Unspecified], [Unspecified] или аналоги.
         */
        @Stable
        val Unspecified = ButtonStyle(
            shape = RoundedCornerShape(size = Dp.Unspecified),
            colors = ButtonColors.Unspecified,
            textStyle = TextStyle.Default,
            minHeight = Dp.Unspecified,
            iconSize = Dp.Unspecified,
            contentPadding = PaddingValues(0.dp),
            elevation = null,
            hapticFeedbackType = null,
        )
    }
}

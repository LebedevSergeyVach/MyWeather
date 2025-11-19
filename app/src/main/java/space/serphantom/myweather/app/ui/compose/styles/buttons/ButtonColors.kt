package space.serphantom.myweather.app.ui.compose.styles.buttons

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

/**
 * Цветовая схема кнопки для различных состояний.
 * Определяет цвета для нормального и `disabled` состояний.
 *
 * @property [containerColor] Цвет фона кнопки в активном состоянии
 * @property [contentColor] Цвет содержимого (текст, иконки) в активном состоянии
 * @property [disabledContainerColor] Цвет фона кнопки в отключенном состоянии
 * @property [disabledContentColor] Цвет содержимого в отключенном состоянии
 */
@Immutable
data class ButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
) {

    companion object {

        /**
         * Неопределенная цветовая схема кнопки.
         * Все цвета установлены в [Color.Unspecified].
         */
        @Stable
        val Unspecified = ButtonColors(
            containerColor = Color.Unspecified,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified,
        )
    }
}

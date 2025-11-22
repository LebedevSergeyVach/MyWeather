package space.serphantom.myweather.app.ui.compose.styles.cards

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color


/**
 * Цветовая схема карточки для различных состояний.
 *
 * @property [containerColor] Цвет фона карточки в активном состоянии
 * @property [contentColor] Цвет содержимого в активном состоянии
 * @property [disabledContainerColor] Цвет фона карточки в отключенном состоянии
 * @property [disabledContentColor] Цвет содержимого в отключенном состоянии
 */
@Immutable
data class CardColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val borderStrokeColor: Color,
) {

    companion object {

        /**
         * Неопределенная цветовая схема карточки.
         * Все цвета установлены в [Color.Unspecified].
         */
        @Stable
        val Unspecified = CardColors(
            containerColor = Color.Unspecified,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified,
            borderStrokeColor = Color.Unspecified,
        )
    }
}

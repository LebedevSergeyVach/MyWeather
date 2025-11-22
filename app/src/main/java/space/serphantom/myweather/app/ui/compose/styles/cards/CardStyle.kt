package space.serphantom.myweather.app.ui.compose.styles.cards

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * `Data class`, содержащий параметры стиля для карточных компонентов.
 *
 * Полный стиль карточки, содержащий все параметры для отрисовки.
 * Определяет внешний вид карточки: форму, цвета, тени и отступы.
 *
 * @property [shape] Форма карточки (скругление углов) - [Shape]
 * @property [elevation] Настройки тени и elevation карточки - [CardElevation]
 * @property [colors] Цветовая схема карточки для различных состояний - [CardColors]
 * @property [contentPadding] Внутренние отступы содержимого - [PaddingValues]
 */
@Immutable
data class CardStyle(
    val shape: Shape,
    val elevation: CardElevation?,
    val colors: CardColors,
    val contentPadding: PaddingValues,
) {

    companion object {

        /**
         * Неопределенный стиль карточки, используемый как `fallback` значение.
         */
        @Stable
        val Unspecified = CardStyle(
            shape = RoundedCornerShape(size = Dp.Unspecified),
            elevation = null,
            colors = CardColors.Unspecified,
            contentPadding = PaddingValues(0.dp)
        )
    }
}

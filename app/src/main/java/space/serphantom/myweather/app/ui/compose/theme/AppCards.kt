package space.serphantom.myweather.app.ui.compose.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp
import space.serphantom.myweather.app.ui.compose.styles.cards.CardStyle

/**
 * Система карточек приложения, содержащая стили для различных типов карточек.
 *
 * @property [filled] Стили для заполненных карточек
 * @property [outlined] Стили для контурных карточек
 */
@Immutable
data class AppCards(
    val filled: CardStyle,
    val outlined: CardStyle,
)

/**
 * [CompositionLocal] для предоставления системы карточек `throughout` приложения.
 * Использует [CardStyle.Unspecified] как `fallback` значения.
 */
val LocalAppCards = staticCompositionLocalOf {
    AppCards(
        filled = CardStyle.Unspecified,
        outlined = CardStyle.Unspecified,
    )
}

/**
 * Создает и возвращает полную систему карточек приложения.
 *
 * @param [appColor] Цветовая схема приложения для получения цветов карточек
 * @return Настроенный объект [AppCards] со всеми стилями карточек
 */
@Composable
fun createAppCards(appColor: AppColor): AppCards {
    val shape = RoundedCornerShape(16.dp)
    val paddingValues = PaddingValues(16.dp)

    val filledColors = appColor.cardColors
    val outlinedColors = appColor.cardColors

    val elevation = CardDefaults.cardElevation(0.dp)

    return AppCards(
        filled = CardStyle(
            shape = shape,
            elevation = elevation,
            colors = filledColors,
            contentPadding = paddingValues,
        ),
        outlined = CardStyle(
            shape = shape,
            elevation = elevation,
            colors = outlinedColors,
            contentPadding = paddingValues,
        ),
    )
}

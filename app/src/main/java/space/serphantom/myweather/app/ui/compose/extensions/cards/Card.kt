package space.serphantom.myweather.app.ui.compose.extensions.cards

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Composable-функция для создания карточки с предопределенным стилем.
 * Упрощает использование системы [AppCard] в компонентах.
 *
 * @param [cardStyle] Стиль карточки из системы [AppCard]
 * @param [modifier] Базовый модификатор для настройки внешнего вида
 * @param [content] Контент, отображаемый внутри карточки
 *
 * @see AppCard.CardStyle
 */
@Composable
fun StyledCard(
    cardStyle: AppCard.CardStyle,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        shape = cardStyle.shape,
        elevation = cardStyle.elevation,
        colors = cardStyle.toCardColors(),
        modifier = modifier,
        content = content,
    )
}

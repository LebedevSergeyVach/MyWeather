package space.serphantom.myweather.app.ui.compose.extensions.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import space.serphantom.myweather.app.ui.compose.styles.cards.CardStyle
import space.serphantom.myweather.app.ui.compose.theme.AppTheme

/**
 * Базовая карточка приложения с поддержкой различных стилей.
 * Использует стили из [AppTheme.cards] для согласованного внешнего вида.
 *
 * @param [onClick] Опциональный колбэк, вызываемый при нажатии на карточку
 * @param [modifier] Модификатор для кастомизации внешнего вида и поведения карточки
 * @param [enabled] Флаг, указывающий на активность карточки
 * @param [style] Стиль карточки из темы приложения. По умолчанию используется средний размер elevated карточки
 * @param [border] Параметры обводки кнопки - [BorderStroke]
 * @param [indication] Эффект при нажатии. По умолчанию используется `ripple` или отключается если `onClick == null`
 * @param [interactionSource] [MutableInteractionSource] для отслеживания состояний взаимодействия
 * @param [content] [Composable] контент карточки
 */
@Composable
fun AppCard(
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: CardStyle = AppTheme.cards.filled,
    border: BorderStroke? = null,
    indication: Indication? = onClick?.let { LocalIndication.current },
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable ColumnScope.() -> Unit,
) {
    val cardColors = CardDefaults.cardColors(
        containerColor = style.colors.containerColor,
        contentColor = style.colors.contentColor,
        disabledContainerColor = style.colors.disabledContainerColor,
        disabledContentColor = style.colors.disabledContentColor,
    )

    val clickModifier = onClick?.let {
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = indication,
            enabled = enabled,
            onClick = onClick,
        )
    } ?: Modifier

    Card(
        modifier = modifier.then(other = clickModifier),
        shape = style.shape,
        elevation = style.elevation ?: CardDefaults.cardElevation(),
        colors = cardColors,
        content = content,
        border = border,
    )
}

/**
 * Упрощенная версия filled карточки.
 * Рекомендуется для карточек без тени с заполненным фоном.
 *
 * @param [onClick] Опциональный колбэк, вызываемый при нажатии на карточку
 * @param [modifier] Модификатор для кастомизации внешнего вида и поведения карточки
 * @param [enabled] Флаг, указывающий на активность карточки
 * @param [content] Composable контент карточки
 */
@Composable
fun AppFilledCard(
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    AppCard(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = AppTheme.cards.filled,
        content = content,
    )
}

/**
 * Упрощенная версия outlined карточки.
 * Рекомендуется для карточек с обводкой.
 *
 * @param [onClick] Опциональный колбэк, вызываемый при нажатии на карточку
 * @param [modifier] Модификатор для кастомизации внешнего вида и поведения карточки
 * @param [enabled] Флаг, указывающий на активность карточки
 * @param [borderStrokeWidth] Ширина обводки в [Dp]
 * @param [content] Composable контент карточки
 */
@Composable
fun AppOutlinedCard(
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    borderStrokeWidth: Dp = 3.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    AppCard(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = AppTheme.cards.outlined,
        content = content,
        border = BorderStroke(
            width = borderStrokeWidth,
            color = AppTheme.cards.outlined.colors.borderStrokeColor,
        ),
    )
}

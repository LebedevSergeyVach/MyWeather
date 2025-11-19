package space.serphantom.myweather.app.ui.compose.extensions.buttons

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import space.serphantom.myweather.app.ui.compose.data.constans.buttons.IconPosition
import space.serphantom.myweather.app.ui.compose.styles.buttons.ButtonStyle
import space.serphantom.myweather.app.ui.compose.theme.AppTheme

/**
 * Базовая заполненная кнопка приложения с поддержкой кастомного контента.
 * Использует стили из [AppTheme.buttons] для согласованного внешнего вида.
 *
 * @param [onClick] Колбэк, вызываемый при нажатии на кнопку
 * @param [modifier] Модификатор для кастомизации внешнего вида и поведения кнопки
 * @param [enabled] Флаг, указывающий на активность кнопки (`true` - активна, `false` - отключена)
 * @param [style] Стиль кнопки из темы приложения. По умолчанию используется средний размер `filled` кнопки
 * @param [content] Composable контент кнопки, обычно содержащий текст и/или иконки.
 *                Используется [RowScope] для горизонтального расположения элементов
 *
 * @see AppTheme
 * @see ButtonStyle
 * @see RowScope
 */
@Composable
fun AppFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ButtonStyle = AppTheme.buttons.filled.medium,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = style.minHeight),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = style.colors.containerColor,
            contentColor = style.colors.contentColor,
            disabledContainerColor = style.colors.disabledContainerColor,
            disabledContentColor = style.colors.disabledContentColor,
        ),
        shape = style.shape,
        contentPadding = style.contentPadding,
        elevation = style.elevation ?: ButtonDefaults.buttonElevation(),
        content = content,
    )
}

/**
 * Упрощенная версия заполненной кнопки только с текстом.
 * Рекомендуется для простых кнопок, не требующих иконок или кастомного контента.
 *
 * @param [text] Текст, отображаемый на кнопке
 * @param [onClick] Колбэк, вызываемый при нажатии на кнопку
 * @param [modifier] Модификатор для кастомизации внешнего вида и поведения кнопки
 * @param [enabled] Флаг, указывающий на активность кнопки
 * @param [style] Стиль кнопки из темы приложения. По умолчанию используется средний размер
 *
 * @see AppFilledButton
 */
@Composable
fun AppFilledButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ButtonStyle = AppTheme.buttons.filled.medium,
) {
    AppFilledButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = style
    ) {
        Text(
            text = text,
            style = style.textStyle,
        )
    }
}

/**
 * Заполненная кнопка с текстом и иконкой.
 * Поддерживает различные позиции иконки относительно текста.
 *
 * @param [text] Текст, отображаемый на кнопке
 * @param [icon] Иконка, отображаемая на кнопке в виде [ImageVector]
 * @param [onClick] Колбэк, вызываемый при нажатии на кнопку
 * @param [modifier] Модификатор для кастомизации внешнего вида и поведения кнопки
 * @param [enabled] Флаг, указывающий на активность кнопки
 * @param [style] Стиль кнопки из темы приложения. По умолчанию используется средний размер
 * @param [iconPosition] Позиция иконки относительно текста. По умолчанию - в начале (слева от текста)
 *
 * @see IconPosition
 * @see ImageVector
 */
@Composable
fun AppFilledButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ButtonStyle = AppTheme.buttons.filled.medium,
    iconPosition: IconPosition = IconPosition.Start,
) {
    AppFilledButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = style,
    ) {
        when (iconPosition) {
            IconPosition.Start -> {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(style.iconSize)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = text, style = style.textStyle)
            }

            IconPosition.End -> {
                Text(text = text, style = style.textStyle)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(style.iconSize)
                )
            }
        }
    }
}


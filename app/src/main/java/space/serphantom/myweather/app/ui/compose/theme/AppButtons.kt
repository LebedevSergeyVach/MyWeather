package space.serphantom.myweather.app.ui.compose.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp
import space.serphantom.myweather.app.ui.compose.styles.buttons.ButtonStyle

/**
 * Система кнопок приложения, содержащая стили для различных типов кнопок.
 *
 * @property [filled] Стили для `filled` кнопок (заполненных)
 * @property [outlined] Стили для `outlined` кнопок (контурных)
 * @property [text] Стили для `text` кнопок (текстовых)
 * @property [switch] Стили для `switch` кнопок (переключателей)
 */
@Immutable
data class AppButtons(
    val filled: AppFilledButtons,
    val outlined: AppFilledButtons,
    val text: AppFilledButtons,
    val switch: AppFilledButtons,
)

/**
 * Стили для filled кнопок различных размеров.
 *
 * @property [extraSmall] Стиль для очень маленьких кнопок
 * @property [small] Стиль для маленьких кнопок
 * @property [medium] Стиль для средних кнопок (используется по умолчанию)
 * @property [large] Стиль для больших кнопок
 */
@Immutable
data class AppFilledButtons(
    val extraSmall: ButtonStyle,
    val small: ButtonStyle,
    val medium: ButtonStyle,
    val large: ButtonStyle,
)

/**
 * [CompositionLocal] для предоставления системы кнопок `throughout` приложения.
 * Использует [ButtonStyle.Unspecified] как `fallback` значения.
 */
val LocalAppButtons = staticCompositionLocalOf {
    AppButtons(
        filled = AppFilledButtons(
            extraSmall = ButtonStyle.Unspecified,
            small = ButtonStyle.Unspecified,
            medium = ButtonStyle.Unspecified,
            large = ButtonStyle.Unspecified,
        ),
        outlined = AppFilledButtons(
            extraSmall = ButtonStyle.Unspecified,
            small = ButtonStyle.Unspecified,
            medium = ButtonStyle.Unspecified,
            large = ButtonStyle.Unspecified,
        ),
        text = AppFilledButtons(
            extraSmall = ButtonStyle.Unspecified,
            small = ButtonStyle.Unspecified,
            medium = ButtonStyle.Unspecified,
            large = ButtonStyle.Unspecified,
        ),
        switch = AppFilledButtons(
            extraSmall = ButtonStyle.Unspecified,
            small = ButtonStyle.Unspecified,
            medium = ButtonStyle.Unspecified,
            large = ButtonStyle.Unspecified,
        )
    )
}

/**
 * Создает и возвращает полную систему кнопок приложения.
 *
 * @param [appColor] Цветовая схема приложения для получения цветов кнопок
 * @param [appTypography] Типографическая система приложения для текстовых стилей
 *
 * @return Настроенный объект [AppButtons] со всеми стилями кнопок
 *
 * @see AppButtons
 * @see AppColor
 * @see AppTypography
 */
@Composable
fun createAppButtons(
    appColor: AppColor,
    appTypography: AppTypography,
): AppButtons {
    val filledButtonColors = appColor.filledButtonColors

    val buttonShape = RoundedCornerShape(16.dp)

    val buttonElevation = ButtonDefaults.buttonElevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        disabledElevation = 0.dp,
        focusedElevation = 0.dp,
        hoveredElevation = 0.dp,
    )

    val filledButtons = AppFilledButtons(
        extraSmall = ButtonStyle(
            shape = buttonShape,
            colors = filledButtonColors,
            textStyle = appTypography.labelSmall,
            minHeight = 24.dp,
            iconSize = 16.dp,
            contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
            elevation = buttonElevation,
        ),
        small = ButtonStyle(
            shape = buttonShape,
            colors = filledButtonColors,
            textStyle = appTypography.labelMedium,
            minHeight = 36.dp,
            iconSize = 24.dp,
            contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
            elevation = buttonElevation,
        ),
        medium = ButtonStyle(
            shape = buttonShape,
            colors = filledButtonColors,
            textStyle = appTypography.labelLarge,
            minHeight = 44.dp,
            iconSize = 36.dp,
            contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
            elevation = buttonElevation,
        ),
        large = ButtonStyle(
            shape = buttonShape,
            colors = filledButtonColors,
            textStyle = appTypography.labelLarge,
            minHeight = 52.dp,
            iconSize = 44.dp,
            contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
            elevation = buttonElevation,
        )
    )

    return AppButtons(
        filled = filledButtons,
        outlined = filledButtons,
        text = filledButtons,
        switch = filledButtons,
    )
}

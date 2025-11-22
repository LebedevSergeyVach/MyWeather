package space.serphantom.myweather.app.ui.compose.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.CompositionLocal
import space.serphantom.myweather.app.ui.compose.styles.buttons.ButtonColors
import space.serphantom.myweather.app.ui.compose.styles.cards.CardColors

/**
 * Неизменяемый `data class`, представляющий цветовую схему приложения.
 * Содержит основные цвета, используемые в различных компонентах.
 *
 * @property [appBarColor] Основной цвет для фона `AppBar`
 * @property [backgroundColor] Основной цвет фона приложения
 * @property [iconTintColor] Осоновой цвет заливки иконок
 * @property [filledButtonColors] Основной цвет для `filled` кнопок
 * @property [mainDarkColorText] Цвет для текста дисклеймеров и дополнительной информации
 * (выделение более темным цвет по сравнению с основным)
 */
@Immutable
data class AppColor(
    val appBarColor: Color,
    val backgroundColor: Color,
    val iconTintColor: Color,
    val filledButtonColors: ButtonColors,
    val cardColors: CardColors,
    val mainDarkColorText: Color,
)

/**
 * [CompositionLocal] для предоставления цветовой схемы throughout приложения.
 * Использует [Color.Unspecified] как `fallback` значения.
 */
val LocalAppColor = staticCompositionLocalOf {
    AppColor(
        appBarColor = Color.Unspecified,
        backgroundColor = Color.Unspecified,
        filledButtonColors = ButtonColors.Unspecified,
        cardColors = CardColors.Unspecified,
        iconTintColor = Color.Unspecified,
        mainDarkColorText = Color.Unspecified,
    )
}

/**
 * Создает и возвращает объект [AppColor] для темной темы приложения.
 *
 * @param [dynamicColorScheme] Динамическая цветовая палитра `Android OS`, если есть, используется
 * для построения динамических цветов, если `null` - то используется цветовая палитра приложения
 *
 * @return Настроенный объект [AppColor] с цветами для темной темы
 *
 * @see AppColor
 */
@Composable
@ReadOnlyComposable
fun createAppColorSystemDark(dynamicColorScheme: ColorScheme?): AppColor {
    val colorScheme = dynamicColorScheme ?: darkColorScheme()

    // Buttons
    val buttonContainerColor = colorScheme.secondary
    val buttonContentColor = colorScheme.onSecondary
    val buttonDisabledContainerColor = colorScheme.onSurface.copy(alpha = 0.12f)
    val buttonDisabledContentColor = colorScheme.onSurface.copy(alpha = 0.12f)

    // Cards
    val cardContainerColor = colorScheme.surfaceContainerLow
    val cardContentColor = colorScheme.onSurface
    val cardDisabledContainerColor = colorScheme.onSurface.copy(alpha = 0.12f)
    val cardDisabledContentColor = colorScheme.onSurface.copy(alpha = 0.38f)
    val cardBorderStrokeColor = colorScheme.outline

    return AppColor(
        appBarColor = Colors.black.copy(alpha = 0.7f),
        backgroundColor = Colors.black,
        iconTintColor = colorScheme.onSurface,
        filledButtonColors = ButtonColors(
            containerColor = buttonContainerColor,
            contentColor = buttonContentColor,
            disabledContainerColor = buttonDisabledContainerColor,
            disabledContentColor = buttonDisabledContentColor,
        ),
        cardColors = CardColors(
            containerColor = cardContainerColor,
            contentColor = cardContentColor,
            disabledContainerColor = cardDisabledContainerColor,
            disabledContentColor = cardDisabledContentColor,
            borderStrokeColor = cardBorderStrokeColor,
        ),
        mainDarkColorText = colorScheme.onSurfaceVariant,
    )
}

/**
 * Создает и возвращает объект [AppColor] для светлой темы приложения.
 *
 * @param [dynamicColorScheme] Динамическая цветовая палитра `Android OS`, если есть, используется
 * для построения динамических цветов, если `null` - то используется цветовая палитра приложения
 *
 * @return Настроенный объект [AppColor] с цветами для светлой темы
 *
 * @see AppColor
 */
@Composable
@ReadOnlyComposable
fun createAppColorSystemLight(dynamicColorScheme: ColorScheme?): AppColor {
    val colorScheme = dynamicColorScheme ?: lightColorScheme()

    // Buttons
    val buttonContainerColor = colorScheme.primary
    val buttonContentColor = colorScheme.onPrimary
    val buttonDisabledContainerColor = colorScheme.onSurface.copy(alpha = 0.12f)
    val buttonDisabledContentColor = colorScheme.onSurface.copy(alpha = 0.12f)

    // Cards
    val cardContainerColor = colorScheme.surfaceContainerLow
    val cardContentColor = colorScheme.onSurface
    val cardDisabledContainerColor = colorScheme.onSurface.copy(alpha = 0.12f)
    val cardDisabledContentColor = colorScheme.onSurface.copy(alpha = 0.38f)
    val cardBorderStrokeColor = colorScheme.outline

    return AppColor(
        appBarColor = Colors.white.copy(alpha = 0.7f),
        backgroundColor = Colors.white,
        iconTintColor = colorScheme.onSurface,
        filledButtonColors = ButtonColors(
            containerColor = buttonContainerColor,
            contentColor = buttonContentColor,
            disabledContainerColor = buttonDisabledContainerColor,
            disabledContentColor = buttonDisabledContentColor,
        ),
        cardColors = CardColors(
            containerColor = cardContainerColor,
            contentColor = cardContentColor,
            disabledContainerColor = cardDisabledContainerColor,
            disabledContentColor = cardDisabledContentColor,
            borderStrokeColor = cardBorderStrokeColor,
        ),
        mainDarkColorText = colorScheme.onSurfaceVariant,
    )
}

/**
 * Приватный `data class`, содержащий базовые цвета, используемые в приложении.
 * Служит вспомогательным классом для создания цветовых схем.
 *
 * @property [white] Белый цвет
 * @property [black] Черный цвет
 * @property [gray] Серый цвет
 * @property [lightGray] Светлый серый
 * @property [darkGray] Темно-серый цвет
 */
@Immutable
private object Colors {
    val white: Color = Color.White
    val black: Color = Color.Black
    val gray: Color = Color.Gray
    val lightGray: Color = Color.LightGray
    val darkGray: Color = Color.DarkGray
}

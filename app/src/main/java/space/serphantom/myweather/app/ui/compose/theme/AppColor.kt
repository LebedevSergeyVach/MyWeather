package space.serphantom.myweather.app.ui.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.CompositionLocal

/**
 * Неизменяемый `data class`, представляющий цветовую схему приложения.
 * Содержит основные цвета, используемые в различных компонентах.
 *
 * @property [backgroundColor] Основной цвет фона приложения
 * @property [iconsTintColor] Осоновой цвет заливки иконок
 * @property [titleDisclaimerColor] Цвет для текста дисклеймеров и дополнительной информации
 */
@Immutable
data class AppColor(
    val backgroundColor: Color,
    val iconsTintColor: Color,
    val titleDisclaimerColor: Color,
)

/**
 * [CompositionLocal] для предоставления цветовой схемы throughout приложения.
 * Использует [Color.Unspecified] как `fallback` значения.
 */
val LocalAppColor = staticCompositionLocalOf {
    AppColor(
        backgroundColor = Color.Unspecified,
        iconsTintColor = Color.Unspecified,
        titleDisclaimerColor = Color.Unspecified,
    )
}

/**
 * Создает и возвращает объект [AppColor] для темной темы приложения.
 *
 * @return Настроенный объект [AppColor] с цветами для темной темы
 *
 * @see AppColor
 */
@Composable
@ReadOnlyComposable
fun createAppColorSystemDark(): AppColor {
    return AppColor(
        backgroundColor = Colors.black,
        iconsTintColor = Colors.white,
        titleDisclaimerColor = Colors.gray,
    )
}

/**
 * Создает и возвращает объект [AppColor] для светлой темы приложения.
 *
 * @return Настроенный объект [AppColor] с цветами для светлой темы
 *
 * @see AppColor
 */
@Composable
@ReadOnlyComposable
fun createAppColorSystemLight(): AppColor {
    return AppColor(
        backgroundColor = Colors.white,
        iconsTintColor = Colors.black,
        titleDisclaimerColor = Colors.darkGray,
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


package space.serphantom.myweather.app.ui.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.CompositionLocal

/**
 * Основная тема приложения, объединяющая цветовую схему и типографику.
 * Автоматически определяет системную тему и применяет соответствующие настройки.
 *
 * @param darkTheme Флаг, указывающий на использование темной темы (по умолчанию определяется системой)
 * @param content Composable контент, к которому применяется тема
 *
 * @see AppColor
 * @see AppTypography
 */
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val appColor = if (darkTheme) createAppColorSystemDark() else createAppColorSystemLight()
    val appTypography = createAppTypography(appColor)

    val darkColorScheme = darkColorScheme(background = appColor.backgroundColor)
    val lightColorScheme = lightColorScheme(background = appColor.backgroundColor)

    val colorScheme = when {
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    MaterialTheme(colorScheme = colorScheme) {
        CompositionLocalProvider(
            LocalAppColor provides appColor,
            LocalAppTypography provides appTypography,
            content = content,
        )
    }
}

/**
 * Объект-компаньон для доступа к текущим настройкам темы приложения.
 * Предоставляет удобный доступ к цветам и типографике через [CompositionLocal].
 */
object AppTheme {

    /**
     * Возвращает текущую цветовую схему приложения.
     *
     * @return Текущий объект [AppColor]
     *
     * @see AppColor
     */
    val color: AppColor
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColor.current

    /**
     * Возвращает текущую типографическую систему приложения.
     *
     * @return Текущий объект [AppTypography]
     *
     * @see AppTypography
     */
    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current
}

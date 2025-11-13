package space.serphantom.myweather.app.ui.compose.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.runtime.CompositionLocal

/**
 * Неизменяемый `data class`, представляющий типографическую систему приложения.
 * Содержит набор текстовых стилей для различных типов контента.
 *
 * @property [displayLarge] Стиль для самого крупного заголовка `Display Large`
 * @property [displayMedium] Стиль для крупного заголовка `Display Medium`
 * @property [displaySmall] Стиль для среднего заголовка `Display Small`
 * @property [headlineLarge] Стиль для крупного заголовка раздела `Headline Large`
 * @property [headlineMedium] Стиль для среднего заголовка раздела `Headline Medium`
 * @property [headlineSmall] Стиль для мелкого заголовка раздела `Headline Small`
 * @property [titleLarge] Стиль для крупного заголовка `Title Large`
 * @property [titleMedium] Стиль для среднего заголовка `Title Medium`
 * @property [titleSmall] Стиль для мелкого заголовка `Title Small`
 * @property [bodyLarge] Стиль для крупного основного текста `Body Large`
 * @property [bodyMedium] Стиль для среднего основного текста `Body Medium`
 * @property [bodySmall] Стиль для мелкого основного текста `Body Small`
 * @property [labelLarge] Стиль для крупных меток `Label Large`
 * @property [labelMedium] Стиль для средних меток `Label Medium`
 * @property [labelSmall] Стиль для мелких меток `Label Small`
 * @property [titleDisclaimer] Специальный стиль для дисклеймеров и дополнительной информации
 */
@Immutable
data class AppTypography(

    // Display
    val displayLarge: TextStyle,
    val displayMedium: TextStyle,
    val displaySmall: TextStyle,

    // Headline
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,

    // Title
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,

    // Body
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,

    // Label
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle,

    // Custom title
    val titleDisclaimer: TextStyle,
)

/**
 * [CompositionLocal] для предоставления типографики `throughout` приложения.
 * Использует значения по умолчанию [Material Theme][MaterialTheme.typography] как `fallback`.
 */
val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        displayLarge = TextStyle.Default,
        displayMedium = TextStyle.Default,
        displaySmall = TextStyle.Default,
        headlineLarge = TextStyle.Default,
        headlineMedium = TextStyle.Default,
        headlineSmall = TextStyle.Default,
        titleLarge = TextStyle.Default,
        titleMedium = TextStyle.Default,
        titleSmall = TextStyle.Default,
        bodyLarge = TextStyle.Default,
        bodyMedium = TextStyle.Default,
        bodySmall = TextStyle.Default,
        labelLarge = TextStyle.Default,
        labelMedium = TextStyle.Default,
        labelSmall = TextStyle.Default,
        titleDisclaimer = TextStyle.Default,
    )
}

/**
 * Создает и возвращает объект [AppTypography] с настройками шрифтов и цветов.
 *
 * @param appColor Цветовая схема приложения для настройки цветов текста
 * @return Настроенный объект [AppTypography] с примененными шрифтами и цветами
 *
 * @see AppTypography
 * @see AppColor
 */
@Composable
@ReadOnlyComposable
fun createAppTypography(appColor: AppColor): AppTypography {
    val nunitoFamilyLarge = FontFamily.Default
    val nunitoFamilyMedium = FontFamily.Default
    val nunitoFamilySmall = FontFamily.Default

    val defaultTypography = MaterialTheme.typography

    return AppTypography(
        displayLarge = defaultTypography.displayLarge.copy(fontFamily = nunitoFamilyLarge),
        displayMedium = defaultTypography.displayMedium.copy(fontFamily = nunitoFamilyMedium),
        displaySmall = defaultTypography.displaySmall.copy(fontFamily = nunitoFamilySmall),

        headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = nunitoFamilyLarge),
        headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = nunitoFamilyMedium),
        headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = nunitoFamilySmall),

        titleLarge = defaultTypography.titleLarge.copy(fontFamily = nunitoFamilyLarge),
        titleMedium = defaultTypography.titleMedium.copy(fontFamily = nunitoFamilyMedium),
        titleSmall = defaultTypography.titleSmall.copy(fontFamily = nunitoFamilySmall),

        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = nunitoFamilyLarge),
        bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = nunitoFamilyMedium),
        bodySmall = defaultTypography.bodySmall.copy(fontFamily = nunitoFamilySmall),

        labelLarge = defaultTypography.labelLarge.copy(fontFamily = nunitoFamilyLarge),
        labelMedium = defaultTypography.labelMedium.copy(fontFamily = nunitoFamilyMedium),
        labelSmall = defaultTypography.labelSmall.copy(fontFamily = nunitoFamilySmall),

        titleDisclaimer = defaultTypography.titleSmall.copy(
            fontFamily = nunitoFamilySmall,
            color = appColor.titleDisclaimerColor,
        ),
    )
}

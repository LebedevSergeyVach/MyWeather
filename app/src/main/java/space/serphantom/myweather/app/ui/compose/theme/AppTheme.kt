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
     * ## Display Styles (Крупные заголовки)
     *
     * **`displayLarge`** - самый крупный текст
     * - Размер: 57sp, межстрочный: 64dp, межбуквенный: -0.25dp
     * - **Использование**: Главный заголовок экрана, герой-секции
     *
     * **`displayMedium`** - крупный заголовок
     * - Размер: 45sp, межстрочный: 52dp
     * - **Использование**: Крупные разделы, баннеры
     *
     * **`displaySmall`** - средний заголовок
     * - Размер: 36sp, межстрочный: 44dp
     * - **Использование**: Заголовки важных блоков
     *
     * ## Headline Styles (Заголовки среднего уровня)
     *
     * **`headlineLarge`** - крупный заголовок раздела
     * - Размер: 32sp, межстрочный: 40dp
     * - **Использование**: Заголовки основных разделов
     *
     * **`headlineMedium`** - стандартный заголовок
     * - Размер: 28sp, межстрочный: 36dp
     * - **Использование**: Заголовки карточек, секций
     *
     * **`headlineSmall`** - мелкий заголовок
     * - Размер: 24sp, межстрочный: 32dp
     * - **Использование**: Подзаголовки, заголовки списков
     *
     * ## Title Styles (Заголовки элементов)
     *
     * **`titleLarge`** - крупный заголовок элемента
     * - Размер: 22sp, межстрочный: 28dp
     * - **Использование**: Названия товаров, заголовки в диалогах
     *
     * **`titleMedium`** - стандартный заголовок элемента
     * - Размер: 16sp, межстрочный: 24dp, межбуквенный: 0.15dp
     * - **Использование**: Названия кнопок, пункты навигации
     *
     * **`titleSmall`** - мелкий заголовок элемента
     * - Размер: 14sp, межстрочный: 20dp, межбуквенный: 0.1dp
     * - **Использование**: Мелкие заголовки, метки
     *
     * ## Body Styles (Основной текст)
     *
     * **`bodyLarge`** - крупный основной текст
     * - Размер: 16sp, межстрочный: 24dp, межбуквенный: 0.5dp
     * - **Использование**: Основной текст статей, описания
     *
     * **`bodyMedium`** - стандартный основной текст
     * - Размер: 14sp, межстрочный: 20dp, межбуквенный: 0.25dp
     * - **Использование**: Стандартный текст, комментарии
     *
     * **`bodySmall`** - мелкий основной текст
     * - Размер: 12sp, межстрочный: 16dp, межбуквенный: 0.4dp
     * - **Использование**: Вспомогательный текст, подписи
     *
     * ## Label Styles (Тексты для интерфейса)
     *
     * **`labelLarge`** - крупный текст интерфейса
     * - Размер: 14sp, межстрочный: 20dp, межбуквенный: 0.1dp
     * - **Использование**: Текст кнопок, выпадающие списки
     *
     * **`labelMedium`** - стандартный текст интерфейса
     * - Размер: 12sp, межстрочный: 16dp, межбуквенный: 0.5dp
     * - **Использование**: Всплывающие подсказки, теги
     *
     * **`labelSmall`** - мелкий текст интерфейса
     * - Размер: 11sp, межстрочный: 16dp, межбуквенный: 0.5dp
     * - **Использование**: Самый мелкий текст, капсулы
     *
     * ## Кастомный стиль
     *
     * **`titleDisclaimer`** - на основе `titleSmall`
     * - **Использование**: Дисклеймеры, юридический текст, вспомогательная информация
     *
     * ## Практические рекомендации для e-commerce:
     *
     * - **`titleLarge`** - названия товаров в карточках
     * - **`bodyMedium`** - описания товаров, характеристики
     * - **`labelMedium`** - цены, скидки, теги "новинка"
     * - **`titleSmall`** - артикулы, размеры, доступность
     * - **`titleDisclaimer`** - условия доставки, юридическая информация
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

package space.serphantom.myweather.app.ui.compose.extensions.cards

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Объект, содержащий настройки и стили для карточных компонентов приложения.
 * Определяет единообразное оформление карточек throughout приложения.
 */
object AppCard {

    /**
     * `Data class`, содержащий параметры стиля для карточных компонентов.
     *
     * @property [shape] Форма карточки (радиус скругления углов)
     * @property [elevation] Высота карточки (тень)
     * @property [containerColor] Цвет фона карточки
     * @property [contentColor] Цвет контента по умолчанию
     * @property [disabledContainerColor] Цвет фона когда карточка отключена
     * @property [disabledContentColor] Цвет контента когда карточка отключена
     *
     * @see CardDefaults
     */
    @Immutable
    data class CardStyle(
        val shape: Shape,
        val elevation: CardElevation,
        val containerColor: Color,
        val contentColor: Color,
        val disabledContainerColor: Color,
        val disabledContentColor: Color,
    ) {

        /**
         * Преобразует стиль в объект [CardColors] для использования в компоненте [androidx.compose.material3.Card].
         *
         * @return Объект [CardColors] с настройками цветов
         */
        @Composable
        fun toCardColors(): CardColors {
            return CardDefaults.cardColors(
                containerColor = containerColor,
                contentColor = contentColor,
                disabledContainerColor = disabledContainerColor,
                disabledContentColor = disabledContentColor,
            )
        }
    }

    /**
     * Константы для радиусов скругления
     */
    private object CornerRadius {
        const val SMALL = 8
        const val MEDIUM = 12
        const val LARGE = 16
        const val EXTRA_LARGE = 20
    }

    /**
     * Константы для `elevation`
     */
    private object Elevation {
        const val NONE = 0
        const val LOW = 1
        const val MEDIUM = 2
        const val HIGH = 4
        const val EXTRA_HIGH = 8
    }

    /**
     * Создает и возвращает стиль для карточек без `elevation`.
     * Используется для карточек, которые визуально ближе к поверхности.
     *
     * @param containerColor Цвет фона карточки - (по умолчанию [surfaceContainerLow][MaterialTheme.colorScheme.surfaceContainerLow])
     * @return Стиль карточки без `elevation`
     */
    @Composable
    fun noneElevationStyle(containerColor: Color = MaterialTheme.colorScheme.surfaceContainerLow): CardStyle {
        return CardStyle(
            shape = RoundedCornerShape(CornerRadius.LARGE.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = Elevation.NONE.dp),
            containerColor = containerColor,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        )
    }

    /**
     * Создает и возвращает стиль для карточек с низким `elevation`.
     * Используется для карточек, которые визуально ближе к поверхности.
     *
     * @param containerColor Цвет фона карточки - (по умолчанию [surfaceContainerLow][MaterialTheme.colorScheme.surfaceContainerLow])
     * @return Стиль карточки с низким `elevation`
     */
    @Composable
    fun lowElevationStyle(containerColor: Color = MaterialTheme.colorScheme.surfaceContainerLow): CardStyle {
        return CardStyle(
            shape = RoundedCornerShape(CornerRadius.LARGE.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = Elevation.LOW.dp),
            containerColor = containerColor,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        )
    }

    /**
     * Создает и возвращает стиль для карточек со средним `elevation`.
     * Используется для стандартных карточек с умеренной тенью.
     *
     * @param [containerColor] Цвет фона карточки - (по умолчанию [surface][MaterialTheme.colorScheme.surface])
     * @return Стиль карточки со средним elevation
     */
    @Composable
    fun mediumElevationStyle(containerColor: Color = MaterialTheme.colorScheme.surface): CardStyle {
        return CardStyle(
            shape = androidx.compose.foundation.shape.RoundedCornerShape(CornerRadius.MEDIUM.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = Elevation.MEDIUM.dp),
            containerColor = containerColor,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        )
    }

    /**
     * Создает и возвращает стиль для карточек с высоким `elevation`.
     * Используется для карточек, которые должны визуально "парить" над поверхностью.
     *
     * @param [containerColor] Цвет фона карточки - (по умолчанию [surface][MaterialTheme.colorScheme.surface])
     * @return Стиль карточки с высоким elevation
     */
    @Composable
    fun highElevationStyle(containerColor: Color = MaterialTheme.colorScheme.surface): CardStyle {
        return CardStyle(
            shape = androidx.compose.foundation.shape.RoundedCornerShape(CornerRadius.MEDIUM.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = Elevation.HIGH.dp),
            containerColor = containerColor,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        )
    }

    /**
     * Создает и возвращает стиль для прозрачных карточек без фона.
     * Используется для карточек, которые должны сливаться с фоном.
     *
     * @return Стиль прозрачной карточки
     */
    @Composable
    fun transparentStyle(): CardStyle {
        return CardStyle(
            shape = androidx.compose.foundation.shape.RoundedCornerShape(CornerRadius.MEDIUM.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = Elevation.NONE.dp),
            containerColor = Color.Companion.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = Color.Companion.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        )
    }

    /**
     * Создает и возвращает стиль для карточек с акцентным цветом.
     * Используется для выделения важных карточек.
     *
     * @return Стиль акцентной карточки
     */
    @Composable
    fun accentStyle(): CardStyle {
        return CardStyle(
            shape = androidx.compose.foundation.shape.RoundedCornerShape(CornerRadius.LARGE.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = Elevation.MEDIUM.dp),
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.12f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.38f)
        )
    }

    /**
     * Создает и возвращает стиль для карточек с ошибкой или предупреждением.
     * Используется для отображения состояний ошибок.
     *
     * @return Стиль карточки с ошибкой
     */
    @Composable
    fun errorStyle(): CardStyle {
        return CardStyle(
            shape = androidx.compose.foundation.shape.RoundedCornerShape(CornerRadius.MEDIUM.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = Elevation.LOW.dp),
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer,
            disabledContainerColor = MaterialTheme.colorScheme.onErrorContainer.copy(alpha = 0.12f),
            disabledContentColor = MaterialTheme.colorScheme.onErrorContainer.copy(alpha = 0.38f)
        )
    }
}
package space.serphantom.myweather.app.ui.compose.components.settings

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale

/**
 * `Data class`, содержащий настройки для компонента изображения.
 * Определяет параметры загрузки, отображения и обработки состояний изображения.
 *
 * @property [alignment] Выравнивание изображения внутри контейнера
 * @property [contentScale] Способ масштабирования изображения
 * @property [contentDescription] Подпись описания изображения
 * @property [crossFadeDurationInMilliseconds] Длительность анимации перекрестного `fade`-эффекта при загрузке
 * @property [errorDrawableResourceId] `Resource ID drawable` для отображения при ошибке загрузки
 * @property [placeholderDrawableResourceId] `Resource ID drawable` для отображения во время загрузки
 *
 * @see Alignment
 * @see ContentScale
 * @see DrawableRes
 */
@Immutable
data class ImageComponentSettings(
    val alignment: Alignment = Alignment.Center,
    val contentScale: ContentScale = ContentScale.Fit,
    val contentDescription: String? = null,
    val crossFadeDurationInMilliseconds: Int = 100,
    @field:DrawableRes
    val errorDrawableResourceId: Int? = null,
    @field:DrawableRes
    val placeholderDrawableResourceId: Int? = null,
)

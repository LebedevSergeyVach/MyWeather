package space.serphantom.myweather.app.ui.compose.components.images

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.fallback
import coil3.request.placeholder
import space.serphantom.myweather.app.ui.compose.components.settings.ImageComponentSettings

/**
 * Компонент для отображения изображений с использованием библиотеки `Coil`.
 * Поддерживает загрузку изображений по `URL`, кастомные настройки и обработку состояний загрузки.
 *
 * @param [modifier] Модификатор для настройки внешнего вида и размера изображения
 * @param [url] `URL`-адрес изображения для загрузки. Если `null`, изображение не будет загружаться
 * @param [settings] Настройки компонента изображения, определяющие поведение загрузки и отображения
 * @param [onImageLoadingSuccess] `Callback`, вызываемый при успешной загрузке изображения
 * @param [onImageLoadingError] `Callback`, вызываемый при ошибке загрузки изображения
 *
 * @see rememberAsyncImagePainter
 * @see ImageComponentSettings
 * @see ImageRequest
 */
@Composable
fun ImageComponent(
    modifier: Modifier = Modifier,
    url: String?,
    settings: ImageComponentSettings = ImageComponentSettings(),
    onImageLoadingSuccess: (() -> Unit)? = null,
    onImageLoadingError: (() -> Unit)? = null,
) {

    /**
     * [Painter] для асинхронной загрузки и отображения изображения.
     * Использует [rememberAsyncImagePainter] для эффективной загрузки и кэширования.
     */
    val painter = rememberAsyncImagePainter(
        model = createImageRequest(url, settings),
        contentScale = settings.contentScale,
        onSuccess = { onImageLoadingSuccess?.invoke() },
        onError = { onImageLoadingError?.invoke() }
    )

    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = null,
        alignment = settings.alignment,
        contentScale = settings.contentScale,
    )
}

/**
 * Создает и возвращает объект [ImageRequest] для загрузки изображения с учетом переданных настроек.
 * Функция оптимизирована с помощью [remember] для избежания избыточных пересозданий.
 *
 * @param [url] `URL`-адрес изображения для загрузки
 * @param [settings] Настройки компонента изображения
 * @return Настроенный объект [ImageRequest] для загрузки изображения
 *
 * @see ImageRequest
 * @see ImageComponentSettings
 * @see NonRestartableComposable
 */
@Composable
@NonRestartableComposable
private fun createImageRequest(
    url: String?,
    settings: ImageComponentSettings,
): ImageRequest {
    val context = LocalContext.current
    return remember(key1 = context, key2 = url) {
        val builder = ImageRequest.Builder(context)
            .data(url)
            .crossfade(settings.crossFadeDurationInMilliseconds)

        settings.errorDrawableResourceId?.let {
            builder.error(it)
            builder.fallback(it)
        }

        settings.placeholderDrawableResourceId?.let {
            builder.placeholder(it)
        }

        builder.build()
    }
}

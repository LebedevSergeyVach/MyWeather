package space.serphantom.myweather.app.ui.compose.extensions.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

/**
 * Модификатор для блокировки всех жестов взаимодействия
 * Используется для предотвращения скролла на области TopAppBar
 */
fun Modifier.blockAllGestures(): Modifier = pointerInput(Unit) {
    awaitPointerEventScope {
        while (true) {
            val event = awaitPointerEvent()
            event.changes.forEach { it.consume() }
        }
    }
}

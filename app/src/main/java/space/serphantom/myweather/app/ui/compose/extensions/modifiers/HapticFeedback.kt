package space.serphantom.myweather.app.ui.compose.extensions.modifiers

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback

/**
 * Расширяет [Modifier] для добавления тактильной обратной связи (вибрации)
 * при достижении краев прокручиваемого списка [LazyListState].
 *
 * Модификатор отслеживает состояние [lazyListState] и вызывает заданный тактильный
 * отклик при достижении первого (элемент 0, смещение 0) или последнего элемента списка.
 * Отклик срабатывает однократно при *первом* достижении края после его покидания.
 *
 * **Пример использования:**
 * ```kotlin
 * val lazyListState = rememberLazyListState()
 * LazyColumn(
 *     modifier = Modifier.hapticScrollEdge(lazyListState),
 *     state = lazyListState
 * ) { /* ... */ }
 * ```
 *
 * @param [lazyListState] Состояние [LazyListState] для отслеживания прокрутки и определения краев.
 * @param [hapticFeedbackType] Тип тактильного отклика. По умолчанию [HapticFeedbackType.TextHandleMove].
 * @param [hapticFeedback] Экземпляр [HapticFeedback] для выполнения эффектов. По умолчанию [LocalHapticFeedback.current].
 * @return [Modifier], применяющий логику тактильной обратной связи к компоненту.
 *
 * @see HapticFeedback
 * @see HapticFeedbackType
 * @see LocalHapticFeedback
 * @see LazyListState
 */
@SuppressLint("FrequentlyChangingValue")
@Composable
fun Modifier.hapticScrollEdge(
    lazyListState: LazyListState,
    hapticFeedbackType: HapticFeedbackType = HapticFeedbackType.TextHandleMove,
    hapticFeedback: HapticFeedback = LocalHapticFeedback.current,
): Modifier = composed {
    var wasAtTop by remember { mutableStateOf(false) }
    var wasAtBottom by remember { mutableStateOf(false) }
    var isInitialized by remember { mutableStateOf(false) }
    val hapticFeedbackType = remember { hapticFeedbackType }

    LaunchedEffect(
        key1 = lazyListState.firstVisibleItemIndex,
        key2 = lazyListState.firstVisibleItemScrollOffset
    ) {
        val layoutInfo = lazyListState.layoutInfo

        val isAtTop = lazyListState.firstVisibleItemIndex == 0
                && lazyListState.firstVisibleItemScrollOffset == 0

        val isAtBottom = if (layoutInfo.visibleItemsInfo.isNotEmpty()) {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.last()

            lastVisibleItem.index == layoutInfo.totalItemsCount - 1 &&
                    lastVisibleItem.offset + lastVisibleItem.size <= layoutInfo.viewportEndOffset
        } else false

        if (isInitialized) {
            if (isAtTop && wasAtTop.not()) {
                hapticFeedback.performHapticFeedback(hapticFeedbackType = hapticFeedbackType)
            }

            if (isAtBottom && wasAtBottom.not()) {
                hapticFeedback.performHapticFeedback(hapticFeedbackType = hapticFeedbackType)
            }
        } else isInitialized = true

        wasAtTop = isAtTop
        wasAtBottom = isAtBottom
    }

    return@composed this
}

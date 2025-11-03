package space.serphantom.myweather.app.ui.compose.extensions

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

@SuppressLint("FrequentlyChangingValue")
@Composable
fun Modifier.hapticScrollEdge(
    lazyListState: LazyListState,
    hapticFeedbackType: HapticFeedbackType = HapticFeedbackType.SegmentTick,
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

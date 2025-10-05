package space.serphantom.myweather.extensions.coroutines

import kotlinx.coroutines.CancellableContinuation
import kotlin.coroutines.resume

fun <T> CancellableContinuation<T>.resumeIfActive(data: T) {
    if (isActive) {
        resume(data)
    }
}
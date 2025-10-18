package space.serphantom.myweather.app.data.network.executors

import space.serphantom.myweather.app.application.ApplicationThrowable

/**
 * Исключение при выполнении сетевого запроса
 */
sealed class NetworkThrowable(cause: Throwable) : ApplicationThrowable(cause) {
    class IO(cause: Throwable) : NetworkThrowable(cause)
    class Timeout(cause: Throwable) : NetworkThrowable(cause)
    class Parsing(cause: Throwable) : NetworkThrowable(cause)
    class Unknown(cause: Throwable) : NetworkThrowable(cause)
    class ServerError(cause: Throwable) : NetworkThrowable(cause)
}
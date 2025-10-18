package space.serphantom.myweather.app.data.network.executors.errors

import space.serphantom.myweather.app.data.network.executors.NetworkThrowable

/**
 * Ошибка выполнения сетевого запроса
 */
sealed class NetworkError {
    class HTTP(val data: HTTPErrorData) : NetworkError()
    class Failure(val throwable: NetworkThrowable) : NetworkError()
}

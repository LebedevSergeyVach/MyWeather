package space.serphantom.myweather.app.data.network.executors.results

import space.serphantom.myweather.app.data.network.executors.errors.NetworkError

/**
 * Результат выполнения сетевого запроса
 */
sealed class NetworkResult<out ResponseBody> {
    class Success<ResponseBody>(val responseBody: ResponseBody) : NetworkResult<ResponseBody>()
    class Error(val error: NetworkError) : NetworkResult<Nothing>()
}
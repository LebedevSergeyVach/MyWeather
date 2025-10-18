package space.serphantom.myweather.app.data.network.executors

import space.serphantom.myweather.app.data.network.executors.errors.NetworkError
import space.serphantom.myweather.app.data.network.executors.results.ExecutionResult
import space.serphantom.myweather.app.data.network.executors.results.NetworkResult
import space.serphantom.myweather.app.data.network.parsers.ErrorDataParser

class ApiRequestExecutor {

    suspend fun <ResponseBody> execute(
        request: suspend () -> ResponseBody,
    ): ExecutionResult<ResponseBody> {
        val result: NetworkResult<ResponseBody> = NetworkRequestExecutor().execute {
            request.invoke()
        }

        return when (result) {
            is NetworkResult.Success -> ExecutionResult.Success(result.responseBody)
            is NetworkResult.Error -> convertNetworkErrorToExecutionError(result.error)
        }
    }

    private fun convertNetworkErrorToExecutionError(
        networkError: NetworkError,
    ): ExecutionResult.Error {
        return when (networkError) {
            is NetworkError.HTTP -> parseErrorData(networkError.data.errorBody)
            is NetworkError.Failure -> ExecutionResult.Error.Failure(networkError.throwable)
        }
    }

    private fun parseErrorData(errorBody: String?): ExecutionResult.Error {
        return try {
            val errorData = ErrorDataParser().parse(errorBody)
            ExecutionResult.Error.Expected(errorData)
        } catch (throwable: Throwable) {
            val networkThrowable = NetworkThrowable.ServerError(cause = throwable)
            ExecutionResult.Error.Failure(networkThrowable)
        }
    }
}

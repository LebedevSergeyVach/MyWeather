package space.serphantom.myweather.app.data.network.executors.results

import space.serphantom.myweather.app.application.ApplicationThrowable
import space.serphantom.myweather.app.data.network.executors.errors.ErrorExecuteData

sealed class ExecutionResult<out Data> {

    class Success<Data>(val data: Data) : ExecutionResult<Data>()

    sealed class Error : ExecutionResult<Nothing>() {
        class Expected(val data: ErrorExecuteData) : Error()
        class Failure(val throwable: ApplicationThrowable) : Error()
    }
}
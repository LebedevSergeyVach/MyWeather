package space.serphantom.myweather.data.result.base

/**
 * Результат выполнения операции
 */
sealed class ExecutionResult<out T> {

    class Success<T>(val data: T) : ExecutionResult<T>()

    class Error(val message: String?) : ExecutionResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data = $data]"
            is Error -> "Error[error = $message]"
        }
    }
}

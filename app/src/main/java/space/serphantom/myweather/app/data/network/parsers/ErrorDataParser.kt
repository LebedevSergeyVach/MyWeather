package space.serphantom.myweather.app.data.network.parsers

import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import space.serphantom.myweather.app.data.network.executors.errors.ErrorExecuteData
import space.serphantom.myweather.app.data.network.executors.errors.ErrorResponse

/**
 * Parser ответа сетевого запроса, завершившегося ошибкой
 */
class ErrorDataParser : KoinComponent {

    private val json by inject<Json>()

    fun parse(errorBodyText: String?): ErrorExecuteData {
        if (errorBodyText == null) return ErrorExecuteData(message = null, code = null)

        val errorResponse = json.decodeFromString<ErrorResponse>(errorBodyText)

        return ErrorExecuteData(errorResponse.message, errorResponse.code)
    }
}

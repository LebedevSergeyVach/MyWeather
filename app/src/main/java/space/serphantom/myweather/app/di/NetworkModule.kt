package space.serphantom.myweather.app.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import space.serphantom.myweather.BuildConfig
import java.util.concurrent.TimeUnit

/**
 * Таймаут сетевых операций в секундах.
 *
 * Определяет максимальное время ожидания для различных этапов сетевого запроса:
 * - Установка соединения (connect timeout)
 * - Чтение данных (read timeout)
 * - Запись данных (write timeout)
 *
 * @value 30L Стандартное значение таймаута в 30 секунд подходит для большинства мобильных приложений.
 * Для медленных сетей или больших файлов может потребоваться увеличение этого значения.
 *
 * @see OkHttpClient.Builder.connectTimeout
 * @see OkHttpClient.Builder.readTimeout
 * @see OkHttpClient.Builder.writeTimeout
 */
private const val TIMEOUT_IN_SECONDS = 30L

/**
 * MIME-тип контента для JSON данных.
 *
 * Используется для указания типа передаваемых данных в HTTP-запросах и ответах.
 * Значение "application/json" является стандартным для JSON формата согласно RFC 7159.
 *
 * @value "application/json" Стандартный MIME-тип для JSON данных.
 *
 * @see MediaType.get
 * @see <a href="https://tools.ietf.org/html/rfc7159">RFC 7159 - The JavaScript Object Notation (JSON) Data Interchange Format</a>
 */
private const val CONTENT_TYPE: String = "application/json"

/**
 * Модуль зависимостей Koin для сетевого слоя приложения.
 *
 * Предоставляет все необходимые зависимости для работы с сетевыми запросами:
 * - Конфигурация JSON сериализации/десериализации
 * - HTTP клиент с настройками таймаутов и логированием
 * - Retrofit instance с конвертером для kotlinx-serialization
 * - API сервисы для работы с удаленными данными
 *
 * @see module Модуль Koin для объединения зависимостей
 * @see <a href="https://insert-koin.io/docs/reference/koin-core/modules">Koin Modules Documentation</a>
 */
val networkModule = module {

    // NETWORK JSON CONFIGURATION

    /**
     * Предоставляет сконфигурированный экземпляр [Json] для сериализации и десериализации данных.
     *
     * ## Конфигурация включает:
     * - `ignoreUnknownKeys = true` - Игнорирует неизвестные ключи в JSON, что полезно при эволюции API
     * - `coerceInputValues = true` - Приводит значения `null` к значениям по умолчанию для примитивных типов
     * - `explicitNulls = false` - Позволяет опускать `null` значения при сериализации, уменьшая размер JSON
     * - `isLenient = true` - Разрешает нестрогий парсинг JSON (нестандартные форматы чисел, комментарии)
     * - `encodeDefaults = true` - Сериализует значения по умолчанию, обеспечивая полную передачу состояния
     *
     * @return [Json] Сконфигурированный экземпляр JSON сериализатора
     *
     * @see Json Конфигуратор JSON сериализации
     * @see <a href="https://github.com/Kotlin/kotlinx.serialization">Kotlinx Serialization Documentation</a>
     * @see <a href="https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/json.md">JSON Serialization Guide</a>
     */
    single<Json> {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            explicitNulls = false
            isLenient = true
            encodeDefaults = true
        }
    }

    /**
     * Предоставляет настроенный экземпляр [OkHttpClient] для выполнения HTTP-запросов.
     *
     * ## Особенности конфигурации:
     * - Таймаут соединения: [TIMEOUT_IN_SECONDS] секунд
     * - Таймаут чтения: [TIMEOUT_IN_SECONDS] секунд
     * - Таймаут записи: [TIMEOUT_IN_SECONDS] секунд
     * - Логирование запросов/ответов в debug-режиме (уровень BODY)
     *
     * ## Логирование включает:
     * - URL запроса и метод
     * - Заголовки запроса и ответа
     * - Тело запроса и ответа
     * - Коды статусов и время выполнения
     *
     * @return [OkHttpClient] Настроенный HTTP-клиент для сетевых операций
     *
     * @see OkHttpClient.Builder Построитель HTTP-клиента
     * @see HttpLoggingInterceptor Интерсептор для логирования сетевых запросов
     * @see BuildConfig.DEBUG Флаг режима отладки приложения
     * @see <a href="https://square.github.io/okhttp/">OkHttp Official Documentation</a>
     * @see <a href="https://square.github.io/okhttp/features/interceptors/">OkHttp Interceptors Guide</a>
     */
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .readTimeout(timeout = TIMEOUT_IN_SECONDS, unit = TimeUnit.SECONDS)
            .connectTimeout(timeout = TIMEOUT_IN_SECONDS, unit = TimeUnit.SECONDS)
            .writeTimeout(timeout = TIMEOUT_IN_SECONDS, unit = TimeUnit.SECONDS)
            .readTimeout(timeout = TIMEOUT_IN_SECONDS, unit = TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                }
            }
            .build()
    }

    /**
     * Предоставляет настроенный экземпляр [Retrofit] для работы с REST API.
     *
     * ## Конфигурация включает:
     * - Базовый URL API: [BuildConfig.URL_SERVER_WEATHER]
     * - HTTP клиент: [OkHttpClient] из DI
     * - Конвертер для kotlinx-serialization с указанным [MediaType]
     *
     * ## Требует экспериментального API:
     * Использует [ExperimentalSerializationApi] для работы с конвертером kotlinx-serialization
     *
     * @return [Retrofit] Настроенный экземпляр Retrofit для создания API сервисов
     *
     * @throws IllegalStateException Если базовый URL невалиден или конвертер не может быть создан
     *
     * @see Retrofit.Builder Построитель Retrofit клиента
     * @see asConverterFactory Конвертер для kotlinx-serialization
     * @see MediaType.toMediaType Создание MediaType из строки
     * @see ExperimentalSerializationApi Экспериментальный API сериализации
     * @see <a href="https://square.github.io/retrofit/">Retrofit Official Documentation</a>
     * @see <a href="https://github.com/JakeWharton/retrofit2-kotlinx-serialization-converter">Retrofit Converter for Kotlinx Serialization</a>
     */
    @OptIn(ExperimentalSerializationApi::class)
    single<Retrofit> {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BuildConfig.URL_SERVER_WEATHER)
            .addConverterFactory(get<Json>().asConverterFactory(CONTENT_TYPE.toMediaType()))
            .build()
    }
}

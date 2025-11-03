package space.serphantom.myweather.app.ui.extensions.log

import android.util.Log
import space.serphantom.myweather.BuildConfig

/**
 * Утилитарный класс для логирования сообщений в приложении.
 * Этот класс предоставляет статические методы для вывода логов различных уровней (`debug`, `error`, `info`, `warning`).
 * Все логи выводятся с общим тегом [TAG] `LoggerHelperDebug`, что упрощает фильтрацию логов в `Logcat`.
 *
 * @see Log Класс `Android` для работы с логами.
 */
object Logger {

    /**
     * Общий тег для всех логов, выводимых этим классом.
     * Используется для фильтрации логов в Logcat.
     */
    private const val TAG = "LoggerHelperDebug"

    /**
     * Выводит отладочное (debug) сообщение в лог.
     * Этот метод используется для вывода сообщений, которые помогают в отладке приложения.
     *
     * @param message Сообщение, которое нужно вывести в лог.
     * @param tag Название TAG, по умолчанию [TAG] `LoggerHelperDebug`
     *
     * @see Log.d Вывод отладочных сообщений в Android.
     */
    fun d(message: String, tag: String = TAG) {
        if (BuildConfig.DEBUG) Log.d(tag, message)
    }

    /**
     * Выводит сообщение об ошибке (error) в лог.
     * Этот метод используется для вывода сообщений об ошибках, которые могут возникнуть в приложении.
     * Также можно передать исключение (Throwable), чтобы вывести стектрейс ошибки.
     *
     * @param message Сообщение, которое нужно вывести в лог.
     * @param throwable Исключение, связанное с ошибкой. Если передано, будет выведен стектрейс.
     * @param tag Название TAG, по умолчанию [TAG] `LoggerHelperDebug`
     *
     * @see Log.e Вывод сообщений об ошибках в Android.
     */
    fun e(message: String, throwable: Throwable? = null, tag: String = TAG) {
        if (BuildConfig.DEBUG) Log.e(tag, message, throwable)
    }

    /**
     * Выводит информационное (info) сообщение в лог.
     * Этот метод используется для вывода важных информационных сообщений, которые не являются ошибками,
     * но могут быть полезны для понимания работы приложения.
     *
     * @param message Сообщение, которое нужно вывести в лог.
     * @param tag Название TAG, по умолчанию [TAG] `LoggerHelperDebug`
     *
     * @see Log.i Вывод информационных сообщений в Android.
     */
    fun i(message: String, tag: String = TAG) {
        if (BuildConfig.DEBUG) Log.i(tag, message)
    }

    /**
     * Выводит предупреждающее (warning) сообщение в лог.
     * Этот метод используется для вывода сообщений, которые указывают на потенциальные проблемы
     * или нестандартные ситуации в приложении.
     *
     * @param message Сообщение, которое нужно вывести в лог.
     * @param tag Название TAG, по умолчанию [TAG] `LoggerHelperDebug`
     *
     * @see Log.w Вывод предупреждающих сообщений в Android.
     */
    fun w(message: String, tag: String = TAG) {
        if (BuildConfig.DEBUG) Log.w(tag, message)
    }

    /**
     * Выводит обычное (verbose) сообщение в лог.
     *
     * @param message Сообщение, которое нужно вывести в лог.
     * @param tag Название TAG, по умолчанию [TAG] `LoggerHelperDebug`
     *
     * @see Log.v Вывод обычных сообщений в Android.
     */
    fun v(message: String, tag: String = TAG) {
        if (BuildConfig.DEBUG) Log.v(tag, message)
    }

    fun wtf(message: String, tag: String = TAG) {
        if (BuildConfig.DEBUG) Log.wtf(tag, message)
    }
}

package space.serphantom.myweather.app.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import space.serphantom.myweather.BuildConfig

/**
 * Объект-менеджер для настройки и инициализации `dependency injection` с использованием `Koin`.
 * Отвечает за старт `Koin` контейнера и регистрацию всех модулей приложения.
 *
 * @see startKoin
 */
object DIManager {

    /**
     * Инициализирует `Koin dependency injection` контейнер с настройками для `Android` приложения.
     * Настраивает логирование в зависимости от типа сборки (`debug`/`release`) и регистрирует все модули приложения.
     *
     * @param [context] Контекст приложения для предоставления `Android-specific` зависимостей
     *
     * @see androidLogger
     * @see androidContext
     * @see modules
     */
    fun startKoin(context: Context) {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE)
            androidContext(context)

            modules(
                networkModule,
                repositoriesModule,
                modelsModule,
                viewModelsModule,
            )
        }
    }
}

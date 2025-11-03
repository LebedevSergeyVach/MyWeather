package space.serphantom.myweather.app.application

import android.app.Application
import space.serphantom.myweather.app.di.DIManager

/**
 * Основной класс приложения, отвечающий за инициализацию глобальных компонентов.
 * Наследуется от `Android Application` класса и выполняет настройку dependency `injection`.
 *
 * @see Application
 */
class App : Application() {
    /**
     * Вызывается при создании приложения. Выполняет инициализацию `dependency injection`.
     *
     * @see initKoin
     */
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    /**
     * Инициализирует `Koin dependency injection` контейнер через [DIManager].
     *
     * @see DIManager.startKoin
     */
    private fun initKoin() {
        DIManager.startKoin(this)
    }
}

package space.serphantom.myweather.app.application

import android.app.Application
import space.serphantom.myweather.app.di.DIManager

/**
 * Класс-Application
 * Инициализация DI Koin с помощью [DIManager]
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        DIManager.startKoin(this)
    }
}

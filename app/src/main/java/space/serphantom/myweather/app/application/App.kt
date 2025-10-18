package space.serphantom.myweather.app.application

import android.app.Application
import org.koin.core.context.startKoin


/**
 * Класс-Application
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            modules()
        }
    }
}

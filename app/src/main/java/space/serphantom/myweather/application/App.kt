package space.serphantom.myweather.application

import android.app.Application
import org.koin.core.context.startKoin
import space.serphantom.myweather.di.networkModule


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
            modules(
                networkModule
            )
        }
    }
}

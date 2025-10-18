package space.serphantom.myweather.app.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import space.serphantom.myweather.BuildConfig

object DIManager {

    fun startKoin(context: Context) {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE)
            androidContext(context)

            modules(
                networkModule,
                repositoriesModule,
                modelsModule,
            )
        }
    }
}

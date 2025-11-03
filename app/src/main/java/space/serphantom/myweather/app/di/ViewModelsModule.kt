package space.serphantom.myweather.app.di

import androidx.lifecycle.ViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import space.serphantom.myweather.app.ui.viewmodels.main.MainViewModel

/**
 * Koin модуль для регистрации [ViewModel] компонентов.
 * Содержит `viewModel` определения для создания [Android ViewModel][ViewModel]'ей.
 *
 * @property [MainViewModel] [ViewModel] для главного экрана приложения
 *
 * @see module
 * @see viewModel
 */
val viewModelsModule = module {

    /**
     * Регистрирует [ViewModel] для главного экрана приложения.
     * ViewModel будет связана с `lifecycle` и пересоздаваться при необходимости.
     *
     * @return Новый экземпляр [MainViewModel]
     *
     * @see MainViewModel
     */
    viewModel {
        MainViewModel()
    }
}

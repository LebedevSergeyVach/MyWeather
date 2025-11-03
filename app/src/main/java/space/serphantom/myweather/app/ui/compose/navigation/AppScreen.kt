package space.serphantom.myweather.app.ui.compose.navigation

import androidx.compose.runtime.Immutable

/**
 * Запечатанный класс, представляющий экраны приложения в системе навигации.
 * Каждый экран определяется уникальным маршрутом (`route`).
 *
 * @property [route] Уникальный строковый идентификатор маршрута для навигации
 *
 * @see sealed class
 */
@Immutable
sealed class AppScreen(val route: String) {

    /**
     * Объект, представляющий главный экран погоды.
     * Использует маршрут `main` для навигации.
     *
     * @see AppScreen
     */
    object MainWeather : AppScreen("main")
}

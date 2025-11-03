package space.serphantom.myweather.app.ui.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import space.serphantom.myweather.app.ui.compose.screen.main.MainWeatherScreen

/**
 * Основной компонент навигации приложения, использующий `Jetpack Navigation Compose`.
 * Определяет граф навигации и начальный экран приложения.
 *
 * @see NavHost
 * @see rememberNavController
 */
@Composable
fun AppNavigationComponent() {

    /**
     * Контроллер навигации, управляющий `stack` экранов и переходами между ними.
     */
    val navController = rememberNavController()

    /**
     * Контейнер навигации, определяющий граф экранов приложения.
     *
     * @param [navController] Контроллер для управления навигацией
     * @param [startDestination] Начальный экран приложения
     */
    NavHost(
        navController = navController,
        startDestination = AppScreen.MainWeather.route,
    ) {

        /**
         * Определение composable экрана в графе навигации.
         *
         * @param [route] Уникальный идентификатор маршрута экрана
         */
        composable(
            route = AppScreen.MainWeather.route,
        ) {
            MainWeatherScreen()
        }
    }
}

package space.serphantom.myweather.app.ui.activities

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import space.serphantom.myweather.app.ui.compose.navigation.AppNavigationComponent
import space.serphantom.myweather.app.ui.compose.theme.AppTheme
import android.app.Activity

/**
 * Главная [Activity] приложения, являющаяся точкой входа в пользовательский интерфейс.
 * Наследуется от [AppCompatActivity] и настраивает полноэкранный режим с прозрачными системными барами.
 *
 * @see AppCompatActivity
 */
class MainActivity : AppCompatActivity() {

    /**
     * Вызывается при создании [Activity]. Выполняет начальную настройку интерфейса:
     * - Настраивает `edge-to-edge` режим
     * - Устанавливает `Compose` контент
     * - Инициализирует навигацию
     *
     * @param savedInstanceState Сохраненное состояние [Activity] для восстановления
     *
     * @see setupEdgeToEdge
     * @see setContent
     * @see AppTheme
     * @see AppNavigationComponent
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupEdgeToEdge()
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    AppNavigationComponent()
                }
            }
        }
    }

    /**
     * Настраивает `edge-to-edge` режим для приложения,
     * делая системные бары (`status bar` и `navigation bar`) прозрачными.
     * Это позволяет контенту отображаться под системными барами, создавая современный полноэкранный вид.
     *
     * @see enableEdgeToEdge
     * @see SystemBarStyle
     */
    private fun setupEdgeToEdge() {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
        )
    }
}

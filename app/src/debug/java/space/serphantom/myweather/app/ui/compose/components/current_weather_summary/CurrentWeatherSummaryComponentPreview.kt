package space.serphantom.myweather.app.ui.compose.components.current_weather_summary

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import space.serphantom.myweather.app.ui.compose.data.entity.current_weather_summary.CurrentWeatherSummaryData
import java.time.LocalDate


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CurrentWeatherSummaryComponentNoDisclaimerPreview() {
    MaterialTheme {
        CurrentWeatherSummaryComponent(
            weatherData = CurrentWeatherSummaryData(
                feelsLike = 18,
                date = LocalDate.now().plusDays(1),
                humidity = 45,
                windSpeed = 5.1,
                chanceOfRain = 5
            )
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CurrentWeatherSummaryComponentPreview() {
    MaterialTheme {
        CurrentWeatherSummaryComponent(
            weatherData = CurrentWeatherSummaryData(
                feelsLike = 23,
                date = LocalDate.now(),
                humidity = 65,
                windSpeed = 3.2,
                chanceOfRain = 15,
                disclaimer = "Данные обновлены 5 минут назад"
            )
        )
    }
}


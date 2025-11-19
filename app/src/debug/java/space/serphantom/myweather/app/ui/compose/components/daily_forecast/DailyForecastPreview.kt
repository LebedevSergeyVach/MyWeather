package space.serphantom.myweather.app.ui.compose.components.daily_forecast

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import space.serphantom.myweather.app.ui.compose.components.stubs.createTestDailyForecastData
import space.serphantom.myweather.app.ui.compose.data.entity.daily_forecast.DailyForecastData
import kotlin.collections.take

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DailyForecastComponentPreview() {
    MaterialTheme {
        val sampleData = DailyForecastData(
            days = createTestDailyForecastData().take(7)
        )

        DailyForecastComponent(
            onShowMoreClick = { },
            onShowMoreForDateClick = { },
            forecastData = sampleData,
            initialVisibleItems = 5,
            modifier = Modifier.padding(16.dp)
        )
    }
}


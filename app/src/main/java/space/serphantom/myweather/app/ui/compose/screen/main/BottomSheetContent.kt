package space.serphantom.myweather.app.ui.compose.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

// Контент Bottom Sheet
@Composable
fun BottomSheetContent(onDismiss: () -> Unit) {
    // Для правильного отображения Bottom Sheet
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "Настройки погоды",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Пример настроек
                var temperatureUnit by remember { mutableStateOf("°C") }
                var windSpeedUnit by remember { mutableStateOf("м/с") }

                Text(
                    text = "Единицы измерения",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Выбор единиц температуры
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Температура:", modifier = Modifier.weight(1f))
                    SegmentedButton(
                        items = listOf("°C", "°F", "K"),
                        selectedItem = temperatureUnit,
                        onItemSelected = { temperatureUnit = it }
                    )
                }

                // Выбор единиц скорости ветра
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Скорость ветра:", modifier = Modifier.weight(1f))
                    SegmentedButton(
                        items = listOf("м/с", "км/ч", "миль/ч"),
                        selectedItem = windSpeedUnit,
                        onItemSelected = { windSpeedUnit = it }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Сохранить и закрыть")
                }

                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Просто закрыть")
                }
            }
        }
    }
}


// Кастомный Segmented Button
@Composable
fun SegmentedButton(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = item == selectedItem
            val shape = when {
                index == 0 -> RoundedCornerShape(topStart = 7.dp, bottomStart = 7.dp)
                index == items.lastIndex -> RoundedCornerShape(topEnd = 7.dp, bottomEnd = 7.dp)
                else -> RoundedCornerShape(0.dp)
            }

            Box(
                modifier = Modifier
                    .clickable { onItemSelected(item) }
                    .background(
                        color = if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.background,
                        shape = shape
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .border(
                        width = 0.5.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = shape
                    )
            ) {
                Text(
                    text = item,
                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}
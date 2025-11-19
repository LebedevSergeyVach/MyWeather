package space.serphantom.myweather.app.ui.compose.components.stubs

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * BottomSheet для отображения всех цветов из MaterialTheme.colorScheme
 * с поддержкой навигации через NavController
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPaletteBottomSheet(
    onDismiss: () -> Unit,
) {
    val context = LocalContext.current
    val colorScheme = when (isSystemInDarkTheme()) {
        true -> dynamicDarkColorScheme(context)
        false -> dynamicLightColorScheme(context)
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(),
        containerColor = colorScheme.surface
    ) {
        ColorsComponent(colorScheme)
    }
}

@Composable
private fun ColorsComponent(
    colorScheme: ColorScheme,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Палитра цветов темы",
                style = MaterialTheme.typography.headlineSmall,
                color = colorScheme.onSurface,
            )

            IconButton(
                onClick = {},
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Закрыть",
                    tint = colorScheme.onSurface
                )
            }
        }

        Text(
            text = "Material Theme Color Scheme",
            style = MaterialTheme.typography.bodyMedium,
            color = colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 600.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Primary Colors
            item { ColorSchemeSectionTitle("Primary Colors") }
            item { ColorItem(color = colorScheme.primary, name = "primary") }
            item { ColorItem(color = colorScheme.onPrimary, name = "onPrimary") }
            item { ColorItem(color = colorScheme.primaryContainer, name = "primaryContainer") }
            item {
                ColorItem(
                    color = colorScheme.onPrimaryContainer,
                    name = "onPrimaryContainer"
                )
            }

            // Secondary Colors
            item { ColorSchemeSectionTitle("Secondary Colors") }
            item { ColorItem(color = colorScheme.secondary, name = "secondary") }
            item { ColorItem(color = colorScheme.onSecondary, name = "onSecondary") }
            item {
                ColorItem(
                    color = colorScheme.secondaryContainer,
                    name = "secondaryContainer"
                )
            }
            item {
                ColorItem(
                    color = colorScheme.onSecondaryContainer,
                    name = "onSecondaryContainer"
                )
            }

            // Tertiary Colors
            item { ColorSchemeSectionTitle("Tertiary Colors") }
            item { ColorItem(color = colorScheme.tertiary, name = "tertiary") }
            item { ColorItem(color = colorScheme.onTertiary, name = "onTertiary") }
            item {
                ColorItem(
                    color = colorScheme.tertiaryContainer,
                    name = "tertiaryContainer"
                )
            }
            item {
                ColorItem(
                    color = colorScheme.onTertiaryContainer,
                    name = "onTertiaryContainer"
                )
            }

            // Surface Colors
            item { ColorSchemeSectionTitle("Surface Colors") }
            item { ColorItem(color = colorScheme.background, name = "background") }
            item { ColorItem(color = colorScheme.onBackground, name = "onBackground") }
            item { ColorItem(color = colorScheme.surface, name = "surface") }
            item { ColorItem(color = colorScheme.onSurface, name = "onSurface") }
            item { ColorItem(color = colorScheme.surfaceVariant, name = "surfaceVariant") }
            item { ColorItem(color = colorScheme.onSurfaceVariant, name = "onSurfaceVariant") }

            // Error Colors
            item { ColorSchemeSectionTitle("Error Colors") }
            item { ColorItem(color = colorScheme.error, name = "error") }
            item { ColorItem(color = colorScheme.onError, name = "onError") }
            item { ColorItem(color = colorScheme.errorContainer, name = "errorContainer") }
            item { ColorItem(color = colorScheme.onErrorContainer, name = "onErrorContainer") }

            // Outline Colors
            item { ColorSchemeSectionTitle("Outline Colors") }
            item { ColorItem(color = colorScheme.outline, name = "outline") }
            item { ColorItem(color = colorScheme.outlineVariant, name = "outlineVariant") }

            // Additional Colors
            item { ColorSchemeSectionTitle("Additional Colors") }
            item { ColorItem(color = colorScheme.scrim, name = "scrim") }
            item { ColorItem(color = colorScheme.inverseOnSurface, name = "inverseOnSurface") }
            item { ColorItem(color = colorScheme.inverseSurface, name = "inverseSurface") }
            item { ColorItem(color = colorScheme.inversePrimary, name = "inversePrimary") }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

/**
 * Упрощенный элемент цвета (без HEX для компактности)
 */
@Composable
private fun ColorItem(
    color: Color,
    name: String,
) {
    val textColor = if (color.isLightColor()) Color.Black else Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(color, RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            color = textColor,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = color.toHex(),
            style = MaterialTheme.typography.labelSmall,
            color = textColor.copy(alpha = 0.8f)
        )
    }
}

/**
 * Элемент для отображения цвета с названием и HEX значением
 */
@Composable
private fun ColorItem(
    color: Color,
    name: String,
    hexValue: String,
) {
    val textColor = if (color.isLightColor()) Color.Black else Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color, RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            color = textColor,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = hexValue,
            style = MaterialTheme.typography.bodySmall,
            color = textColor.copy(alpha = 0.8f)
        )
    }
}

/**
 * Заголовок секции цветов
 */
@Composable
private fun ColorSchemeSectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

/**
 * Расширение для конвертации Color в HEX строку
 */
fun Color.toHex(): String {
    val red = (red * 255).toInt()
    val green = (green * 255).toInt()
    val blue = (blue * 255).toInt()
    val alpha = (alpha * 255).toInt()

    return if (alpha < 255) {
        String.format("#%02X%02X%02X%02X", alpha, red, green, blue)
    } else {
        String.format("#%02X%02X%02X", red, green, blue)
    }
}

/**
 * Проверка, является ли цвет светлым (для выбора контрастного текста)
 */
fun Color.isLightColor(): Boolean {
    // Формула воспринимаемой яркости
    val brightness = (red * 299 + green * 587 + blue * 114) / 1000
    return brightness > 0.5
}

package space.serphantom.myweather.app.ui.compose.components.stubs

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
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
 * с поддержкой сравнения статических и динамических цветов
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPaletteBottomSheet(
    darkTheme: Boolean = isSystemInDarkTheme(),
    useDynamicColors: Boolean = true,
    onDismiss: () -> Unit = {},
    onCheckedChange: (Boolean) -> Unit,
) {
    val context = LocalContext.current

    val dynamicColorScheme = when (darkTheme) {
        true -> dynamicDarkColorScheme(context)
        false -> dynamicLightColorScheme(context)
    }

    val staticColorScheme = when (darkTheme) {
        true -> darkColorScheme()
        false -> lightColorScheme()
    }

    val currentColorScheme = if (useDynamicColors) dynamicColorScheme else staticColorScheme

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(),
        containerColor = currentColorScheme.surface
    ) {
        ColorsComparisonComponent(
            staticColorScheme = staticColorScheme,
            dynamicColorScheme = dynamicColorScheme,
            useDynamicColors = useDynamicColors,
            onCheckedChange = { onCheckedChange(useDynamicColors) },
        )
    }
}

@Composable
private fun ColorsComparisonComponent(
    staticColorScheme: ColorScheme,
    dynamicColorScheme: ColorScheme,
    useDynamicColors: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    val currentColorScheme = if (useDynamicColors) dynamicColorScheme else staticColorScheme

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Заголовок
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Палитра цветов темы",
                    style = MaterialTheme.typography.headlineSmall,
                    color = currentColorScheme.onSurface,
                )
                Text(
                    text = if (useDynamicColors) "Динамические цвета" else "Статические цвета",
                    style = MaterialTheme.typography.bodyMedium,
                    color = currentColorScheme.onSurfaceVariant,
                )
            }

            IconButton(
                onClick = {},
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Закрыть",
                    tint = currentColorScheme.onSurface
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Переключение между режимами
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Статические",
                style = MaterialTheme.typography.labelLarge,
                color = if (!useDynamicColors) currentColorScheme.primary else currentColorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1f)
            )

            Switch(
                checked = useDynamicColors,
                onCheckedChange = onCheckedChange,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "Динамические",
                style = MaterialTheme.typography.labelLarge,
                color = if (useDynamicColors) currentColorScheme.primary else currentColorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 500.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Primary Colors
            item { ColorSchemeSectionTitle("Primary Colors", currentColorScheme) }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.primary,
                    dynamicColor = dynamicColorScheme.primary,
                    name = "primary",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.onPrimary,
                    dynamicColor = dynamicColorScheme.onPrimary,
                    name = "onPrimary",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.primaryContainer,
                    dynamicColor = dynamicColorScheme.primaryContainer,
                    name = "primaryContainer",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.onPrimaryContainer,
                    dynamicColor = dynamicColorScheme.onPrimaryContainer,
                    name = "onPrimaryContainer",
                    useDynamicColors = useDynamicColors
                )
            }

            // Secondary Colors
            item { ColorSchemeSectionTitle("Secondary Colors", currentColorScheme) }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.secondary,
                    dynamicColor = dynamicColorScheme.secondary,
                    name = "secondary",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.onSecondary,
                    dynamicColor = dynamicColorScheme.onSecondary,
                    name = "onSecondary",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.secondaryContainer,
                    dynamicColor = dynamicColorScheme.secondaryContainer,
                    name = "secondaryContainer",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.onSecondaryContainer,
                    dynamicColor = dynamicColorScheme.onSecondaryContainer,
                    name = "onSecondaryContainer",
                    useDynamicColors = useDynamicColors
                )
            }

            // Tertiary Colors
            item { ColorSchemeSectionTitle("Tertiary Colors", currentColorScheme) }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.tertiary,
                    dynamicColor = dynamicColorScheme.tertiary,
                    name = "tertiary",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.onTertiary,
                    dynamicColor = dynamicColorScheme.onTertiary,
                    name = "onTertiary",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.tertiaryContainer,
                    dynamicColor = dynamicColorScheme.tertiaryContainer,
                    name = "tertiaryContainer",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.onTertiaryContainer,
                    dynamicColor = dynamicColorScheme.onTertiaryContainer,
                    name = "onTertiaryContainer",
                    useDynamicColors = useDynamicColors
                )
            }

            // Surface Colors
            item { ColorSchemeSectionTitle("Surface Colors", currentColorScheme) }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.background,
                    dynamicColor = dynamicColorScheme.background,
                    name = "background",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.onBackground,
                    dynamicColor = dynamicColorScheme.onBackground,
                    name = "onBackground",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.surface,
                    dynamicColor = dynamicColorScheme.surface,
                    name = "surface",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.onSurface,
                    dynamicColor = dynamicColorScheme.onSurface,
                    name = "onSurface",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.surfaceVariant,
                    dynamicColor = dynamicColorScheme.surfaceVariant,
                    name = "surfaceVariant",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.onSurfaceVariant,
                    dynamicColor = dynamicColorScheme.onSurfaceVariant,
                    name = "onSurfaceVariant",
                    useDynamicColors = useDynamicColors
                )
            }

            // Error Colors
            item { ColorSchemeSectionTitle("Error Colors", currentColorScheme) }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.error,
                    dynamicColor = dynamicColorScheme.error,
                    name = "error",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.onError,
                    dynamicColor = dynamicColorScheme.onError,
                    name = "onError",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.errorContainer,
                    dynamicColor = dynamicColorScheme.errorContainer,
                    name = "errorContainer",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.onErrorContainer,
                    dynamicColor = dynamicColorScheme.onErrorContainer,
                    name = "onErrorContainer",
                    useDynamicColors = useDynamicColors
                )
            }

            // Outline Colors
            item { ColorSchemeSectionTitle("Outline Colors", currentColorScheme) }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.outline,
                    dynamicColor = dynamicColorScheme.outline,
                    name = "outline",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.outlineVariant,
                    dynamicColor = dynamicColorScheme.outlineVariant,
                    name = "outlineVariant",
                    useDynamicColors = useDynamicColors
                )
            }

            // Inverse Colors
            item { ColorSchemeSectionTitle("Inverse Colors", currentColorScheme) }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.inverseOnSurface,
                    dynamicColor = dynamicColorScheme.inverseOnSurface,
                    name = "inverseOnSurface",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.inverseSurface,
                    dynamicColor = dynamicColorScheme.inverseSurface,
                    name = "inverseSurface",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.inversePrimary,
                    dynamicColor = dynamicColorScheme.inversePrimary,
                    name = "inversePrimary",
                    useDynamicColors = useDynamicColors
                )
            }

            // Surface Container Colors
            item { ColorSchemeSectionTitle("Surface Container Colors", currentColorScheme) }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.surfaceContainerHighest,
                    dynamicColor = dynamicColorScheme.surfaceContainerHighest,
                    name = "surfaceContainerHighest",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.surfaceContainerHigh,
                    dynamicColor = dynamicColorScheme.surfaceContainerHigh,
                    name = "surfaceContainerHigh",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.surfaceContainer,
                    dynamicColor = dynamicColorScheme.surfaceContainer,
                    name = "surfaceContainer",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.surfaceContainerLow,
                    dynamicColor = dynamicColorScheme.surfaceContainerLow,
                    name = "surfaceContainerLow",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.surfaceContainerLowest,
                    dynamicColor = dynamicColorScheme.surfaceContainerLowest,
                    name = "surfaceContainerLowest",
                    useDynamicColors = useDynamicColors
                )
            }

            // Additional Colors
            item { ColorSchemeSectionTitle("Additional Colors", currentColorScheme) }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.scrim,
                    dynamicColor = dynamicColorScheme.scrim,
                    name = "scrim",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.surfaceBright,
                    dynamicColor = dynamicColorScheme.surfaceBright,
                    name = "surfaceBright",
                    useDynamicColors = useDynamicColors
                )
            }
            item {
                ColorComparisonItem(
                    staticColor = staticColorScheme.surfaceDim,
                    dynamicColor = dynamicColorScheme.surfaceDim,
                    name = "surfaceDim",
                    useDynamicColors = useDynamicColors
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

/**
 * Элемент для сравнения статического и динамического цвета
 */
@Composable
private fun ColorComparisonItem(
    staticColor: Color,
    dynamicColor: Color,
    name: String,
    useDynamicColors: Boolean,
) {
    val currentColor = if (useDynamicColors) dynamicColor else staticColor
    val textColor = if (currentColor.isLightColor()) Color.Black else Color.White

    // Проверяем, отличаются ли цвета
    val colorsDiffer = staticColor != dynamicColor

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(currentColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
                fontWeight = FontWeight.Medium
            )

            if (colorsDiffer) {
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(
                            if (useDynamicColors) Color.Green else Color.Red,
                            CircleShape
                        )
                )
            }
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = currentColor.toHex(),
                style = MaterialTheme.typography.labelSmall,
                color = textColor.copy(alpha = 0.9f)
            )
            if (colorsDiffer) {
                Text(
                    text = if (useDynamicColors) "dynamic" else "static",
                    style = MaterialTheme.typography.labelSmall,
                    color = textColor.copy(alpha = 0.6f)
                )
            }
        }
    }
}

/**
 * Заголовок секции цветов
 */
@Composable
private fun ColorSchemeSectionTitle(title: String, colorScheme: ColorScheme) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = colorScheme.onSurfaceVariant,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(vertical = 8.dp)
    )
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

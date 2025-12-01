package space.serphantom.myweather.app.ui.compose.extensions.common

/**
 * Преобразует буквенное обозначение направления в градусы.
 *
 * @param [direction] Буквенное обозначение направления
 * @return Направление в градусах
 */
fun getDegreesFromWindDirection(direction: String): Float {
    return when (direction) {
        WIND_DIRECTION_NORTH -> WIND_NORTH_DEGREES
        WIND_DIRECTION_NORTH_EAST -> WIND_NORTH_EAST_DEGREES
        WIND_DIRECTION_EAST -> WIND_EAST_DEGREES
        WIND_DIRECTION_SOUTH_EAST -> WIND_SOUTH_EAST_DEGREES
        WIND_DIRECTION_SOUTH -> WIND_SOUTH_DEGREES
        WIND_DIRECTION_SOUTH_WEST -> WIND_SOUTH_WEST_DEGREES
        WIND_DIRECTION_WEST -> WIND_WEST_DEGREES
        WIND_DIRECTION_NORTH_WEST -> WIND_NORTH_WEST_DEGREES
        else -> WIND_DEFAULT_DEGREES
    }
}

private const val WIND_DIRECTION_NORTH = "С"
private const val WIND_DIRECTION_NORTH_EAST = "СВ"
private const val WIND_DIRECTION_EAST = "В"
private const val WIND_DIRECTION_SOUTH_EAST = "ЮВ"
private const val WIND_DIRECTION_SOUTH = "Ю"
private const val WIND_DIRECTION_SOUTH_WEST = "ЮЗ"
private const val WIND_DIRECTION_WEST = "З"
private const val WIND_DIRECTION_NORTH_WEST = "СЗ"

private const val WIND_NORTH_DEGREES = 0f
private const val WIND_NORTH_EAST_DEGREES = 45f
private const val WIND_EAST_DEGREES = 90f
private const val WIND_SOUTH_EAST_DEGREES = 135f
private const val WIND_SOUTH_DEGREES = 180f
private const val WIND_SOUTH_WEST_DEGREES = 225f
private const val WIND_WEST_DEGREES = 270f
private const val WIND_NORTH_WEST_DEGREES = 315f
private const val WIND_DEFAULT_DEGREES = 0f

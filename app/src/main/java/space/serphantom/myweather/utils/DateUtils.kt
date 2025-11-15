package space.serphantom.myweather.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

/**
 * Утилиты для работы с датами
 */
object DateUtils {

    /**
     * Форматирует дату в строку вида "Понедельник, 15 декабря"
     *
     * @param [date] дата для форматирования
     * @param [locale] локаль для форматирования (по умолчанию русская)
     *
     * @return отформатированная строка с днем недели и датой
     */
    fun formatDateWithDayOfWeek(date: LocalDate, locale: Locale = Locale("ru")): String {
        val dayOfWeek = date.dayOfWeek
            .getDisplayName(TextStyle.FULL, locale)
            .replaceFirstChar { it.uppercase() }

        val dateFormatter = DateTimeFormatter.ofPattern("d MMMM", locale)
        val dateString = date.format(dateFormatter)

        return "$dayOfWeek, $dateString"
    }
}

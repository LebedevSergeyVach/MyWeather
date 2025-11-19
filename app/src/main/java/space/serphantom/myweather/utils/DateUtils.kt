app/src/main/java/space/serphantom/myweather/utils/DateUtils.ktpackage space.serphantom.myweather.utils

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


    /**
     * Форматирует дату в сокращенный формат "число месяц"
     * Пример: "15 дек"
     *
     * @param date дата для форматирования
     * @param locale локаль для форматирования (по умолчанию русская)
     * @return отформатированная строка с числом и сокращенным названием месяца
     */
    fun formatShortDate(date: LocalDate, locale: Locale = Locale("ru")): String {
        val formatter = DateTimeFormatter.ofPattern("d MMM", locale)
        return date.format(formatter)
    }

    /**
     * Форматирует день недели для прогноза погоды
     * Возвращает "Сегодня", "Завтра" или сокращенное название дня недели
     *
     * @param date дата для определения дня недели
     * @param today текущая дата для сравнения
     * @param locale локаль для форматирования (по умолчанию русская)
     * @return отформатированное название дня недели
     */
    fun formatDayOfWeekForForecast(
        date: LocalDate,
        today: LocalDate = LocalDate.now(),
        locale: Locale = Locale("ru"),
    ): String {
        return when (date) {
            today -> "Сегодня"
            today.plusDays(1) -> "Завтра"
            else -> date.dayOfWeek.getDisplayName(TextStyle.SHORT, locale)
                .replaceFirstChar { it.uppercase() }
        }
    }
}

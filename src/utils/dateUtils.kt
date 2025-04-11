package utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun dateToString(date: Date, pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
    val formatter = SimpleDateFormat(pattern)
    return formatter.format(date)
}

fun stringToDate(dateString: String, pattern: String = "yyyy-MM-dd HH:mm:ss"): Date? {
    return try {
        val formatter = SimpleDateFormat(pattern)
        formatter.parse(dateString)
    } catch (e: ParseException) {
        e.printStackTrace()
        null
    }
}
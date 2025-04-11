package datasource

import java.io.File
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun readCsvBasic(filePath: String): List<List<String>> {
    if (File(filePath).exists()) {
        return File(filePath).readLines()
            .map { line -> line.split(",").map { it.trim() } }
    }
    return emptyList()
}

fun writeCsvBasic(filePath: String, data: List<List<String>>) {
    File(filePath).bufferedWriter().use { writer ->
        data.forEach { row ->
            writer.write(row.joinToString(","))
            writer.newLine()
        }
    }
}

fun appendCsvBasic(filePath: String, data: List<List<String>>) {
    val file = File(filePath)

    for (row in data) {
        file.appendText(row.joinToString(","))
        file.appendText("\n")
    }
}

fun deleteLineByNumber(filePath: String, lineNumberToDelete: Int) {
    val lines = File(filePath).readLines().toMutableList()
    if (lineNumberToDelete in 1..lines.size) {
        lines.removeAt(lineNumberToDelete - 1)
        File(filePath).writeText(lines.joinToString("\n"))
    }
}

fun deleteEntireFile(filePath: String) {
    val file = File(filePath)
    if (file.exists()) {
        file.delete()
    }
}

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
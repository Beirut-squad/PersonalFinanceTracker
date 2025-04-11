package datasource.utils

import java.io.File

class CsvParser {
    fun readCsv(file: File): List<List<String>> {
        return file.readLines()
            .map { line -> line.split(",").map { it.trim() } }
    }

    fun writeCsv(file: File, data: List<List<String>>) {
        file.bufferedWriter().use { writer ->
            data.forEach { row ->
                writer.write(row.joinToString(","))
                writer.newLine()
            }
        }
    }

    fun appendCsv(file: File, data: List<List<String>>) {
        for (row in data) {
            file.appendText(row.joinToString(","))
            file.appendText("\n")
        }
    }

    fun deleteLineByNumber(file: File, lineNumberToDelete: Int) {
        val lines = file.readLines().toMutableList()
        if (lineNumberToDelete in 1..lines.size) {
            lines.removeAt(lineNumberToDelete - 1)
            file.writeText(lines.joinToString("\n"))
        }
    }

    fun deleteEntireFile(file: File) {
        file.delete()
    }
}
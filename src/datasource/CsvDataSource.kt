package datasource

import datasource.utils.CsvParser
import models.Category
import models.Transaction
import models.TransactionType
import utils.dateToString
import utils.stringToDate
import java.io.File
import java.io.IOException
import java.util.*

class CsvDataSource(
    private val csvParser: CsvParser
) : FinanceTrackerDataSource {
    override fun addTransactions(transaction: Transaction) {
        val file = getCsv()
        csvParser.appendCsv(file, listOf(parseTransactionToCsv(transaction)))
    }

    private fun getCsv(): File {
        val file = File(FILE_NAME)
        if (file.exists()) {
            return file
        }
        throw IOException("Unable to access file")
    }

    override fun editTransactions(transaction: Transaction) {
        deleteTransactions(transaction)
        addTransactions(transaction)
    }

    override fun getTransactions(): List<Transaction> {
        val file = getCsv()
        val csvEntries = csvParser.readCsv(file)
        val allTransactions = mutableListOf<Transaction>()

        for (entry in csvEntries) {
            allTransactions.add(parseCsvTransaction(entry))
        }

        return allTransactions.toList()
    }

    override fun deleteTransactions(transaction: Transaction) {
        val file = getCsv()
        val allData = csvParser.readCsv(file)
        val transactions = allData.map { parseCsvTransaction(it) }

        for (currentTransactionInd in 1..transactions.size) {
            val currentTransaction = transactions[currentTransactionInd - 1]
            if (currentTransaction.id == transaction.id) {
                csvParser.deleteLineByNumber(file, currentTransactionInd)
                break
            }
        }
    }

    override fun clear() {
        csvParser.deleteEntireFile(getCsv())
    }

    private fun parseTransactionToCsv(transaction: Transaction): List<String> {
        return listOf(
            transaction.id.toString(),
            transaction.title,
            transaction.amount.toString(),
            transaction.transactionType.toString(),
            transaction.category.toString(),
            dateToString(transaction.date)
        )
    }

    private fun parseCsvTransaction(csvList: List<String>): Transaction {
        return Transaction(
            id = csvList[0].toInt(),
            title = csvList[1],
            amount = csvList[2].toDouble(),
            transactionType = TransactionType.entries.find { it.toString() == csvList[3] } ?: TransactionType.INCOME,
            category = Category.entries.find { it.toString() == csvList[4] } ?: Category.FEES,
            date = stringToDate(csvList[5]) ?: Date()
        )
    }

    companion object {
        private const val FILE_NAME = "transactions.csv"
    }
}
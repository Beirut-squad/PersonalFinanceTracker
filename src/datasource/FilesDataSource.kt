package datasource

import models.Category
import models.Transaction
import models.TransactionType
import java.io.File
import java.util.*

class FilesDataSource : FinanceTrackerDataSource {
    override fun addTransactions(transaction: Transaction) {
        if (!File(FILE_NAME).exists()) {
            writeCsvBasic(FILE_NAME, listOf(parseTransactionToCsv(transaction)))
        } else {
            appendCsvBasic(FILE_NAME, listOf(parseTransactionToCsv(transaction)))
        }
    }

    override fun editTransactions(transaction: Transaction) {
        deleteTransactions(transaction)
        addTransactions(transaction)
    }

    override fun getTransactions(): List<Transaction> {
        val csvEntries = readCsvBasic(FILE_NAME)
        val allTransactions = mutableListOf<Transaction>()

        for (entry in csvEntries) {
            allTransactions.add(parseCsvTransaction(entry))
        }

        return allTransactions.toList()
    }

    override fun deleteTransactions(transaction: Transaction) {
        val allData = readCsvBasic(FILE_NAME)
        val transactions = allData.map { parseCsvTransaction(it) }

        for (currentTransactionInd in 1..transactions.size) {
            val currentTransaction = transactions[currentTransactionInd - 1]
            if (currentTransaction.id == transaction.id) {
                deleteLineByNumber(FILE_NAME, currentTransactionInd)
                break
            }
        }
    }

    override fun clear() {
        deleteEntireFile(FILE_NAME)
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
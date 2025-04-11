package datasource

import models.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class InTextFileDataSource(private val filePath: String = "transactions.txt") : FinanceTrackerDataSource {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val file = File(filePath)

    private val transactions: MutableList<Transaction> = mutableListOf()

    init {
        if (file.exists()) {
            file.readLines().forEach { line ->
                val parts = line.split(",")
                if (parts.size == 6) {
                    val transaction = Transaction(
                        id = parts[0].toInt(),
                        title = parts[1],
                        amount = parts[2].toDouble(),
                        transactionType = TransactionType.valueOf(parts[3]),
                        category = Category.valueOf(parts[4]),
                        date = dateFormat.parse(parts[5])!!
                    )
                    transactions.add(transaction)
                }
            }
        }
    }

    private fun saveToFile() {
        file.writeText("")
        transactions.forEach { transaction ->
            file.appendText(
                "Id: ( ${transaction.id} ),Title: ( ${transaction.title} ) ,Amount: ( ${transaction.amount} )," +
                        "Transaction type: ( ${transaction.transactionType} ) ,Category: ( ${transaction.category} )," +
                        " date: ( ${dateFormat.format(transaction.date)} )\n"
            )
        }
    }

    override fun addTransactions(transaction: Transaction) {
        transactions.add(transaction)
        saveToFile()
    }

    override fun editTransactions(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun getTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun deleteTransactions(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }


}

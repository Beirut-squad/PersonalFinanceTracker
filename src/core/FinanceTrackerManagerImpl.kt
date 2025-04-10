package core

import datasource.FinanceTrackerDataSource
import datasource.InMemoryDataSource
import models.Category
import models.Transaction
import models.TransactionType
import java.util.*

class FinanceTrackerManagerImpl(private val ftDataSource: FinanceTrackerDataSource) : FinanceTrackerManager {

    override fun addTransaction(transaction: Transaction): Boolean {
        return false
    }

    override fun deleteTransaction(id: Int): Boolean {

        val transactions = ftDataSource.transactions

        if (id < 0) {
            return false
        }

        if (transactions.isEmpty()) {
            return false
        }

        val deletedTransaction = transactions.find { id == it.id }

        if (deletedTransaction == null) {
            return false
        }

        return transactions.remove(deletedTransaction)
    }

    override fun clearTransactions(): Boolean {
        return true
    }

    override fun editTransaction(transaction: Transaction): Boolean {
        return false
    }
}
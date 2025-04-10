package core

import datasource.FinanceTrackerDataSource
import datasource.InMemoryDataSource
import models.Category
import models.Transaction
import models.TransactionType
import java.util.*

class FinanceTrackerManagerImpl(private val ftDataSource: FinanceTrackerDataSource): FinanceTrackerManager {

    override fun addTransaction(transaction: Transaction): Boolean {
        return false
    }

    override fun deleteTransaction(id: Int): Boolean {
        return false
    }

    override fun clearTransactions(): Boolean {
        return true
    }

    override fun editTransaction(transaction: Transaction): Boolean {
        return false
    }
}
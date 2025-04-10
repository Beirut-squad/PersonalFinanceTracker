package core

import datasource.FinanceTrackerDataSource
import datasource.InMemoryDataSource
import models.Category
import models.Transaction
import models.TransactionType
import java.util.*

class FinanceTrackerManagerImpl(
    private val ftDataSource: FinanceTrackerDataSource,
    private val validator: FinanceTrackerValidator
): FinanceTrackerManager {

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
        if (!validator.validateTransaction(transaction)) return false

        for (existingTransaction in ftDataSource.transactions) {
            if (transaction.id == existingTransaction.id) {
                ftDataSource.editTransactions(transaction)
                return true
            }
        }

        return false
    }

    override fun viewMonthlySummery(month: Int, year: Int): List<Transaction> {
        return emptyList()
    }
}
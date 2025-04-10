package datasource

import models.Transaction

interface FinanceTrackerDataSource {
    fun addTransactions(transaction: Transaction)
    fun editTransactions(transaction: Transaction)
    fun getTransactions(): List<Transaction>
    fun deleteTransactions(transaction: Transaction)
    fun clear()
}
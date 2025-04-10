package datasource

import models.Transaction

interface FinanceTrackerDataSource {
    var transactions: MutableList<Transaction>

    fun addTransactions(transaction: Transaction)
    fun editTransactions(transaction: Transaction)
    fun viewTransactions(transaction: Transaction)
    fun deleteTransactions(transaction: Transaction)
    fun clear()
}
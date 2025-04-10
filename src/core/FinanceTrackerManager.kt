package core

import models.Transaction

interface FinanceTrackerManager {
    //todo: add methods to handle transactions and generate reports
    fun addTransaction(transaction: Transaction): Boolean
    fun deleteTransaction(id:Int): Boolean
    fun clearTransactions(): Boolean
    fun editTransaction(transaction: Transaction) : Boolean
    fun viewMonthlySummery(month: Int , year: Int): List<Transaction>
}
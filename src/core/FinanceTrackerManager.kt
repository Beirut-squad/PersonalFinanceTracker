package core

import models.TotalTransactions
import models.Transaction

interface FinanceTrackerManager {
    fun addTransaction(transaction: Transaction): Boolean
    fun deleteTransaction(id:Int): Boolean
    fun clearTransactions()
    fun editTransaction(transaction: Transaction) : Boolean
    fun getBalanceReport(): TotalTransactions
    fun getMonthlySummery(month: Int, year: Int): List<Transaction>
}
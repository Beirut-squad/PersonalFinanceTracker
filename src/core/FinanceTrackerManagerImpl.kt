package core

import models.Transaction

class FinanceTrackerManagerImpl: FinanceTrackerManager {
    //todo: implement methods to handle transactions and generate reports
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
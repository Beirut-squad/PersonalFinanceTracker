package core

import models.Transaction

interface FinanceTrackerManager {
    //todo: add methods to handle transactions and generate reports
    fun addTransaction(transaction: Transaction): Boolean
}
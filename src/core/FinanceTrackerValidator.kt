package core

import models.Transaction

interface FinanceTrackerValidator {
    //todo: add methods to validate transactions and reports
    fun validateTransaction(transaction: Transaction): Boolean
}
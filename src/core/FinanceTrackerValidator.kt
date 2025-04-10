package core

import models.Transaction

interface FinanceTrackerValidator {
    fun validateTransaction(transaction: Transaction): Boolean
}
package core

import models.Transaction

class FinanceTrackerValidatorImp :FinanceTrackerValidator{
    override fun validateTransaction(transaction: Transaction): Boolean {
        if(transaction.amount <= 0) {
            return false
        }
        else if(transaction.title.isBlank()) {
            return false
        }
        return true
    }
}
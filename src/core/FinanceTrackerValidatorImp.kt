package core

import models.Transaction

class FinanceTrackerValidatorImp :FinanceTrackerValidator{
    //todo: implement methods to validate transactions and reports
    override fun validateTransaction(transaction: Transaction): Boolean {
        if(transaction.amount <= 0) {
            return false
        }
        else if(transaction.title.isEmpty()) {
            return false
        }
        return true
    }
}
package core

import models.Transaction

class FinanceTrackerValidatorImp: FinanceTrackerValidator {
    //todo: implement methods to validate transactions and reports
    override fun validateTransaction(transaction: Transaction): Boolean {
        return true
    }
}
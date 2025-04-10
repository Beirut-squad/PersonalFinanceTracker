package test

import core.FinanceTrackerManager
import models.Category
import models.Transaction
import models.TransactionType
import java.util.*
class DeleteTransactionChecker (
    private val financeTrackerManager: FinanceTrackerManager,
    private val checker: Checker = Checker()
) {
    fun runAllDeleteChecker() {
        financeTrackerManager.clearTransactions()
        val value1 = Transaction(
            id = 0,
            title = "AboAnas",
            amount = 500.0,
            transactionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )
        val value2 = Transaction(
            id = -1,
            title = "AboAnas",
            amount = 500.0,
            transactionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )
        financeTrackerManager.addTransaction(value1)

        //Delete element in list, it will delete successfully
        checker.check(
            name = "Delete element in the list",
            result = financeTrackerManager.deleteTransaction(value1.id),
            expectedResult = true
        )
        financeTrackerManager.addTransaction(value1)
        //Delete element not in the list, we can't delete element not found
        checker.check(
            name = "Delete element not in the list",
            result = financeTrackerManager.deleteTransaction(-1),
            expectedResult = false
        )

        //Delete element with incorrect id, we can't delete element not found
        checker.check(
            name = "Element with wrong id",
            result = financeTrackerManager.deleteTransaction(-99),
            expectedResult = false
        )

        financeTrackerManager.clearTransactions()
        //Delete element from empty list, we can't delete element not found
        checker.check(
            name = "Delete element in empty list",
            result = financeTrackerManager.deleteTransaction(-99),
            expectedResult = false
        )

    }

}
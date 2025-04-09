package testing

import core.FinanceTrackerManager
import models.Category
import models.Transaction
import models.TransactionType
import java.util.*
class DeleteTransactionChecker (
    private val financeTrackerManager: FinanceTrackerManager
) {
    fun runAllDeleteChecker() {
        val value1 = Transaction(
            id = 0,
            title = "AboAnas",
            amount = 500.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )
        val value2 = Transaction(
            id = -1,
            title = "AboAnas",
            amount = 500.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )
        financeTrackerManager.deleteTransaction(value1.id)
        //Delete element in list, it will delete successfully
        check(
            name = "Delete element in the list",
            result = financeTrackerManager.deleteTransaction(value1.id),
            correctResult = true
        )
        //Delete element not in the list, we can't delete element not found
        check(
            name = "Delete element not in the list",
            result = financeTrackerManager.deleteTransaction(0),
            correctResult = false
        )
        //Delete element with incorrect id, we can't delete element not found
        check(
            name = "Element with wrong id",
            result = financeTrackerManager.deleteTransaction(-1),
            correctResult = false
        )

        //Delete element from empty list, we can't delete element not found
        check(
            name = "Delete element in empty list",
            result = financeTrackerManager.deleteTransaction(-99),
            correctResult = false
        )

    }

    private fun <T> check(
        name: String,
        result: T,
        correctResult: T
    ) {
        if (result == correctResult) {
            println("\u001B[32mSuccess: $name")
        } else {
            println("\u001b[31mFailure: $name (Expected $correctResult but found $result)")
        }
    }
}
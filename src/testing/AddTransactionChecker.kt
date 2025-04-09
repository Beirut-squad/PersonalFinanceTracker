import core.FinanceTrackerManager
import models.Category
import models.Transaction
import models.TransactionType
import java.util.*

class AddTransactionChecker(
    private val financeTrackerManager: FinanceTrackerManager
) {

    fun runAddTests() {
        financeTrackerManager.clearTransactions()
        var transaction = Transaction(
            id = 0,
            title = "College fees",
            amount = 500.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FEES,
            date = Date()
        )

        check(
            name = "Adding a valid transaction to datasource",
            result = financeTrackerManager.addTransaction(transaction),
            expectedResult = true
        )

        /***/
        financeTrackerManager.clearTransactions()
        transaction = Transaction(
            id = 1,
            title = "Clothes",
            amount = -100.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.SHOPPING,
            date = Date()
        )
        check(
            name = "Invalid - trying to add a transaction with negative amount",
            result = financeTrackerManager.addTransaction(transaction),
            expectedResult = false
        )

        /***/
        financeTrackerManager.clearTransactions()
        transaction = Transaction(
            id = 1,
            title = "Food",
            amount = 0.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )
        check(
            name = "Invalid - trying to add a transaction with zero amount",
            result = financeTrackerManager.addTransaction(transaction),
            expectedResult = false
        )

        /***/
        financeTrackerManager.clearTransactions()
        val transaction1 = Transaction(
            id = 0,
            title = "Shopping Market",
            amount = 1000.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )

        financeTrackerManager.addTransaction(transaction1)

        val transaction2 = Transaction(
            id = 0,
            title = "Shopping Market",
            amount = 1000.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )

        check(
            name = "Invaild - trying to add a transaction with repeated id",
            result = financeTrackerManager.addTransaction(transaction2),
            expectedResult = false
        )

        /***/
        financeTrackerManager.clearTransactions()
        transaction = Transaction(
            id = 12,
            title = "",
            amount = 1000.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )
        check(
            name = "Invalid - trying to add transaction with no title",
            result = financeTrackerManager.addTransaction(transaction),
            expectedResult = false
        )


    }

    private fun <T> check(
        name: String,
        result: T,
        expectedResult: T
    ) {
        if (result == expectedResult) {
            println("\u001B[32mSuccess: $name")
        } else {
            println("\u001b[31mFailure: $name (Expected $expectedResult but found $result)")
        }
    }
}
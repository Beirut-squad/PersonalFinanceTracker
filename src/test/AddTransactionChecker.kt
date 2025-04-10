package test

import core.FinanceTrackerManager
import models.Category
import models.Transaction
import models.TransactionType
import java.util.*

class AddTransactionChecker(
    private val financeTrackerManager: FinanceTrackerManager,
    val printChecks: Checker = Checker()
) {

    fun runAddTests() {
        financeTrackerManager.clearTransactions()
        var transaction = Transaction(
            id = 0,
            title = "College fees",
            amount = 500.0,
            transactionType = TransactionType.EXPENSE,
            category = Category.FEES,
            date = Date()
        )

        printChecks.check(
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
            transactionType = TransactionType.EXPENSE,
            category = Category.SHOPPING,
            date = Date()
        )
        printChecks.check(
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
            transactionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )
        printChecks.check(
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
            transactionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )

        financeTrackerManager.addTransaction(transaction1)

        val transaction2 = Transaction(
            id = 0,
            title = "Shopping Market",
            amount = 1000.0,
            transactionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )

        printChecks.check(
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
            transactionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )
        printChecks.check(
            name = "Invalid - trying to add transaction with no title",
            result = financeTrackerManager.addTransaction(transaction),
            expectedResult = false
        )


    }

}
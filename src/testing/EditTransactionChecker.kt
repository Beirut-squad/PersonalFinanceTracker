package testing

import core.FinanceTrackerManager
import models.Category
import models.Transaction
import models.TransactionType
import java.util.*

class EditTransactionChecker(
    private val financeTrackerManager: FinanceTrackerManager
) {
    fun editTransactionCheck() {
        // valid transaction edit : all fields are correct
        financeTrackerManager.clearTransactions()
        val successfulTransaction = Transaction(
            id = 6,
            title = "t-shirt",
            amount = 60.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.SHOPPING,
            date = Date()
        )
        financeTrackerManager.addTransaction(successfulTransaction)
        check(
            name = "Should Successfully Edit Transaction  ",
            result = financeTrackerManager.editTransaction(successfulTransaction),
            expectedResult = true
        )

        // valid partial transaction edit : only some fields changed
        financeTrackerManager.clearTransactions()
        val successfulTransactionWithSomeFieldChanged = Transaction(
            id = 6,
            title = "supermarket",
            amount = 60.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.SHOPPING,
            date = Date()
        )
        financeTrackerManager.addTransaction(successfulTransactionWithSomeFieldChanged)
        check(
            name = "Should Successfully Update Transaction With Partial Changes",
            result = financeTrackerManager.editTransaction(successfulTransactionWithSomeFieldChanged),
            expectedResult = true
        )

        // invalid transaction edit : non existent ID
        financeTrackerManager.clearTransactions()
        val transactionWithNonExistentID = Transaction(
            id = -4,
            title = "Clothes",
            amount = 60.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.SHOPPING,
            date = Date()
        )
        check(
            name = "Should Fail To Edit When Transaction ID Does Not Exist ",
            result = financeTrackerManager.editTransaction(transactionWithNonExistentID),
            expectedResult = false
        )

        // successful transaction : reflects changes in balance
        financeTrackerManager.clearTransactions()
        val successfulTransactionToUpdateBalance = Transaction(
            id = 21,
            title = "Salary Bonus",
            amount = 750.0,
            transActionType = TransactionType.INCOME,
            category = Category.SALARY,
            date = Date()
        )
        financeTrackerManager.addTransaction(successfulTransactionToUpdateBalance)
        check(
            name = "Should Update Account Balance After Successful Edit",
            result = financeTrackerManager.editTransaction(successfulTransactionToUpdateBalance),
            expectedResult = true
        )

        // successful transaction : reflects changes in monthly summary
        financeTrackerManager.clearTransactions()
        val successfulTransactionToUpdateMonthlySummary = Transaction(
            id = 21,
            title = "Freelancer Project",
            amount = 1000.0,
            transActionType = TransactionType.INCOME,
            category = Category.SALARY,
            date = Date()
        )
        financeTrackerManager.addTransaction(successfulTransactionToUpdateMonthlySummary)
        check(
            name = "Should Update Monthly Summary After Successful Edit",
            result = financeTrackerManager.editTransaction(successfulTransactionToUpdateMonthlySummary),
            expectedResult = true
        )

        // unsuccessful transaction : failed to make transaction when title is only spaces
        val unsuccessfulTransactionWithWhiteSpaceOnly = Transaction(
            id = 21,
            title = " ",
            amount = 100.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )
        check(
            name = "Should Fail To Edit When Title Contains Only Whitespace",
            result = financeTrackerManager.editTransaction(unsuccessfulTransactionWithWhiteSpaceOnly),
            expectedResult = false
        )

        //Invalid: Should Fail To Edit When Amount Is Negative
        val transactionWithNegativeAmount = Transaction(
            id = 21,
            title = "Freelance Project",
            amount = -2500.0,
            transActionType = TransactionType.INCOME,
            category = Category.SALARY,
            date = Date()
        )
        check(
            name = "Should Fail To Edit When Amount Is Negative ",
            result = financeTrackerManager.editTransaction(transactionWithNegativeAmount),
            expectedResult = false
        )

        // Invalid: Should Fail To Edit When Amount EquaIs Zero
        val transactionWithAmountEqualZero = Transaction(
            id = 21,
            title = "Freelance Project",
            amount = 0.0,
            transActionType = TransactionType.INCOME,
            category = Category.SALARY,
            date = Date()
        )
        check(
            name = "Should Fail To Edit When Amount EquaIs Zero\n ",
            result = financeTrackerManager.editTransaction(transactionWithAmountEqualZero),
            expectedResult = false
        )

        // Invalid: Should Fail To enter empty description
        val transactionWithEmptyDescription = Transaction(
            id = 21,
            title = "",
            amount = 1000.0,
            transActionType = TransactionType.INCOME,
            category = Category.SALARY,
            date = Date()
        )
        check(
            name = "Should Fail To enter empty description ",
            result = financeTrackerManager.editTransaction(transactionWithEmptyDescription),
            expectedResult = false
        )



        // Invalid: Should Fail To Edit When Amount Exceeds Valid Range
        val transactionWithAmountOutOFRange =Transaction(
            id = 21,
            title = "Freelance Salary",
            amount = 1.8E308,
            transActionType = TransactionType.INCOME,
            category = Category.SALARY,
            date = Date()
        )
        check(
            name = "Should Fail To Edit When Amount Exceeds Valid Range ",
            result = financeTrackerManager.editTransaction(transactionWithAmountOutOFRange),
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
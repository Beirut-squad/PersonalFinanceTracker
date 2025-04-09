package Testing

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
        val successfulTransaction = Transaction(
            id = 6,
            title = "t-shirt",
            amount = 60.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.SHOPPING,
            date = Date()
        )
        check(
            name = "Should Successfully Edit Transaction  ",
            result = financeTrackerManager.editTransaction(successfulTransaction),
            expectedResult = true
        )

        // valid partial transaction edit : only some fields changed
        val successfulTransactionWithSomeFieldChanged = Transaction(
            id = 30,
            title = "supermarket",
            amount = 250.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )
        check(
            name = "Should Successfully Update Transaction With Partial Changes",
            result = financeTrackerManager.editTransaction(successfulTransactionWithSomeFieldChanged),
            expectedResult = true
        )

        // invalid transaction edit : non existent ID
        val transactionWithNonExistentID = Transaction(
            id = -4,
            title = "Electric Bill",
            amount = 300.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FEES,
            date = Date()
        )
        check(
            name = "Should Fail To Edit When Transaction ID Does Not Exist ",
            result = financeTrackerManager.editTransaction(transactionWithNonExistentID),
            expectedResult = false
        )

        // successful transaction : reflects changes in balance
        val successfulTransactionToUpdateBalance = Transaction(
            id = 21,
            title = "Salary Bonus",
            amount = 750.0,
            transActionType = TransactionType.INCOME,
            category = Category.SALARY,
            date = Date()
        )
        check(
            name = "Should Update Account Balance After Successful Edit",
            result = financeTrackerManager.editTransaction(successfulTransactionToUpdateBalance),
            expectedResult = true
        )

        // successful transaction : reflects changes in monthly summary
        val successfulTransactionToUpdateMonthlySummary = Transaction(
            id = 21,
            title = "Freelancer Project",
            amount = 1000.0,
            transActionType = TransactionType.INCOME,
            category = Category.SALARY,
            date = Date()
        )
        check(
            name = "Should Update Monthly Summary After Successful Edit",
            result = financeTrackerManager.editTransaction(successfulTransactionToUpdateMonthlySummary),
            expectedResult = true
        )

        // unsuccessful transaction : failed to make transaction when title is only spaces
        val unsuccessfulTransactionWithWhiteSpaceOnly = Transaction(
            id = 20,
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

        val transactionWithNegativeAmount = Transaction(
            id = 9,
            title = "Freelance Salary",
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

        val transactionWithAmountEqualZero = Transaction(
            id = 10,
            title = "Snacks",
            amount = 0.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )
        check(
            name = "Should Fail To Edit When Amount EquaIs Zero\n ",
            result = financeTrackerManager.editTransaction(transactionWithAmountEqualZero),
            expectedResult = false
        )

        val transactionWithEmptyDescription = Transaction(
            id = 7,
            title = "",
            amount = 500.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FEES,
            date = Date()
        )
        check(
            name = "Should Fail To enter empty description ",
            result = financeTrackerManager.editTransaction(transactionWithEmptyDescription),
            expectedResult = false
        )

        val transactionWithAmountOutOFRange =Transaction(
            id = 9,
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
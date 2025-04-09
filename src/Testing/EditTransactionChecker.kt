package Testing

import core.FinanceTrackerManager
import models.Category
import models.Transaction
import models.TransactionType
import java.util.*

class EditTransactionChecker(
    private val financeTrackerManager: FinanceTrackerManager
) {
    fun checkEdit(){
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

    }


    private fun <T>check(
        name: String,
        result: T,
        expectedResult : T
    ){
        if (result == expectedResult) {
            println("\u001B[32mSuccess: $name")
        }
        else {
            println("\u001b[31mFailure: $name (Expected $expectedResult but found $result)")
        }
    }

}
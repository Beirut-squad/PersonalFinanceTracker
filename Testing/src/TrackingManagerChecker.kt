import core.FinanceTrackerManager
import models.Category
import models.Transaction
import models.TransactionType
import java.util.*

class TrackingManagerChecker(
    private val financeTrackerManager: FinanceTrackerManager
) {

    fun editWithValidData(){
        val transaction = Transaction(
            id = 0,
            title = "t-shirt",
            amount = 60.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.SHOPPING,
            date = Date()
        )
        check(
            name ="Edit with Valid Data",
            result = financeTrackerManager.EditTransaction(transaction),
            expectedResult = true
        )
    }
    fun editPartialUpdate(){
        val transaction = Transaction(
            id = 0,
            title = "t-shirt",
            amount = 100.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.SHOPPING,
            date = Date()
        )
        check(
            name = "Partial Update",
            result = financeTrackerManager.EditTransaction(transaction),
            expectedResult = true
        )
    }
    fun EditwithNonExistentTransiction(){
        val transaction = Transaction(
            id = -5,
            title = "t-shirt",
            amount = 100.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.SHOPPING,
            date = Date()
        )
        check(
            name = "Edit with Non-Existent transiction",
            result = financeTrackerManager.EditTransaction(transaction),
            expectedResult = false
        )
    }

    fun runEditTest(){
        editWithValidData()
        editPartialUpdate()
        EditwithNonExistentTransiction()
    }

    fun runAllTests() {
        runEditTest()
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
import core.FinanceTrackerManager
import models.Category
import models.Transaction
import models.TransactionType
import java.util.*

class TrackingManagerChecker(
    private val financeTrackerManager: FinanceTrackerManager
) {

    fun addValidTest() {
        val transaction = Transaction(
            id = 0,
            title = "College fees",
            amount = 500.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FEES,
            date = Date()
        )

        check(
            name = "Valid test",
            result = financeTrackerManager.addTransaction(transaction),
            expectedResult = true
        )
    }

    fun addRepeatedId() {
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
            name = "Invaild - Repeated Id",
            result = financeTrackerManager.addTransaction(transaction2),
            expectedResult = false
        )
    }

    fun addEmptyTitleTest() {
        val transaction = Transaction(
            id = 12,
            title = "",
            amount = 1000.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )
        check(
            name = "Invalid - empty title",
            result = financeTrackerManager.addTransaction(transaction),
            expectedResult = false
        )
    }

    fun runAddTests() {
        addValidTest()
        addRepeatedId()
        addEmptyTitleTest()
    }

    fun runAllTests() {
        runAddTests()
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
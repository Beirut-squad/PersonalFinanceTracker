package Testing

import core.FinanceTrackerManager
import models.Category
import models.Transaction
import models.TransactionType
import java.util.*

class ViewMonthlySummeryChecker(
    private val financeTrackerManager: FinanceTrackerManager
) {
    fun runAllMonthlySummaryChecks() {
        financeTrackerManager.clearTransactions()

        val calendar = Calendar.getInstance()

        // Transaction in March 2024
        calendar.set(2024, Calendar.MARCH, 5)
        val transaction1 = Transaction(
            id = 1,
            title = "Rent",
            amount = 800.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = calendar.time
        )

        // Transaction in March 2024
        calendar.set(2024, Calendar.MARCH, 15)
        val transaction2 = Transaction(
            id = 2,
            title = "Salary",
            amount = 3000.0,
            transActionType = TransactionType.INCOME,
            category = Category.SALARY,
            date = calendar.time
        )

        // Transaction in April 2024
        calendar.set(2024, Calendar.APRIL, 10)
        val transaction3 = Transaction(
            id = 3,
            title = "Groceries",
            amount = 150.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = calendar.time
        )

        // Add all transactions
        financeTrackerManager.addTransaction(transaction1)
        financeTrackerManager.addTransaction(transaction2)
        financeTrackerManager.addTransaction(transaction3)

        val validDate = financeTrackerManager.viewMonthlySummery(3, 2024) // March is month 3
        val invalidDate = financeTrackerManager.viewMonthlySummery(10, 2026)
        val invalidMonth = financeTrackerManager.viewMonthlySummery(25, 2026)
        val invalidYear = financeTrackerManager.viewMonthlySummery(3, 102)

        check(
            name = "Summary should return only March 2024 transactions",
            result = validDate.map { it.id }.sorted(),
            correctResult = listOf(1, 2)
        )

        check(
            name = "When the date is wrong , should return empty list ",
            result = invalidDate.map { it.id }.sorted(),
            correctResult = listOf()
        )

        check(
            name = "When the month is wrong , should return empty list ",
            result = invalidMonth.map { it.id }.sorted(),
            correctResult = listOf()
        )

        check(
            name = "When the year is wrong , should return empty list ",
            result = invalidYear.map { it.id }.sorted(),
            correctResult = listOf()
        )



    }

    private fun <T> check(
        name: String,
        result: T,
        correctResult: T
    ) {
        if (result == correctResult) {
            println("Success: $name")
        } else {
            println("Failed :  $name")
        }
    }
}


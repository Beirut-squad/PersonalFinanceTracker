package test

import core.FinanceTrackerManager
import models.Category
import models.Transaction
import models.TransactionType
import java.util.*

class GetMonthlySummeryChecker(
    private val financeTrackerManager: FinanceTrackerManager,
    private val printChecks: Checker = Checker()
) {

    fun runAllMonthlySummaryChecks() {
        val transactions : MutableList<Transaction> = mutableListOf()
        val calendar = Calendar.getInstance()

        // Transaction in March 2024
        calendar.set(2024, Calendar.MARCH, 5)
        val transaction1 = Transaction(
            id = 1,
            title = "Rent",
            amount = 800.0,
            transactionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = calendar.time
        )
        transactions.add(transaction1)

        // Transaction in March 2024
        calendar.set(2024, Calendar.MARCH, 15)
        val transaction2 = Transaction(
            id = 2,
            title = "Salary",
            amount = 3000.0,
            transactionType = TransactionType.INCOME,
            category = Category.SALARY,
            date = calendar.time
        )
        transactions.add(transaction2)

        // Transaction in April 2024
        calendar.set(2024, Calendar.APRIL, 10)
        val transaction3 = Transaction(
            id = 3,
            title = "Groceries",
            amount = 150.0,
            transactionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = calendar.time
        )
        transactions.add(transaction3)

        val validDate = financeTrackerManager.viewMonthlySummery(3, 2024) // March is month 3
        val invalidDate = financeTrackerManager.viewMonthlySummery(10, 2026)
        val invalidMonth = financeTrackerManager.viewMonthlySummery(25, 2026)
        val invalidYear = financeTrackerManager.viewMonthlySummery(3, 102)

        printChecks.check(
            name = "Summary should return only March 2024 transactions",
            result = validDate,
            expectedResult = listOf(transaction1, transaction2)
        )

        printChecks.check(
            name = "When the date is wrong , should return empty list ",
            result = invalidDate,
            expectedResult = listOf()
        )

        printChecks.check(
            name = "When the month is wrong , should return empty list ",
            result = invalidMonth,
            expectedResult = listOf()
        )

        printChecks.check(
            name = "When the year is wrong , should return empty list ",
            result = invalidYear,
            expectedResult = listOf()
        )



    }
}
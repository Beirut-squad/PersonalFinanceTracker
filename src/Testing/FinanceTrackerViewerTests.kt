import core.DefaultFinanceTrackerViewer
import datasource.FinanceTrackerDataSource
import models.Category
import models.TotalTransactions
import models.Transaction
import models.TransactionType
import java.util.Date

class FinanceTrackerViewerTests(private val dataSource: FinanceTrackerDataSource) {

    fun checkBalanceReport() {
        checker(
            testCase = "should return empty transactions when no transactions exist",
            result = DefaultFinanceTrackerViewer(dataSource).balanceReport(),
            expected = TotalTransactions(transactions = emptyList())
        )

        val mockTransactions = listOf(
            Transaction(
                id = 1,
                title = "buy food",
                amount = 30.0,
                transactionType = TransactionType.Expenses,
                category = Category.FOOD,
                date = Date()
            ), Transaction(
                id = 2,
                title = "monthly salary",
                amount = 3000.0,
                transactionType = TransactionType.Income,
                category = Category.SALARY,
                date = Date()
            ), Transaction(
                id = 3,
                title = " ",
                amount = 100.0,
                transactionType = TransactionType.Expenses,
                category = Category.SHOPPING,
                date = Date()
            )
        )
        mockTransactions.forEach { dataSource.addTransactions(it) }

        checker(
            testCase = "should correctly calculate total expenses from transactions",
            result = DefaultFinanceTrackerViewer(dataSource).balanceReport(),
            expected = TotalTransactions(transactions = mockTransactions)
        )

        checker(
            testCase = "should correctly calculate total income from transactions",
            result = DefaultFinanceTrackerViewer(dataSource).balanceReport(),
            expected = TotalTransactions(transactions = mockTransactions)
        )

        checker(
            testCase = "should correctly calculate net balance (income - expenses)",
            result = DefaultFinanceTrackerViewer(dataSource).balanceReport(),
            expected = TotalTransactions(transactions = mockTransactions)
        )
    }

}

private fun FinanceTrackerViewerTests.checker(
    testCase: String, result: TotalTransactions, expected: TotalTransactions
) {
    println("Test Case: $testCase")
    println("Expected: $expected")
    println("Actual: $result")
    println(
        if (result.transactions.size == expected.transactions.size && result.incomeBalance == expected.incomeBalance && result.expensesBalance == expected.expensesBalance && result.totalBalance == expected.totalBalance) "✅ PASSED" else "❌ FAILED"
    )
    println()
}
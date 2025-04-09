package models

import core.DefaultFinanceTrackerViewer

class FinanceTrackerViewerTests {

    fun testsBalanceReport() {
        checker(
            testCase = "when Dont exists transaction",
            result = DefaultFinanceTrackerViewer().balanceReport(),
            expected = TotalTransactions(transactions = emptyList())
        )
    }

}

private fun FinanceTrackerViewerTests.checker(
    testCase: String,
    result: TotalTransactions,
    expected: TotalTransactions
) {
    println("Test Case: $testCase")
    println("Expected: $expected, Actual: $result")
    println(
        if (result.transactions.size == expected.transactions.size &&
            result.incomeBalance == expected.incomeBalance &&
            result.expensesBalance == expected.expensesBalance &&
            result.totalBalance == expected.totalBalance
        ) "✅ PASSED" else "❌ FAILED"
    )
    println()
}
package test

import core.FinanceTrackerManager
import models.Category
import models.TotalTransactions
import models.Transaction
import models.TransactionType
import java.util.Date

class BalanceReportChecker (
    private val financeTrackerManager: FinanceTrackerManager,
    private val checker: Checker = Checker()
) {

    fun checkBalanceReport() {
        checker.check(
            name = "should return empty transactions when no transactions exist",
            result = financeTrackerManager.getBalanceReport(),
            expectedResult = TotalTransactions(transactions = emptyList())
        )
        financeTrackerManager.clearTransactions()
        val mockTransactions = listOf(
            Transaction(
                id = 1,
                title = "buy food",
                amount = 30.0,
                transactionType = TransactionType.EXPENSE,
                category = Category.FOOD,
                date = Date()
            ), Transaction(
                id = 2,
                title = "monthly salary",
                amount = 3000.0,
                transactionType = TransactionType.INCOME,
                category = Category.SALARY,
                date = Date()
            ), Transaction(
                id = 3,
                title = " ",
                amount = 100.0,
                transactionType = TransactionType.EXPENSE,
                category = Category.SHOPPING,
                date = Date()
            )
        )
        mockTransactions.forEach { financeTrackerManager.addTransaction(it) }

        checker.check(
            name = "should correctly calculate total expenses from transactions",
            result = financeTrackerManager.getBalanceReport(),
            expectedResult = TotalTransactions(transactions = mockTransactions)
        )

        checker.check(
            name = "should correctly calculate total income from transactions",
            result = financeTrackerManager.getBalanceReport(),
            expectedResult = TotalTransactions(transactions = mockTransactions)
        )

        checker.check(
            name = "should correctly calculate net balance (income - expenses)",
            result = financeTrackerManager.getBalanceReport(),
            expectedResult = TotalTransactions(transactions = mockTransactions)
        )
    }

}



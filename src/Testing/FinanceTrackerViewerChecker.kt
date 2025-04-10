import core.DefaultFinanceTrackerViewer
import datasource.FinanceTrackerDataSource
import models.Category
import models.TotalTransactions
import models.Transaction
import models.TransactionType
import testing.Checker
import java.util.Date

class FinanceTrackerViewerChecker (
    private val dataSource: FinanceTrackerDataSource, 
    val printChecks:Checker = Checker()) {

    fun checkBalanceReport() {
        printChecks.check(
            name = "should return empty transactions when no transactions exist",
            result = DefaultFinanceTrackerViewer(dataSource).balanceReport(),
            expectedResult = TotalTransactions(transactions = emptyList())
        )

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
        mockTransactions.forEach { dataSource.addTransactions(it) }

        printChecks.check(
            name = "should correctly calculate total expenses from transactions",
            result = DefaultFinanceTrackerViewer(dataSource).balanceReport(),
            expectedResult = TotalTransactions(transactions = mockTransactions)
        )

        printChecks.check(
            name = "should correctly calculate total income from transactions",
            result = DefaultFinanceTrackerViewer(dataSource).balanceReport(),
            expectedResult = TotalTransactions(transactions = mockTransactions)
        )

        printChecks.check(
            name = "should correctly calculate net balance (income - expenses)",
            result = DefaultFinanceTrackerViewer(dataSource).balanceReport(),
            expectedResult = TotalTransactions(transactions = mockTransactions)
        )
    }

}



import Testing.FakeDataSource

fun main() {
    val financeTrackerViewerTests = FinanceTrackerViewerTests(dataSource = FakeDataSource())
    financeTrackerViewerTests.checkBalanceReport()
}
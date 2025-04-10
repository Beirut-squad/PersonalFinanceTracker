import testing.DeleteTransactionChecker
import testing.EditTransactionChecker
import core.FinanceTrackerManagerImpl

fun main() {
    val addChecker = AddTransactionChecker(
        financeTrackerManager = FinanceTrackerManagerImpl()
    )
    val deleteChecker = DeleteTransactionChecker(
        financeTrackerManager = FinanceTrackerManagerImpl()
    )
    val editChecker = EditTransactionChecker(
        financeTrackerManager = FinanceTrackerManagerImpl()
    )

    addChecker.runAddTests()
    deleteChecker.runAllDeleteChecker()
    editChecker.editTransactionCheck()
}
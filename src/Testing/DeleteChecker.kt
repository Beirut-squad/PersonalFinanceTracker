package Testing

import core.FinanceTrackerManager
import models.Category
import models.Transaction
import models.TransactionType
import java.util.*
class DeleteChecker (
    private val financeTrackerManager: FinanceTrackerManager
) {
    fun runAllDeleteChecker() {
        val value1 = Transaction(
            id = 0,
            title = "AboAnas",
            amount = 500.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )
        val value2 = Transaction(
            id = -1,
            title = "AboAnas",
            amount = 500.0,
            transActionType = TransactionType.EXPENSE,
            category = Category.FOOD,
            date = Date()
        )
        financeTrackerManager.deleteTransaction(value1.id)
        //Delete element in list, it will delete successfully
        check(
            name = "Delete element in the list",
            result = financeTrackerManager.deleteTransaction(value1.id),
            correctResult = true
        )
        //Delete element not in the list, we can't delete element not found
        check(
            name = "Delete element not in the list",
            result = financeTrackerManager.deleteTransaction(0),
            correctResult = false
        )
        //Delete element with incorrect id, we can't delete element not found
        check(
            name = "Element with wrong id",
            result = financeTrackerManager.deleteTransaction(-1),
            correctResult = false
        )

    }

    fun check( name: String , result: Boolean, correctResult: Boolean)
    {
        if (result == correctResult)
        {  println(" true - $name")}
        else
        { println(" false - $name")}
    }
}
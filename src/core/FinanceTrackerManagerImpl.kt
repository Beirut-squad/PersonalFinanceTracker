package core

import datasource.FinanceTrackerDataSource
import models.TotalTransactions
import models.Transaction
import java.util.*

class FinanceTrackerManagerImpl(
    private val dataSource: FinanceTrackerDataSource,
    private val validator: FinanceTrackerValidator
): FinanceTrackerManager {
    override fun addTransaction(transaction: Transaction): Boolean {
        if (!validator.validateTransaction(transaction)) return false

        if (dataSource.getTransactions().any { it.id == transaction.id }){
            return false
        }

        dataSource.addTransactions(transaction)
        return true
    }

    override fun deleteTransaction(id: Int): Boolean {
        if (dataSource.getTransactions().isEmpty()) {
            return false
        }

        val deletedTransaction = dataSource.getTransactions().find { id == it.id }

        if (deletedTransaction == null) {
            return false
        }

        dataSource.deleteTransactions(deletedTransaction)
        return true
    }

    override fun clearTransactions() {
        dataSource.clear()
    }

    override fun editTransaction(transaction: Transaction): Boolean {
        if (!validator.validateTransaction(transaction)) return false

        for (existingTransaction in dataSource.getTransactions()) {
            if (transaction.id == existingTransaction.id) {
                dataSource.editTransactions(transaction)
                return true
            }
        }

        return false
    }

    // Viewer
    override fun getBalanceReport(): TotalTransactions {
        return TotalTransactions(transactions = dataSource.getTransactions())
    }

    override fun getMonthlySummery(month: Int, year: Int): List<Transaction> {
        return dataSource.getTransactions().filter { transaction ->
            val cal = Calendar.getInstance()
            cal.time = transaction.date
            val transactionMonth = cal.get(Calendar.MONTH) + 1
            val transactionYear = cal.get(Calendar.YEAR)

            transactionMonth == month && transactionYear == year
        }
    }

}
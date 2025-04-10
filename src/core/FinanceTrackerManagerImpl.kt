package core

import datasource.FinanceTrackerDataSource
import datasource.InMemoryDataSource
import models.Category
import models.Transaction
import models.TransactionType
import java.util.*

class FinanceTrackerManagerImpl(
    private val ftDataSource: FinanceTrackerDataSource,
    private val validator: FinanceTrackerValidator
): FinanceTrackerManager {
    override fun addTransaction(transaction: Transaction): Boolean {
        if (ftDataSource.transactions.any { it.id == transaction.id }){
            return false
        }else if(transaction.id < 0){
            return false

        }else if (transaction.title.trim().isBlank()){
            return false
        }else if(transaction.amount <= 0.0){
            return false
        }



        ftDataSource.addTransactions(transaction)
        return true
    }

    override fun deleteTransaction(id: Int): Boolean {

        val transactions = ftDataSource.transactions

        if (id < 0) {
            return false
        }

        if (transactions.isEmpty()) {
            return false
        }

        val deletedTransaction = transactions.find { id == it.id }

        if (deletedTransaction == null) {
            return false
        }

        return transactions.remove(deletedTransaction)
    }

    override fun clearTransactions(): Boolean {
        return true
    }

    override fun editTransaction(transaction: Transaction): Boolean {
        if (!validator.validateTransaction(transaction)) return false

        for (existingTransaction in ftDataSource.transactions) {
            if (transaction.id == existingTransaction.id) {
                ftDataSource.editTransactions(transaction)
                return true
            }
        }

        return false
    }

    override fun viewMonthlySummery(month: Int, year: Int): List<Transaction> {
        return ftDataSource.transactions.filter { transaction ->
            val cal = Calendar.getInstance()
            cal.time = transaction.date
            val transactionMonth = cal.get(Calendar.MONTH) + 1
            val transactionYear = cal.get(Calendar.YEAR)

            transactionMonth == month && transactionYear == year
        }
    }
}
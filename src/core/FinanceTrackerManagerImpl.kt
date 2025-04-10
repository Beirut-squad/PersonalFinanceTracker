package core

import datasource.FinanceTrackerDataSource
import datasource.InMemoryDataSource
import models.Category
import models.Transaction
import models.TransactionType
import java.util.*

class FinanceTrackerManagerImpl(private val ftDataSource: FinanceTrackerDataSource): FinanceTrackerManager {


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
        return false
    }

    override fun clearTransactions(): Boolean {
        return true
    }

    override fun editTransaction(transaction: Transaction): Boolean {
        return false
    }
}
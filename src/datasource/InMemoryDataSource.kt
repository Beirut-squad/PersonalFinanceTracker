package datasource

import models.Transaction

class InMemoryDataSource : FinanceTrackerDataSource {
    private val transactions: MutableList<Transaction> = mutableListOf()

    override fun addTransactions(transaction: Transaction) {
        this.transactions.add(transaction)

    }

    override fun editTransactions(transaction: Transaction) {
        for (exitingTransaction in transactions){
            if (exitingTransaction.id == transaction.id){
                transactions.remove(exitingTransaction)
                transactions.add(transaction)
                break
            }
        }
    }

    override fun getTransactions(): List<Transaction> {
        return transactions
    }

    override fun deleteTransactions(transaction: Transaction) {
        transactions.remove(transaction)
    }

    override fun clear() {
        transactions.clear()
    }
}
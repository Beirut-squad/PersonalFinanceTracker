package datasource

import models.Transaction

class InMemoryDataSource : FinanceTrackerDataSource {
    override var transactions: MutableList<Transaction> = emptyList<Transaction>().toMutableList()

    override fun addTransactions(transaction: Transaction) {
        this.transactions.add(transaction)

    }

    override fun editTransactions(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun viewTransactions(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun deleteTransactions(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }
}
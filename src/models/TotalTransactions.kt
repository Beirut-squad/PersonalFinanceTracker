package models

data class TotalTransactions(
    var transactions: List<Transaction>,
) {
    val incomeBalance: Double
        get() {
            val incomeTransactions = transactions.filter { it.transactionType == TransactionType.INCOME }
            return incomeTransactions.sumOf { it.amount }
        }

    val expensesBalance: Double
        get() {
            val expensesTransactions = transactions.filter { it.transactionType == TransactionType.EXPENSE }
            return expensesTransactions.sumOf { it.amount }
        }

    val totalBalance: Double
        get() = incomeBalance - expensesBalance

}
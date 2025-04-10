package models

data class TotalTransactions(
    var transactions: List<Transaction>,
) {

    val incomeBalance: Double = 0.0
        get() {
            val incomeTransactions = transactions.filter { it.transactionType == TransactionType.INCOME }
            return incomeTransactions.sumOf { it.amount }
        }
    val expensesBalance: Double = 0.0
        get() {
            val expensesTransactions = transactions.filter { it.transactionType == TransactionType.EXPENSE }
            return expensesTransactions.sumOf { it.amount }
        }
    val totalBalance: Double = 0.0
        get() = incomeBalance - expensesBalance

}
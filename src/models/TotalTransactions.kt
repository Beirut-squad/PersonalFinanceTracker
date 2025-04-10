package models

data class TotalTransactions(
    var transactions: List<Transaction>,
) {

    var incomeBalance: Double = 0.0
        get() {
            val incomeTransactions = transactions.filter { it.transactionType == TransactionType.INCOME }
            return incomeTransactions.sumOf { it.amount }
        }
    var expensesBalance: Double = 0.0
        get() {
            val expensesTransactions = transactions.filter { it.transactionType == TransactionType.EXPENSE }
            return expensesTransactions.sumOf { it.amount }
        }
    var totalBalance: Double = 0.0
        get() = incomeBalance - expensesBalance

}
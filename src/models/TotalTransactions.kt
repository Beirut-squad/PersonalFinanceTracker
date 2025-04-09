package models

class TotalTransactions(
    var transactions: List<Transaction>,
) {

    var incomeBalance: Double = 0.0
        get() {
            val incomeTransactions = transactions.filter { it.transactionType == TransactionType.Income }
            return incomeTransactions.sumOf { it.amount }
        }
    var expensesBalance: Double = 0.0
        get() {
            val expensesTransactions = transactions.filter { it.transactionType == TransactionType.Expenses }
            return expensesTransactions.sumOf { it.amount }
        }
    var totalBalance: Double = 0.0
        get() = incomeBalance + expensesBalance

}
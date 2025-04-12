package ui

import Colors
import core.FinanceTrackerManager
import models.Category
import models.Transaction
import models.TransactionType
import java.time.LocalDate
import java.util.*


class FinanceTrackerCLI(private val manager: FinanceTrackerManager) {
    private var running = true
    private var check:String? = null

    fun run() {
        while (running) {
            println("Finance Tracker")
            println("\t1. Manage Transactions")
            println("\t2. View Monthly Summary")
            println("\t3. View Balance Report")
            println("\t4. Exit")
            print("- Choose an option (1, 4): ")

            when (readlnOrNull()?.toIntOrNull()) {
                1 -> transactionsMenu()
                2 -> viewMonthlySummary()
                3 -> viewBalanceReport()
                4 -> {
                    println("Goodbye!")
                    running = false
                }

                else -> {
                    Colors().printRedColorText("Invalid choice.")
                }
            }
        }
    }

    private fun transactionsMenu() {
        while (true) {
            println("Manage your transactions: ")
            println("\t1. Add Transaction")
            println("\t2. Edit Transaction")
            println("\t3. Delete Transaction")
            println("\t4. View Transactions")
            println("\t5. Exit to main menu")
            print("- Choose an option (1, 5): ")

            when (readlnOrNull()?.toIntOrNull()) {
                1 -> addTransaction()
                2 -> editTransaction()
                3 -> deleteTransaction()
                4 -> viewTransactions()
                5 -> {
                    break
                }
                else -> Colors().printRedColorText("Invalid choice.")
            }
        }
    }

    private fun addTransaction() {
        while (true) {
            println("Enter the details of your transaction")
            print("\tEnter title: ")
            val title = readlnOrNull().orEmpty()

            print("\tEnter amount: ")
            val amount = readlnOrNull()?.toDoubleOrNull() ?: 0.0
            val type = getValidTransactionType()
            val category = getValidCategory()
            val transaction = Transaction(
                title = title,
                amount = amount,
                transactionType = type,
                category = category
            )

            if (manager.addTransaction(transaction)) {
                Colors().printGreenColorText("Transactions Added")
                Colors().blueStars()
                break
            } else {
                Colors().printRedColorText("please try again , there is an error in the data")
            }
        }

    }

    private fun getValidTransactionType(): TransactionType {
        while (true) {
            println("\tChoose transaction type\n\t\t1: Income\n\t\t2: Expense")
            print("- Choose an option (1, 2): ")
            when (readlnOrNull()?.toIntOrNull()) {
                1 -> return TransactionType.INCOME
                2 -> return TransactionType.EXPENSE
                else -> Colors().printRedColorText("Invalid input. Please enter 1 or 2.")
            }
        }
    }

    private fun getValidCategory(): Category {
        while (true) {
            println("\tChoose category")
            for (categoryInd in 1..Category.entries.size) {
                val category = Category.entries[categoryInd - 1]
                Colors().printPurpleColorText(
                    "\t\t${categoryInd}: " +
                            category.toString().lowercase().replaceFirstChar { it.uppercase() })
            }
            print("- Choose an option (a number from 1 to ${Category.entries.size}): ")
            when (val choice = readlnOrNull()?.toIntOrNull()) {
                in 1..Category.entries.size -> return Category.entries[(choice ?: 1) - 1]
                else -> Colors().printRedColorText(
                    "Invalid input. Please enter a number between 1 and " +
                            "${Category.entries.size}."
                )
            }
        }
    }

    private fun editTransaction() {
        val transactions = manager.getTransactions()
        while (true) {
            if (transactions.isEmpty()) {
                break
            }
            viewTransactions(transactions)
            println("\tChoose the transaction you want to edit")
            print("- Choose an option (a number from 1 to ${transactions.size}): ")
            when (val choice = readlnOrNull()?.toIntOrNull()) {
                in 1..transactions.size -> {
                    println("Edit your transaction")
                    var currentTransaction = transactions[(choice ?: 1) - 1]
                    currentTransaction = editTransactionTitle(currentTransaction)
                    currentTransaction = editTransactionAmount(currentTransaction)
                    currentTransaction = editTransactionType(currentTransaction)
                    currentTransaction = editTransactionCategory(currentTransaction)
                    if (manager.editTransaction(currentTransaction)) {
                        Colors().printGreenColorText("Transaction edited")
                        Colors().blueStars()
                        break
                    } else {
                        Colors().printRedColorText("please try again , there is an error in the data")
                        Colors().blueStars()
                    }
                }
                else -> {
                    Colors().printRedColorText(
                        "Invalid input. Please enter a number between 1 and " +
                                "${transactions.size}."
                    )
                }
            }
        }
    }

    private fun deleteTransaction() {
        val transactions = manager.getTransactions()
        if (transactions.isEmpty()) {
            Colors().printRedColorText("No transactions available to delete.")
            return
        }
        while (true) {
            viewTransactions(transactions)
            println("\tChoose the transaction you want to delete")
            print("- Choose an option (a number from 1 to ${transactions.size}): ")
            when (val choice = readlnOrNull()?.toIntOrNull()) {
                in 1..transactions.size -> {
                    println("Delete your transaction")
                    val currentTransactionID = transactions[(choice ?: 1) - 1].id
                    if (manager.deleteTransaction(currentTransactionID)) {
                        Colors().printGreenColorText("Transaction deleted")
                        Colors().blueStars()
                        break
                    }
                }
                else -> Colors().printRedColorText(
                    "Invalid input. Please enter a number " +
                            "between 1 and ${transactions.size}."
                )
            }
        }
    }

    private fun viewTransactions(
        transactions: List<Transaction> = manager.getTransactions()
    ) {

        if (transactions.isEmpty()) {
            Colors().printRedColorText("You don't have any transactions.")
        }

        for (transactionInd in 1..transactions.size) {
            val transaction = transactions[transactionInd - 1]
            Colors().printPurpleColorText(
                "\t${transactionInd}: " +
                        " title: ${transaction.title}, amount: ${transaction.amount}, type: ${
                            transaction.transactionType.toString().lowercase().replaceFirstChar
                            { it.uppercase() }
                        }, category: ${
                            transaction.category.toString()
                                .lowercase().replaceFirstChar { it.uppercase() }
                        }," +
                        " date: ${transaction.date}"
            )
        }
    }

    private fun viewMonthlySummary() {
        print("Enter month (1-12): ")
        val month = readlnOrNull()?.toIntOrNull() ?: 0

        if (checkMonth(month)){
            print("Enter year: ")
            val year = readlnOrNull()?.toIntOrNull() ?: 0

            if (checkYear(year)) {
                viewTransactionsInMonth(month, year)
            } else {
                Colors().printRedColorText("Invalid year, or empty input.")
            }
        }else
            Colors().printRedColorText("Invalid month, or empty input.")
    }

    private fun viewBalanceReport() {
        val totalTransactions = manager.getBalanceReport()
        while (true) {
            viewTransactions(totalTransactions.transactions)
            if (totalTransactions.transactions.isEmpty()) {
                break
            }
            break
        }
        Colors().printGreenColorText("Show Total Balance report ")
        Colors().printGreenColorText(
            "Balance report is: " +
                    "${totalTransactions.totalBalance}\nIncome Balance report is: " +
                    "${totalTransactions.incomeBalance}\nExpenses Balance report is: " +
                    "${totalTransactions.expensesBalance}"
        )
        Colors().blueStars()
    }

    private fun checkMonth(month: Int): Boolean {
        return month in 1..12
    }


    private fun editTransactionTitle(currentTransaction: Transaction): Transaction{
        // Title
        print("Do you want to change the title? (Enter y if you want or anything else to skip): ")
        check = readlnOrNull()?.trim()
        // Simplified condition - only proceed if input is exactly "y" or "Y"
        if (check.equals("y", ignoreCase = true)) {
            print("\tEnter title: ")
            val title = readlnOrNull()?.trim()?.takeIf { it.isNotEmpty() } ?: run {
                Colors().printRedColorText("Title cannot be empty. Keeping previous title.")
                currentTransaction.title
            }
            return currentTransaction.copy(
                title = title,
                date = Date()
            )
        }
        else return currentTransaction
    }

    private fun editTransactionAmount(currentTransaction: Transaction): Transaction{
        print("Do you want to change the amount? (Enter y if you want or anything else to skip): ")
        check = readlnOrNull()?.trim()

        if (check.equals("y", ignoreCase = true)) {
            print("\tEnter amount: ")
            val amount = readlnOrNull()?.toDoubleOrNull() ?: run {
                Colors().printRedColorText("Invalid amount. Keeping previous amount.")
                currentTransaction.amount
            }
            return currentTransaction.copy(
                amount = amount,
                date = Date()
            )
        }else return currentTransaction
    }

    private fun editTransactionType(currentTransaction: Transaction): Transaction{
        print("Do you want to change the type? (Enter y if you want " +
                "or anything else to skip): ")
        check = readlnOrNull()?.trim()

        if (check.equals("y", ignoreCase = true)) {
            val type = getValidTransactionType()
            return currentTransaction.copy(
                transactionType = type,
                date = Date()
            )
        }else return currentTransaction
    }

    private fun editTransactionCategory(currentTransaction: Transaction): Transaction{
        print("Do you want to change the category? (Enter y if you want or " +
                "anything else to skip): ")
        check = readlnOrNull()?.trim()

        if (check.equals("y", ignoreCase = true)) {
            val category = getValidCategory()
            return currentTransaction.copy(
                category = category,
                date = Date()
            )
        }else return currentTransaction
    }

    private fun checkYear(year: Int): Boolean {
        val currentYear = LocalDate.now().year
        return year in 1..currentYear
    }

    private fun viewTransactionsInMonth(month: Int, year: Int) {
        val summary = manager.getMonthlySummery(month, year)

        if (summary.isEmpty()) {
            Colors().printRedColorText("No transactions found for $month/$year.")
            return
        }

        summary.forEachIndexed { index, transaction ->
            Colors().printPurpleColorText(
                "${index + 1} -> Title: ${transaction.title}, " +
                        "Amount: ${transaction.amount}, " +
                        "Type: ${transaction.transactionType.name
                            .lowercase().replaceFirstChar { it.uppercase() }}, " +
                        "Category: ${transaction.category.name.lowercase()
                            .replaceFirstChar { it.uppercase() }}, " +
                        "Date: ${transaction.date}"
            )
        }
    }

}


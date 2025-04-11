package ui

import Colors
import core.FinanceTrackerManager
import models.Category
import models.Transaction
import models.TransactionType
import java.awt.Color
import java.time.LocalDate
import java.util.*


class FinanceTrackerCLI(private val manager: FinanceTrackerManager) {
    private var running = true
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
                    println(Colors().redColorText("Invalid choice."))
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

                else -> println(Colors().redColorText("Invalid choice."))
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
                println(Colors().greenColorText("Transactions Added"))
                println(Colors().blueStars())
                break
            } else {
                println(Colors().redColorText("please try again , there is an error in the data"))
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
                else -> println(Colors().redColorText("Invalid input. Please enter 1 or 2."))
            }
        }
    }

    private fun getValidCategory(): Category {
        while (true) {
            println("\tChoose category")
            for (categoryInd in 1..Category.entries.size) {
                val category = Category.entries[categoryInd - 1]
                println("\t\t${categoryInd}: ${category.toString().lowercase().replaceFirstChar { it.uppercase() }}")
            }
            print("- Choose an option (a number from 1 to ${Category.entries.size}): ")
            when (val choice = readlnOrNull()?.toIntOrNull()) {
                in 1..Category.entries.size -> return Category.entries[(choice ?: 1) - 1]
                else -> println(Colors().redColorText("Invalid input. Please enter a number between 1 and ${Category.entries.size}."))
            }
        }
    }

    private fun editTransaction() {
        while (true) {
            val transactions = viewTransactions()
            if (transactions.isEmpty()) {
                break
            }
            println("\tChoose the transaction you want to edit")
            print("- Choose an option (a number from 1 to ${transactions.size}): ")
            when (val choice = readlnOrNull()?.toIntOrNull()) {
                in 1..transactions.size -> {
                    println("Edit your transaction")
                    var currentTransaction = transactions[(choice ?: 1) - 1]

                    // Title
                    print("Do you want to change the title? (Enter y if you want or anything else to skip): ")
                    var check = readlnOrNull()?.trim()

                    // Simplified condition - only proceed if input is exactly "y" or "Y"
                    if (check.equals("y", ignoreCase = true)) {
                        print("\tEnter title: ")
                        val title = readlnOrNull()?.trim()?.takeIf { it.isNotEmpty() } ?: run {
                            println("\u001B[31mTitle cannot be empty. Keeping previous title.\u001B[0m")
                            currentTransaction.title
                        }
                        currentTransaction = currentTransaction.copy(
                            title = title,
                            date = Date()
                        )
                    }

                    // Amount
                    print("Do you want to change the amount? (Enter y if you want or anything else to skip): ")
                    check = readlnOrNull()?.trim()

                    if (check.equals("y", ignoreCase = true)) {
                        print("\tEnter amount: ")
                        val amount = readlnOrNull()?.toDoubleOrNull() ?: run {
                            println("\u001B[31mInvalid amount. Keeping previous amount.\u001B[0m")
                            currentTransaction.amount
                        }
                        currentTransaction = currentTransaction.copy(
                            amount = amount,
                            date = Date()
                        )
                    }

                    // Type
                    print("Do you want to change the type? (Enter y if you want or anything else to skip): ")
                    check = readlnOrNull()?.trim()

                    if (check.equals("y", ignoreCase = true)) {
                        val type = getValidTransactionType()
                        currentTransaction = currentTransaction.copy(
                            transactionType = type,
                            date = Date()
                        )
                    }

                    // Category
                    print("Do you want to change the category? (Enter y if you want or anything else to skip): ")
                    check = readlnOrNull()?.trim()

                    if (check.equals("y", ignoreCase = true)) {
                        val category = getValidCategory()
                        currentTransaction = currentTransaction.copy(
                            category = category,
                            date = Date()
                        )
                    }

                    if (manager.editTransaction(currentTransaction)) {
                        println(Colors().greenColorText("Transaction edited"))
                        println(Colors().blueStars())
                        break
                    } else {
                        println(Colors().redColorText("please try again , there is an error in the data"))
                        println(Colors().blueStars())
                    }
                }

                else -> {
                    println(Colors().redColorText("Invalid input. Please enter a number between 1 and ${transactions.size}."))
                }
            }
        }
    }

    private fun deleteTransaction() {
        while (true) {
            val transactions = viewTransactions()
            if (transactions.isEmpty()) {
                break
            }
            println("\tChoose the transaction you want to delete")
            print("- Choose an option (a number from 1 to ${transactions.size}): ")
            when (val choice = readlnOrNull()?.toIntOrNull()) {
                in 1..transactions.size -> {
                    println("Delete your transaction")
                    val currentTransactionID = transactions[(choice ?: 1) - 1].id
                    if (manager.deleteTransaction(currentTransactionID)) {
                        println(Colors().greenColorText("Transaction deleted"))
                        println(Colors().blueStars())
                        break
                    }
                }

                else -> println(Colors().redColorText("Invalid input. Please enter a number between 1 and ${transactions.size}."))
            }
        }
    }

    private fun viewTransactions(
        transactions: List<Transaction> = manager.getTransactions()
    ): List<Transaction> {

        if (transactions.isEmpty()) {
            println(Colors().redColorText("You don't have any transactions."))
            return emptyList()
        }

        for (transactionInd in 1..transactions.size) {
            val transaction = transactions[transactionInd - 1]

            println(
                Colors().purpleColorText("\t${transactionInd}: title: ${transaction.title}, amount: ${transaction.amount}, type: ${
                    transaction.transactionType.toString().lowercase().replaceFirstChar { it.uppercase() }
                }, category: ${
                    transaction.category.toString().lowercase().replaceFirstChar { it.uppercase() }
                }, date: ${transaction.date}"
            ))
        }

        return transactions
    }

    private fun viewMonthlySummary() {
        println("Enter the Month that you want to view summary: 1-12")
        val month = readlnOrNull().toString().toIntOrNull() ?: 0

        if (month != 0 && checkMonth(month)){
            println("Enter the Year that you want to view summary:")
            val year = readlnOrNull().toString().toIntOrNull() ?: 0

            if (year != 0 && year.toString().length == 4){
                val summary = manager.getMonthlySummery(month,year)
                if (month > (LocalDate.now().month.value) || year > LocalDate.now().year){
                    println("The month or year will come later.")
                }else{
                    if (summary.isNotEmpty()){
                        for (transactionInd in 1..summary.size) {
                            val transaction = summary[transactionInd - 1]
                            println(Colors().purpleColorText("${transactionInd}-> Title: ${transaction.title}, Amount: ${transaction.amount}, Type: ${
                                transaction.transactionType.toString().lowercase().replaceFirstChar { it.uppercase() }
                            }, Category: ${
                                transaction.category.toString().lowercase().replaceFirstChar { it.uppercase() }
                            }, Date: ${transaction.date}"))
                        }
                        println(Colors().blueStars())
                    }else
                        println(Colors().redColorText("You have no any transactions in this month"))
                }
            }else println(Colors().redColorText("Invalid Year"))
        }
        else println(Colors().redColorText("Invalid month"))
    }

    private fun viewBalanceReport() {
        val totalTransactions = manager.getBalanceReport()
        while (true) {
            val transactions = viewTransactions(totalTransactions.transactions)
            if (transactions.isEmpty()) {
                break
            }
            break
        }
        println(Colors().greenColorText("Show Total Balance report "))
        println(Colors().greenColorText("Balance report is: ${totalTransactions.totalBalance}\nIncome Balance report is: ${totalTransactions.incomeBalance}\nExpenses Balance report is: ${totalTransactions.expensesBalance}"))
        println(Colors().blueStars())
    }

    fun checkMonth(month:Int): Boolean{
        return month in 1..12
    }

}


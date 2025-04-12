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
    var check:String? = null

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
                    println(Colors().changeColorTextToRed("Invalid choice."))
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

                else -> println(Colors().changeColorTextToRed("Invalid choice."))
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
                println(Colors().changeColorTextToGreen("Transactions Added"))
                println(Colors().blueStars())
                break
            } else {
                println(Colors().changeColorTextToRed("please try again , there is an error in the data"))
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
                else -> println(Colors().changeColorTextToRed("Invalid input. Please enter 1 or 2."))
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
                else -> println(Colors().changeColorTextToRed("Invalid input. Please enter a number between 1 and ${Category.entries.size}."))
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

                    // edit title
                    currentTransaction = editTransactionTitle(currentTransaction)

                    // edit amount
                    currentTransaction = editTransactionAmount(currentTransaction)

                    // edit type
                    currentTransaction = editTransactionType(currentTransaction)

                    // edit category
                    currentTransaction = editTransactionCategory(currentTransaction)

                    if (manager.editTransaction(currentTransaction)) {
                        println(Colors().changeColorTextToGreen("Transaction edited"))
                        println(Colors().blueStars())
                        break
                    } else {
                        println(Colors().changeColorTextToRed("please try again , there is " +
                                "an error in the data"))
                        println(Colors().blueStars())
                    }
                }

                else -> {
                    println(Colors().changeColorTextToRed("Invalid input. Please enter a number " +
                            "between 1 and ${transactions.size}."))
                }
            }
        }
    }

    private fun deleteTransaction() {
        while (true) {
            val transactions = manager.getTransactions()
            if (transactions.isEmpty()) {
                break
            }
            viewTransactions(transactions)
            println("\tChoose the transaction you want to delete")
            print("- Choose an option (a number from 1 to ${transactions.size}): ")
            when (val choice = readlnOrNull()?.toIntOrNull()) {
                in 1..transactions.size -> {
                    println("Delete your transaction")
                    val currentTransactionID = transactions[(choice ?: 1) - 1].id
                    if (manager.deleteTransaction(currentTransactionID)) {
                        println(Colors().changeColorTextToGreen("Transaction deleted"))
                        println(Colors().blueStars())
                        break
                    }
                }

                else -> println(Colors().changeColorTextToRed("Invalid input. Please enter a number between 1 and ${transactions.size}."))
            }
        }
    }

    private fun viewTransactions(
        transactions: List<Transaction> = manager.getTransactions()
    ) {

        if (transactions.isEmpty()) {
            println(Colors().changeColorTextToRed("You don't have any transactions."))
        }

        for (transactionInd in 1..transactions.size) {
            val transaction = transactions[transactionInd - 1]

            println(
                Colors().changeColorTextToPurple("\t${transactionInd}: title: ${transaction.title}" +
                        ", amount: ${transaction.amount}, type: ${
                    transaction.transactionType.toString().lowercase().replaceFirstChar { it.uppercase() }
                }, category: ${
                    transaction.category.toString().lowercase().replaceFirstChar { it.uppercase() }
                }, date: ${transaction.date}"
            ))
        }
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
                    // run view monthly summary
                    runViewMonthlySummary(summary)
                }
            }else println(Colors().changeColorTextToRed("Invalid Year"))
        }
        else println(Colors().changeColorTextToRed("Invalid month"))
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
        println(Colors().changeColorTextToGreen("Show Total Balance report "))
        println(Colors().changeColorTextToGreen("Balance report is: ${totalTransactions.totalBalance}\nIncome Balance report is: ${totalTransactions.incomeBalance}\nExpenses Balance report is: ${totalTransactions.expensesBalance}"))
        println(Colors().blueStars())
    }

    fun checkMonth(month:Int): Boolean{
        return month in 1..12
    }

    fun editTransactionTitle(currentTransaction: Transaction): Transaction{
        // Title
        print("Do you want to change the title? (Enter y if you want or anything else to skip): ")
        check = readlnOrNull()?.trim()
        // Simplified condition - only proceed if input is exactly "y" or "Y"
        if (check.equals("y", ignoreCase = true)) {
            print("\tEnter title: ")
            val title = readlnOrNull()?.trim()?.takeIf { it.isNotEmpty() } ?: run {
                println("\u001B[31mTitle cannot be empty. Keeping previous title.\u001B[0m")
                currentTransaction.title
            }
            return currentTransaction.copy(
                title = title,
                date = Date()
            )
        }
        else return currentTransaction
    }

    fun editTransactionAmount(currentTransaction: Transaction): Transaction{
        print("Do you want to change the amount? (Enter y if you want or anything else to skip): ")
        check = readlnOrNull()?.trim()

        if (check.equals("y", ignoreCase = true)) {
            print("\tEnter amount: ")
            val amount = readlnOrNull()?.toDoubleOrNull() ?: run {
                println("\u001B[31mInvalid amount. Keeping previous amount.\u001B[0m")
                currentTransaction.amount
            }
            return currentTransaction.copy(
                amount = amount,
                date = Date()
            )
        }else return currentTransaction
    }

    fun editTransactionType(currentTransaction: Transaction): Transaction{
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

    fun editTransactionCategory(currentTransaction: Transaction): Transaction{
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

    fun runViewMonthlySummary(summary: List<Transaction>){
        if (summary.isNotEmpty()){
            for (transactionInd in 1..summary.size) {
                val transaction = summary[transactionInd - 1]
                println(Colors().changeColorTextToPurple("${transactionInd}-> Title: ${transaction.title}, Amount: ${transaction.amount}, Type: ${
                    transaction.transactionType.toString().lowercase().replaceFirstChar { it.uppercase() }
                }, Category: ${
                    transaction.category.toString().lowercase().replaceFirstChar { it.uppercase() }
                }, Date: ${transaction.date}"))
            }
            println(Colors().blueStars())
        }else
            println(Colors().changeColorTextToRed("You have no any transactions in this month"))
    }

}


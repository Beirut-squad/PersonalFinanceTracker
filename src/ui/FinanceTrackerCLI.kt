package ui

import core.FinanceTrackerManager
import models.Category
import models.Transaction
import models.TransactionType

class FinanceTrackerCLI(private val manager: FinanceTrackerManager)
 {
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
                 else -> println("\u001B[31mInvalid choice.\u001B[0m")
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
                 else -> println("\u001B[31mInvalid choice.\u001B[0m")
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
                 println("\u001B[32mTransaction added\u001B[0m")
                 break
             } else {
                 println("\u001B[31mplease try again , there is an error in the data\u001B[0m")
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
                 else -> println("\u001B[31mInvalid input. Please enter 1 or 2.\u001B[0m")
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
                 in 1..Category.entries.size -> return Category.entries[(choice?: 1) - 1]
                 else -> println("\u001B[31mInvalid input. Please enter a number between 1 and ${Category.entries.size}.\u001B[0m")
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
                     var currentTransaction = transactions[(choice?: 1) - 1]

                     // Title
                     println("Do you want to change the title? (y, n)")
                     var check = readlnOrNull()

                     if ((check?.lowercase()?.single() ?: 'n') == 'y') {
                         print("\tEnter title: ")
                         val title = readlnOrNull()?.trim()
                         currentTransaction = currentTransaction.copy(
                             title = title ?: ""
                         )
                     }

                     // Amount
                     println("Do you want to change the amount? (y, n)")
                     check = readlnOrNull()

                     if ((check?.lowercase()?.single() ?: 'n') == 'y') {
                         print("\tEnter amount: ")
                         val amount = readlnOrNull()?.toDoubleOrNull()
                         currentTransaction = currentTransaction.copy(
                             amount = amount ?: 0.0
                         )
                     }


                     // Type
                     println("Do you want to change the type? (y, n)")
                     check = readlnOrNull()

                     if ((check?.lowercase()?.single() ?: 'n') == 'y') {
                         print("\tEnter type: ")
                         val type = getValidTransactionType()
                         currentTransaction = currentTransaction.copy(
                             transactionType = type
                         )
                     }

                     // Category
                     println("Do you want to change the category? (y, n)")
                     check = readlnOrNull()

                     if ((check?.lowercase()?.single() ?: 'n') == 'y') {
                         val category = getValidCategory()
                         currentTransaction = currentTransaction.copy(
                             category = category
                         )
                     }

                     if (manager.editTransaction(currentTransaction)) {
                         println("\u001B[32mTransaction edited\u001B[0m")
                         break
                     } else {
                         println("\u001B[31mplease try again , there is an error in the data\u001B[0m")
                     }
                 }
                 else -> println("\u001B[31mInvalid input. Please enter a number between 1 and ${Category.entries.size}.\u001B[0m")
             }
         }
     }

     private fun deleteTransaction() {
         // TODO
     }

     private fun viewTransactions(): List<Transaction> {
         val transactions = manager.getTransactions()

         if (transactions.isEmpty()) {
             println("\t\u001B[31mYou don't have any transactions.\u001B[0m")
             return emptyList()
         }

         print("\u001B[32m")

         for (transactionInd in 1..transactions.size) {
             val transaction = transactions[transactionInd - 1]

             println("\t\t${transactionInd}: title: ${transaction.title}, amount: ${transaction.amount}, type: ${transaction.transactionType.toString().lowercase().replaceFirstChar { it.uppercase() }}, category: ${transaction.category.toString().lowercase().replaceFirstChar { it.uppercase() }}")
         }

         print("\u001B[0m")

         return transactions
     }

     private fun viewMonthlySummary() {
         // TODO
     }

     private fun viewBalanceReport() {
         // TODO
     }
 }
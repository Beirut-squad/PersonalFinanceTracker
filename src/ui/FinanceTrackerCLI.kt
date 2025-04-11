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
             println("--- Finance Tracker ---")
             println("1. Add Transaction")
             println("2. Exit")
             print("Choose an option: ")

             when (readLine()?.toIntOrNull()) {
                 1 -> addTransaction()
                 2 -> {
                     println("Goodbye!")
                     running = false
                 }
                 else -> println("Invalid choice.")
             }
         }
     }

     private fun addTransaction() {
         print("Enter title: ")
         val title = readLine().orEmpty()

         print("Enter amount: ")
         val amount = readLine()?.toDoubleOrNull() ?: 0.0

         val type = getValidTransactionType()

         val category = getValidCategory()

         val transaction = Transaction(
             title = title,
             amount = amount,
             transactionType = type,
             category = category
         )


         if (manager.addTransaction(transaction)){
             println("Transaction added")
             println("Goodbye!")
         }else{
             println("please try again , there is an error in the data")
         }

     }

     private fun getValidTransactionType(): TransactionType {
         while (true) {
             println("Choose transaction type (1: INCOME, 2: EXPENSE): ")
             when (readLine()?.toIntOrNull()) {
                 1 -> return TransactionType.INCOME
                 2 -> return TransactionType.EXPENSE
                 else -> println("Invalid input. Please enter 1 or 2.")
             }
         }
     }

     private fun getValidCategory(): Category {
         while (true) {
             println("Choose category (1: SALARY, 2: FOOD, 3: FEES, 4: SHOPPING): ")
             when (readLine()?.toIntOrNull()) {
                 1 -> return Category.SALARY
                 2 -> return Category.FOOD
                 3 -> return Category.FEES
                 4 -> return Category.SHOPPING
                 else -> println("Invalid input. Please enter a number between 1 and 4.")
             }
         }
     }
 }
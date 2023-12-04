package com.example.bankaccount

fun main(){
    val whosBankAccount = BankAccount("Whoeswhat", 134.3)
    whosBankAccount.deposit(200.0)
    whosBankAccount.withdraw(1300.0)
    whosBankAccount.deposit(3000.0)
    whosBankAccount.deposit(2500.0)
    whosBankAccount.withdraw(3300.0)
    whosBankAccount.displayTransactionHistory()
    println("${whosBankAccount.accountHolder}'s balance is ${whosBankAccount.acctBalance()}")

}
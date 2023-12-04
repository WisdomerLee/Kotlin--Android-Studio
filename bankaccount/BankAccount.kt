package com.example.bankaccount

class BankAccount(
    var accountHolder: String,
    private var balance: Double) {
    //리스트를 설정할 때 아무런 초기 값이 없을 땐 아래와 같이 어느 유형의 데이터를 받을 것인지 선언해두어야 함!
    //아래의 조건은 문자열 리스트라는 것을 알 수 있음
    private val transactionHistory = mutableListOf<String>()

    fun deposit(amount: Double){
        balance += amount
        transactionHistory.add("$accountHolder deposited $$amount")
    }
    fun withdraw(amount: Double){
        if(amount <= balance){
            balance -= amount
            transactionHistory.add("$accountHolder withdrawed $$amount")
        }
        else{
            println("You don't have the funds to withdraw $$amount")
        }
    }
    fun displayTransactionHistory(){
        println("Transaction history for $accountHolder")
        for (transaction in transactionHistory){
            println(transaction)
        }
    }
    fun acctBalance(): Double{
        return balance
    }

}
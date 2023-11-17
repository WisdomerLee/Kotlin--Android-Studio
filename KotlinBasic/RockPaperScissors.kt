package com.example.rockpaperscissor

fun main(){
    var computerChoice = ""
    var playerChoice = ""
    println("Rock, Paper or Scissors? Enter your choice!")
    playerChoice = readln()

    val randomNumber = (1..3).random() //1~3까지의 숫자 중에 랜덤하게 뽑음!


    //if - else if else if에 들어가는 변수가 다 같고 그 비교 조건이 다른 경우면
    //when으로 대체할 수 있음! :  C등의 switch와 같은 것


    when (randomNumber) {
        1 -> {
            computerChoice = "Rock"
        }
        2 -> {
            computerChoice = "Paper"
        }
        3 -> {
            computerChoice = "Scissors"
        }
    }

    println(playerChoice)
    println(computerChoice)

    val winner = when {
        playerChoice == computerChoice -> "Tie"
        playerChoice == "Rock" && computerChoice == "Scissors" -> "Player"
        playerChoice == "Paper" && computerChoice == "Rock" -> "Player"
        playerChoice == "Scissor" && computerChoice == "Paper" -> "Player"
        else -> "Computer"
    }
    if(winner == "Tie"){
        println("It is a Tie")
    }
    else{
        println("$winner won!")
    }
    //문자열 내부에 $변수 이름 : 해당 변수를 문자열로 반환
}

fun whileattachmain(){
    var count = 0
    //while() : ()안의 조건이 거짓이 될 때까지 {}의 내용을 반복
    while(count<3){
        println("Count is $count")
        count++
    }
    println("Loop is done!")
    var userInput = readln()
    while(userInput == "1"){
        println("While loop executed")
        userInput = readln()
        break //while 등의 {}안의 내용에서 탈출!
    }
    println("Loop is done!")
}
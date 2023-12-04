package com.example.foodrecipieapp


fun operate(){
    println("number input needed")
    try{
        //에러가 발생할 수도 있는 코드!
        val number = readln().toInt()
        println("User entered $number")

    }catch(e: Exception){
        //에러가 발생했을 때 실행될 코드
        println(e.message)
    }finally {
        //에러와 무관하게 항상 실행될 코드!
        println("This will be execute regardless. Error or no error")
    }
}

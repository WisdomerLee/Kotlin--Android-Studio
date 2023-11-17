package com.example.coffeemachines

fun main(){
    var dor = Dog("말콩", "스피츠")
    println("${dor.name}은 ${dor.breed} 계통")
    println(dor.breed)
    println(dor.bark("말콩"))

    val coffeeForDenis = CoffeeData(1, "Denis", "큼", 3)
    makeCoffee(coffeeForDenis)


}


//kotlin에는 data class라는 것이 있음 : 데이터를 저장하는 것이 기본 목적임 C#의 record 타입과 비슷
data class CoffeeData(val sugarCount: Int, val name: String, val size:String, val creamAmount:Int)

//여러 줄의 코멘트는 아래와 같이 씀

/*
fun inputAdd(){

    println("Enter number1")
    val first = readln().toInt()
    println("Enter number2")
    val second = readln().toInt()
    val myResult = add(first, second)
    println("[$first] + [$second] = [$myResult]")

}

fun divide(num1: Int, num2: Int):Double{
    val result = num1/ num2.toDouble()
    return result
}

//함수가 무엇을 돌려주어야 한다면 함수():돌려줄 타입!의 형태로 만들기
fun add(num:Int, num2:Int) : Int{
    val result = num + num2
    return result
}
*/


fun askCoffeeDetails(){
    println("커피를 마실 사람의 이름을 넣어줍시다")
    val name = readln()
    println("설탕은 얼마나 넣을까요?")
    val sugarCount = readln()
    val sugarCountInt = sugarCount.toInt()
    //makeCoffee(sugarCountInt, name)
}

//함수 정의
fun makeCoffee(coffeeData: CoffeeData){

    if(coffeeData.sugarCount==0){
        println("[${coffeeData.name}]이 마실 설탕 없는 커피")
    }
    else{
        println("[${coffeeData.name}]이 마실 설탕 ${coffeeData.sugarCount} 숟갈이 들어간 커피")
    }
}
//리턴값이 있는 함수 정의하기!

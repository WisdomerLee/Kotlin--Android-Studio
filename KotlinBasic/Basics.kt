package com.example.kotlinbasic

//함수를 작성하면 함수 옆에 작은 녹색 세모꼴 아이콘이 생성됨
//이를 실행하면 그 함수의 실행 결과를 미리 알 수 있음!!
//혹은 play.kotlinlang.org라는 곳으로 들어가 코틀린 프로그램을 짠 것을 실행시켜 볼 수 있음
fun main(){

    //코틀린의 val은 처음에 한 번 값을 지정하면 그 값을 바꿀 수 없음...
    val number1 = 1
    //var는 값이 바뀔 수 있음!
    var age = 33
    age = 34
    //var, val과 같은 형태로 변수를 지정할 때는 compiler가 자동으로 해당 값을 특정 값에 맞는 변수로 자동으로 지정해주지만
    //변수를 지정할 때 특정 타입으로 제한하고 싶을 경우...
    //아래와 같은 형태로도 쓸 수 있음
    var customVar : Byte = 5
    //기본적으로 정수형의 숫자들은 int로 지정됨 > 그리고 그 값이 커지면 할당되는 변수에 맞게 자동으로 long으로 바뀌어 저장함..
    //초기 지정한 값에 따라 바뀌게 됨
    //그리고 설정된 기본 변수의 형태가 가질 수 있는 최대 값을 넘는 숫자를 넣게 되면 에러가 발생
    //위와 같은 것을 방지하기 위해 :Long과 같은 형태로 데이터 타입을 지정하게 되는 것
    //kotlin의 정수형 타입은 Byte, Short, Int, Long이 있음
    //정수형이 아닌 소숫점이 들어간 숫자들은
    // float과 double 2가지가 있음
    //또한 소숫점의 숫자는 기본적으로 double로 지정하는데 숫자 끝에 f를 넣으면 float 타입으로 인식함

    //위에 있는 것들은 부호가 있음 : 즉 양수, 음수 모두 처리함
    //그러나 무조건 0보다 큰 숫자들만 처리할 경우엔??
    //UByte, UShort, UInt, ULong

    var unsignedAge : UShort = 35u
    //unsigned 숫자들의 경우 그 값을 집어넣을 때 숫자 끝에 u를 넣음!
    
    //Boolean : true, false를 가진 것
    //Boolean? : true, false외에 null도 가질 수 있음

    // &&, ||, !의 연산자는 다른 프로그래밍 언어와 같음
    //

    val customTrue = true
    val customFalse = false
    println(!customFalse)

    println("Hello World!")

    //char, Unicode, escape character
    //특수한 역할을 하는 캐릭터들 앞에 \ 이 붙으면 그 뒤에 붙는 문자에 따라 하는 역할이 달라짐
    //몇 칸을 떼거나 등등

    //if () : ()에 있는 boolean이 true일 경우에만 {}내용 실행
    // >, >=, ==, !=, <, <= 등의 연산자들이 쓰임
    //위의 비교 연산자를 통해 boolean을 받아 처리할 수도 있음

    print("Enter your age as a whole number: ")

    val inputage = readln().toInt()
    //사용자의 입력 : readln()으로 읽음 : toInt() : 읽어들인 값을 숫자로 처리함
    //아래와 같이 in 시작 숫자..끝 숫자로 넣으면 해당 숫자가 그 값 사이에 있으면 참, 아니면 거짓이 출력됨
    if(inputage in 18..40){
        println("성년임")
    }
    else if(inputage >=13){
        println("청소년")
    }
    else{
        println("어림")
    }
}
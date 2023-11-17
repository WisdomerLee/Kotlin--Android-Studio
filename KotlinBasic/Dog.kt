package com.example.coffeemachines

class Dog (val name: String, val breed:String, var age:Int = 0) {
    // constructor : 생성자...
    //클래스 이름 뒤()가 붙으면 ()안에 클래스 생성시 집어넣은 파라미터가 생성할 때 필요함
    //파라미터 앞에 val이 붙으면 클래스 내부 속성이라는 것을 가리킴!
    //val이 생략되면 단순 생성할 때의 파라미터로 쓰임 : 클래스 내부 속성이 아닌 생성할 때만 잠깐 쓰이는 파라미터
    //또한 기본 값을 지정하면 그 값이 지정되지 않으면 지정된 값으로 자동 설정됨, 다른 값을 넣으면 그 값으로 덮어씌워짐
    //var age:Int=0이 그것
    //클래스 객체가 생성될 때 실행됨 : init
    init{
        bark(name)
    }
    fun bark(name: String){
        println("$name 멍멍")
    }

}
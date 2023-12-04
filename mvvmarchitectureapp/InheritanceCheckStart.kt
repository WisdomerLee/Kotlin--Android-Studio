package com.example.mvvmarchitectureapp

//기본이 될 클래스는 open 키워드를 달고 있어야 함!
//
open class First{

    //또한 상속받은 클래스에서 재정의가 가능한 함수가 되려면 함수 앞에 open이 붙어야 함
    open fun role(){
        println("Member of the Base")
    }

    fun coreValues(){
        println("Core values of Base")
    }
}
//상속을 하게 되면...
open class Second : First(){
    //override : 상속받은 함수의 내용을 완전히 다르게 혹은 내용을 추가하여 적용할 수 있음
    override fun role() {
        super.role() //super.override한 같은 이름의 함수 : 부모클래스에서 정의한 내용을 실행할 것
        println("Knight of the House")
    }
}

class Third : First(){
    override fun role() {
        println("Bard of the House")
    }
}

interface Archer{
    fun archery(){
        println("Archery skill from ~")
    }
}
interface Singer{
    fun sing(){
        println("Singing skill from ~")
    }
}

class Offspring : Second(), Archer, Singer {
    override fun archery() {
        super.archery()
        println("Archery skills enhanced by Offspring")
    }

    override fun sing() {
        super.sing()
        println("Singing skills enhanced by Offspring")
    }
}

//interface를 쓰는 이유??
//상속을 여럿 할 수 있음!
//유연성, 교체성 등이 매우 좋음!
//클래스 내부 구현은 숨기고 외부에 interface의 함수 이름만 노출시킴
//클래스 내부 구현이 바뀌어도 인터페이스를 통한 호출엔 영향이 없음!


class InheritanceCheckStart {
    fun Operate(){
        val obj1 = First()
        obj1.coreValues()

        val obj2 = Second()
        //클래스 자체에는 아무 기능이 없어도 부모 클래스에서 구현된 함수를 호출할 수 있음!
        obj2.coreValues()
        obj2.role()

        val obj3 = Third()
        obj3.role()

    }
}
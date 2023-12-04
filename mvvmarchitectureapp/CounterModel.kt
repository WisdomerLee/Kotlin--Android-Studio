package com.example.mvvmarchitectureapp

//Model : 데이터만 갖고 있으며 내부적으로는 아무런 로직을 갖고 있지 않음!
data class CounterModel(var count : Int)

//Repository쪽에서 데이터를 저장하고 데이터를 수정하는 로직을 갖게 됨!
class CounterRepository{
    private var _counter = CounterModel(0)

    fun getCounter() = _counter

    fun incrementCounter(){
        _counter.count++
    }

    fun decrementCounter(){
        _counter.count--
    }
}
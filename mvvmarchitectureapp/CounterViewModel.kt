package com.example.mvvmarchitectureapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

//View - Model 사이에서 ....행동 할 모델
class CounterViewModel(private val repository: CounterRepository) : ViewModel() {
    private val _count = mutableStateOf(repository.getCounter().count)

    //외부에 노출될 때는 바꿀 수 없는 형태로 제공되어야 함 : 현재 상태만 알려주는 형식으로
    //바꾸는 것은 ViewModel 내부에서만
    val count: MutableState<Int> = _count


    fun increment(){
        repository.incrementCounter()
        _count.value = repository.getCounter().count
    }
    fun decrement(){
        repository.decrementCounter()
        _count.value = repository.getCounter().count
    }
}
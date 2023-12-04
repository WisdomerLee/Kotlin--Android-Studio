package com.example.foodrecipieapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _categorieState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categorieState

    init{
        fetchCategories()
    }


    private fun fetchCategories(){
        //viewModelScope.launch : Coroutine으로 설정될 수 있는 함수 실행 : 비동기 방식으로 실행하는 방법 중에 하나
        viewModelScope.launch {
            try{
                val response = recipieService.getCategories()
                _categorieState.value = _categorieState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            }catch(e: Exception){
                _categorieState.value = _categorieState.value.copy(loading = false, error = "Error fetching Categories ${e.message}")
            }
        }
    }

    data class RecipeState
        (
                val loading: Boolean = true,
                val list: List<Category> = emptyList(),
                val error: String? = null
                )

}
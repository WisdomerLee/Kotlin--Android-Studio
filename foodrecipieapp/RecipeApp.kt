package com.example.foodrecipieapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = "Screen.RecipeScreen.route"){
        composable(route = Screen.RecipeScreen.route){
            RecipeScreen(viewstate = viewstate, navigateToDetail = {
                //이 영역은 데이터를 현재 스크린에서 음식 세부 정보를 알려주는 곳으로 넘겨주는 역할을 담당하는 곳
                //savedStateHandle을 이용하는데 해당 요소는 navigation framework의 요소임
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(route = Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("cat") ?: Category("", "", "", "")
            CategoryDetailScreen(category = category)

        }
    }
}
package com.example.foodrecipieapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter


@Composable
fun RecipeScreen(modifier: Modifier = Modifier,
                 viewstate:MainViewModel.RecipeState,
                 navigateToDetail: (Category) -> Unit
){

    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewstate.loading -> {
                //만약 로딩중이면 > 원형의 프로그레스 진행 UI를 띄울 것
                CircularProgressIndicator(modifier.align(alignment = Alignment.Center))
            }
            viewstate.error != null ->{
                Text(text = "Error Occurred")
            }
            else ->{
                //카테고리 화면에 보여주기
                CategoryScreen(categories = viewstate.list, navigateToDetail)
            }

        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>, navigateToDetail: (Category) -> Unit){
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()){
        items(categories){
            category -> CategoryItem(category = category, navigateToDetail)
        }
    }
}

@Composable
fun CategoryItem(category:Category, navigateToDetail: (Category) -> Unit){
    //clickable : 클릭으로 상호작용 가능하도록 만듦
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize().clickable { navigateToDetail(category) },

        horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter = rememberAsyncImagePainter(category.strCategoryThumb), contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f))
        Text(text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top=4.dp)
            )
    }
}
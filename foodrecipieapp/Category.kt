package com.example.foodrecipieapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


//data만 이곳에 있을 것!
//함수나 클래스를 보면 Category를 통째로 넘기는 과정이 있고, navigation에도 전달하는 것을 볼 수 있는데
//navigation을 통해 route가 다른 곳에 넘기려면 직렬화가 필수!
//plugin id("kotlin-parcelize")을 추가하고 나서!
//@Parcelize 를 붙이고 :Parcelable을 붙이면 : 직렬화 가능한 상태가 됨!
//또한 해당 직렬화는 데이터를 문자열로 만들어주고, 역직렬화를 통해 다시 원래의 데이터로 돌아가게 됨
@Parcelize
data class Category(val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
    ):Parcelable

data class CategoriesResponse(val categories: List<Category>)

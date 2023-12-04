package com.example.foodrecipieapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create()).build()
//기본 baseUrl로 전달된 페이지를 기준으로 network 요청이 생성

    val recipieService = retrofit.create(ApiService::class.java)


interface ApiService{
    @GET("categories.php")
    //위의 @GET은 : 해당 api는 GET 메서드로 동작하고 그것은 기본 api에 "categories.php"이 더 붙음!
    suspend fun getCategories():CategoriesResponse
    //suspend 키워드를 앞에 붙인 함수!
    //suspend 키워드가 붙은 함수는 api에 붙을 때 보다 성능에 영향을 많이 줌(느림)
    //왜냐하면 데이터의 갯수가 엄청나게 많거나, 인터넷 속도가 느리거나 할 경우 ...
    //suspend가 붙으면 : asynchronous : 즉 비동기 방식으로 처리함 : coroutine 등등
    //coroutine api로 처리 : 동시에 여러 가지 처리를 할 경우에 종종 쓰임
    //suspend : background로 처리할 것을 의미
    //coroutine : kotlin에서 동시에 처리할 때 매우 강력하고 간단히 쓸 수 있는 방식!
    //특히 비동기 방식을 다루거나 함수가 실행될 때 프로그램이 멈추는 것을 방지하는 방식

}
package com.example.shoppinglistapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//interface를 상속받은 클래스인 Geocodingapi서비스 인터페이스를 호출할 객체
object RetrofitClient {
    private const val BASE_URL = "https://maps.gooogleapis.com/"

    fun create(): GeocodingApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(GeocodingApiService::class.java)
    }
}
package com.example.shoppinglistapp

import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApiService {
    //기본적으로 요청하는 주소에 api 요청시 추가 로 붙는 주소
    //GET함수는 대체로 서버에서 값을 받아올 때 씀
    @GET("maps/api/geocode/json")
    suspend fun getAddressFromCoordinates(
        @Query("latlng") latlng: String,
        @Query("key") apiKey: String
    ): GeocodingResponse
}
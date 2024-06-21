package com.example.apppractice.data

import com.example.apppractice.model.MRoutesResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {

    @GET("v2/directions/driving-car")
    suspend fun getRoute(
        @Query("api_key") apiKey :String,
        @Query("start", encoded = true) rStart :String,
        @Query("end", encoded = true) rEnd :String
    ): Response<MRoutesResponseModel>
}
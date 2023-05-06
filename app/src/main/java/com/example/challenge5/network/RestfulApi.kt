package com.example.networkingwithretrofit.network

import com.example.networkingwithretrofit.news.ResponseDataNewsItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestfulApi {

    @GET("news")
    fun getAllNews(): Call<List<ResponseDataNewsItem>>

    @GET("news/{id}")
    suspend fun getNewsById(@Path("id") id: Int): Response<ResponseDataNewsItem>
}
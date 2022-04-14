package com.example.belajarretrofit.service

import com.example.belajarretrofit.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("admin/car")
    fun getAllCar(): Call<List<MobilResponseItem>>

    @GET("admin/car/{id}")
    fun getCarById(@Path("id") id: Int): Call<MobilResponseItem>

    @POST("admin/auth/register")
    fun postRegister(@Body request: RegisterRequest): Call<RegisterResponse>

    @GET("leagues")
    fun getKlasemen(): Call<KlasemenResponse>

}
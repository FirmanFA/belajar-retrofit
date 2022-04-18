package com.example.belajarretrofit.service

import com.example.belajarretrofit.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {



    @GET("admin/car")
    fun getAllCar(): Call<List<MobilResponseItem>>

    @GET("admin/car/{id}")
    fun getCarById(@Path("id") id: Int): Call<MobilResponseItem>

    @POST("admin/auth/register")
    fun postRegister(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("admin/auth/login")
    fun postLogin(@Body request: LoginRequest): Call<LoginResponse>

    @GET("movie/popular")
    fun getAllMovies(): Call<MovieResponse>

    @GET("movie/{id}")
    fun getDetailMovie(@Path("id") id: Int): Call<DetailMovieResponse>

    @GET("leagues")
    fun getKlasemen(): Call<KlasemenResponse>



}
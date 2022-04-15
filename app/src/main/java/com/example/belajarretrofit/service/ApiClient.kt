package com.example.belajarretrofit.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // BASE_URL merupakan URL default untuk mengkoneksikan aplikasi dengan endpoint pada API
    const val BASE_URL = "https://rent-cars-api.herokuapp.com/"
    const val BASE_URL2 = "https://abc.com/"
    const val BASE_URL3 = "https://api-football-standings.azharimm.site/"
    const val BASE_URL4 = "https://api.themoviedb.org/3/"

    private val logging: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

//    private val client = OkHttpClient.Builder()
//        .addInterceptor(logging)
//        .build()

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor {
            val request = it.request()
            val queryBuild =  request.url
                .newBuilder()
                .addQueryParameter("api_key","5374e8eba1107b24236cc30d17d5aa11").build()
            return@addInterceptor it.proceed(request.newBuilder().url(queryBuild).build())
        }
        .build()


    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL4)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(ApiService::class.java)
    }

    val instance2: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL3)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(ApiService::class.java)
    }
}

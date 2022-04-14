package com.example.belajarretrofit.model


import com.google.gson.annotations.SerializedName

data class Logos(
    @SerializedName("dark")
    val dark: String,
    @SerializedName("light")
    val light: String
)
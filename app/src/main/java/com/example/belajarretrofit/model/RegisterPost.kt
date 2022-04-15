package com.example.belajarretrofit.model


import com.google.gson.annotations.SerializedName

data class RegisterPost(
    @SerializedName("email")
    val email: String,
    @SerializedName("nomor_telepon")
    val nomorTelepon: String,
    @SerializedName("password")
    val password: String
)
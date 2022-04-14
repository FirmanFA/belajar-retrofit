package com.example.belajarretrofit.model


import com.google.gson.annotations.SerializedName

data class KlasemenResponse(
    @SerializedName("data")
    val `data`: List<KlasemenItem>,
    @SerializedName("status")
    val status: Boolean
)
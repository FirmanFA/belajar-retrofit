package com.example.belajarretrofit.model


import com.google.gson.annotations.SerializedName

data class KlasemenItem(
    @SerializedName("abbr")
    val abbr: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("logos")
    val logos: Logos,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)
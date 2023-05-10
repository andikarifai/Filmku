package com.and.filmku.model


import com.google.gson.annotations.SerializedName

data class ResponseFilmItem(
    @SerializedName("date")
    val date: String,
    @SerializedName("description ")
    val description: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)
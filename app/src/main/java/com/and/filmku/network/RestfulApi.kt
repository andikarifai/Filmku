package com.and.filmku.network

import com.and.filmku.model.ResponseDataFilm
import com.and.filmku.model.ResponseFilmItem
import com.and.filmku.network.RetrofitClient.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestfulApi {
//    @GET("film")
//    fun getAllFilm(): Call<List<ResponseDataFilm>>
    @GET("movie/popular")
    fun getAllFilm(@Query("api_key") apiKey: String) : Call<List<ResponseDataFilm>>
}


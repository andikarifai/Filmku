package com.and.filmku.network

import com.and.filmku.model.ResponseDataFilm
import com.and.filmku.model.ResultFilm
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestfulApi {
    @GET("movie/popular")
    fun getAllFilm(
        @Query("api_key") apiKey: String = RetrofitClient.API_KEY,
//        @Query("page") page: Int
    ) : Call<ResponseDataFilm>
}



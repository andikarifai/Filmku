package com.and.filmku.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.and.filmku.model.ResponseDataFilm
import com.and.filmku.model.ResultFilm
import com.and.filmku.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmViewModel : ViewModel() {

    private val restfulApi = RetrofitClient.instance
    val liveDataFilm = MutableLiveData<List<ResultFilm>>()
    val errorMessage = MutableLiveData<String>()

    fun callApiFilm() {
        restfulApi.getAllFilm().enqueue(object : Callback<ResponseDataFilm>{
            override fun onResponse(call: Call<ResponseDataFilm>, response: Response<ResponseDataFilm>) {
                if (response.isSuccessful) {
                    liveDataFilm.postValue(response.body()?.results)
                    println("Response body: ${response.body()}")
                } else {
                    errorMessage.postValue("Failed to fetch data")
                    liveDataFilm.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseDataFilm>, t: Throwable) {
                liveDataFilm.postValue(null)
                println("Error: ${t.message}")
            }
        })
    }

}



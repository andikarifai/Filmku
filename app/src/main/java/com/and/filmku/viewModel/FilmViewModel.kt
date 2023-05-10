package com.and.filmku.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.and.filmku.model.ResponseDataFilm
import com.and.filmku.model.ResponseFilmItem
import com.and.filmku.network.RetrofitClient
import com.and.filmku.network.RetrofitClient.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.RowId

class FilmViewModel : ViewModel() {

    lateinit var liveDataFilm: MutableLiveData<List<ResponseDataFilm>>

    init {
        liveDataFilm = MutableLiveData()
    }

    fun callApiFilm(){
        RetrofitClient.instance.getAllFilm(API_KEY).enqueue(object : Callback<List<ResponseDataFilm>> {
            override fun onResponse(
                call: Call<List<ResponseDataFilm>>,
                response: Response<List<ResponseDataFilm>>
            ) {
                if (response.isSuccessful) {
                    liveDataFilm.postValue(response.body())
                } else {
                    liveDataFilm.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<ResponseDataFilm>>, t: Throwable) {
                liveDataFilm.postValue(null)
            }
        })
    }

}


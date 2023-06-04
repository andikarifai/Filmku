package com.and.filmku.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.and.filmku.model.ResponseDataFilm
import com.and.filmku.model.ResultFilm
import com.and.filmku.network.RestfulApi
import dagger.hilt.android.lifecycle.HiltViewModel
import org.junit.runner.RunWith
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel

class FilmViewModel @Inject constructor (private var api: RestfulApi) : ViewModel() {

    val liveDataFilm = MutableLiveData<List<ResultFilm>?>()

    fun callApiFilm() {
        api.getAllFilm().enqueue(object : Callback<ResponseDataFilm>{
            override fun onResponse(call: Call<ResponseDataFilm>, response: Response<ResponseDataFilm>) {
                if (response.isSuccessful) {
                    liveDataFilm.postValue(response.body()?.results)
                    println("Response body: ${response.body()}")
                } else {
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



package com.and.filmku.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.and.filmku.room.FavoriteDao
import com.and.filmku.room.FavoriteDatabase
import com.and.filmku.room.FavoriteFilm
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteDao: FavoriteDao
) : ViewModel() {

    val liveDataListfav: LiveData<List<FavoriteFilm>> = favoriteDao.getAllFavoriteFilms()

    suspend fun addFavoriteFilm(favoriteFilm: FavoriteFilm) {
        favoriteDao.addFavoriteFilm(favoriteFilm)
    }

    suspend fun delete(favoriteMovie: FavoriteFilm) {
        favoriteDao.deleteFavoriteFilm(favoriteMovie)
    }

    fun getLiveDataMoviefav(): LiveData<List<FavoriteFilm>> {
        return liveDataListfav
    }
}



package com.and.filmku.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Suppress("unused", "unused", "unused", "unused", "unused")
@Dao
interface FavoriteDao {
    @Query("SELECT * FROM FavoriteFilm")
    fun getAllFavoriteFilms(): LiveData<List<FavoriteFilm>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addFavoriteFilm(favoriteFilm: FavoriteFilm)

    @Delete
     fun deleteFavoriteFilm(favoriteFilm: FavoriteFilm)

}

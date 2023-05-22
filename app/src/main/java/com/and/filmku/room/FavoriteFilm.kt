package com.and.filmku.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.and.filmku.model.ResultFilm
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_films")
data class FavoriteFilm(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val backdropPath: String,
    val overview: String,
    val releaseDate: String,
    val title: String
)

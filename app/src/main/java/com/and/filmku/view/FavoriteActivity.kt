@file:Suppress("SpellCheckingInspection", "SpellCheckingInspection")

package com.and.filmku.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.and.filmku.adapter.FavoriteAdapter
import com.and.filmku.adapter.FilmAdapter
import com.and.filmku.databinding.ActivityFavoriteBinding
import com.and.filmku.model.ResultFilm
import com.and.filmku.room.FavoriteDao
import com.and.filmku.room.FavoriteDatabase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@Suppress("SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection",
    "SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection",
    "SpellCheckingInspection", "SpellCheckingInspection"
)
@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var favoriteDao: FavoriteDao
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi favoriteDao dengan menggunakan FavoriteDatabase.getInstance(context).favoriteDao()
        favoriteDao = FavoriteDatabase.getInstance(applicationContext)!!.favoriteDao()

        favoriteAdapter = FavoriteAdapter(emptyList())
        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.adapter = favoriteAdapter

        // Mengamati LiveData favoriteFilms dari favoriteDao.getAllFavoriteFilms()
        favoriteDao.getAllFavoriteFilms().observe(this) { favoriteFilms ->
            favoriteAdapter.favoriteFilms = favoriteFilms
            favoriteAdapter.notifyDataSetChanged()
        }
    }
}

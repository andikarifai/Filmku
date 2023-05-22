@file:Suppress("SpellCheckingInspection", "SpellCheckingInspection")

package com.and.filmku.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.and.filmku.adapter.FilmAdapter
import com.and.filmku.databinding.ActivityFavoriteBinding
import com.and.filmku.model.ResultFilm
import com.and.filmku.room.FavoriteDao
import dagger.hilt.android.AndroidEntryPoint

@Suppress("SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection",
    "SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection",
    "SpellCheckingInspection", "SpellCheckingInspection"
)
@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: FilmAdapter
    private lateinit var favoriteMovieDao: FavoriteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        // Inisialisasi database dan Dao
//        val database = Room.databaseBuilder(applicationContext, FavoriteDatabase::class.java, "favorite_film").build()
//        favoriteMovieDao = database.favoriteDao()

        // Atur RecyclerView dan adapter
        adapter = FilmAdapter(emptyList()) { selectedFilm ->
            val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
            intent.putExtra("FILM", selectedFilm)
            startActivity(intent)
        }
        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.adapter = adapter

//        // Tampilkan data film favorit
//        displayFavoriteMovies()
    }

//    private fun displayFavoriteMovies() {
//        favoriteMovieDao.getAllFavoriteFilms().observe(this) { favoriteMovies ->
//            if (favoriteMovies.isNotEmpty()) {
//                val films = favoriteMovies.map { favoriteMovie ->
//                    ResultFilm(
//                        favoriteMovie.backdropPath,
//                        favoriteMovie.id,
//                        favoriteMovie.overview,
//                        favoriteMovie.releaseDate,
//                        favoriteMovie.title,
//                    )
//                }
//                adapter.setData(films)
//            } else {
//                Toast.makeText(this, "Tidak ada film favorit.", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

}

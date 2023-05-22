package com.and.filmku.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.and.filmku.R
import com.and.filmku.databinding.ActivityDetailBinding
import com.and.filmku.model.ResultFilm
import com.and.filmku.room.FavoriteDao
import com.and.filmku.room.FavoriteFilm
import com.and.filmku.viewModel.FavoriteViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        val film = intent?.getParcelableExtra<ResultFilm>("FILM")

        supportActionBar?.title = film?.title

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original/${film?.backdropPath}")
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.logoDetail)

        binding.titleDetailFilm.text = film?.title
        binding.detailRelease.text = film?.releaseDate
        binding.descDetail.text = film?.overview

        binding.buttonFavorite.setOnClickListener {
            film?.let {
                addToFavorites(it)
            }
        }
    }

    private fun addToFavorites(film: ResultFilm) {
        val favoriteFilm = FavoriteFilm(
            backdropPath = film.backdropPath,
            overview = film.overview,
            releaseDate = film.releaseDate,
            title = film.title
        )

        CoroutineScope(Dispatchers.IO).launch {
            favoriteViewModel.addFavoriteFilm(favoriteFilm)
            runOnUiThread {
                Toast.makeText(applicationContext, "Added to Favorites", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@DetailActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}



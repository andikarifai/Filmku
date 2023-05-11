package com.and.filmku.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.and.filmku.R
import com.and.filmku.adapter.FilmAdapter
import com.and.filmku.databinding.ActivityDetailBinding
import com.and.filmku.model.ResultFilm
import com.and.filmku.viewModel.FilmViewModel
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan data film dari intent
        val film = intent.getParcelableExtra<ResultFilm>("FILM")

        // Set judul halaman
        supportActionBar?.title = film?.title

        // Menampilkan gambar backdrop film ke ImageView
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original/${film?.backdropPath}")
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.logoDetail)

        // Menampilkan informasi film ke TextView
        binding.titleDetailFilm.text = film?.title
        binding.detailRelease.text = film?.releaseDate
        binding.descDetail.text = film?.overview
    }
}
package com.and.filmku.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.and.filmku.R
import com.and.filmku.adapter.FilmAdapter
import com.and.filmku.databinding.ActivityHomeBinding
import com.and.filmku.viewModel.FilmViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    showDataFilm()
    }

    private fun showDataFilm() {
        val viewModelFilm = ViewModelProvider(this).get(FilmViewModel::class.java)
        viewModelFilm.callApiFilm()
        viewModelFilm.liveDataFilm.observe(this, Observer { data ->
            if (data != null && data.isNotEmpty()) {
                binding.rvHome.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvHome.adapter = FilmAdapter(data)
            } else {
                // tampilkan pesan bahwa data kosong
                Toast.makeText(this, "Tidak ada data film yang ditemukan.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
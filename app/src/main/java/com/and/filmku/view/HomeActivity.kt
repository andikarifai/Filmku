package com.and.filmku.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.and.filmku.R
import com.and.filmku.adapter.FilmAdapter
import com.and.filmku.databinding.ActivityHomeBinding
import com.and.filmku.model.ResultFilm
import com.and.filmku.viewModel.FilmViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: FilmAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE) // mengubah key menjadi "MyPrefs"
        editor = sharedPreferences.edit()

        // Mendapatkan data email dan username dari SharedPreferences
        val email = sharedPreferences.getString("email", "")
        val username = sharedPreferences.getString("username", "")

        // Jika email dan username tidak kosong, langsung tampilkan showDataFilm
        if (username != null && username != "" && email != null && email != "") {
            showDataFilm()
        } else {
            // Jika email dan username kosong, arahkan ke halaman login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        Log.d("HomeActivity", "email: $email, username: $username")

        // Menambahkan item click listener pada card
        adapter = FilmAdapter(emptyList()) { selectedFilm ->
            val intent = Intent(this@HomeActivity, DetailActivity::class.java)
            intent.putExtra("FILM", selectedFilm)
            startActivity(intent)
        }
        binding.rvHome.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvHome.adapter = adapter
    }


    private fun showDataFilm() {
        val viewModelFilm = ViewModelProvider(this).get(FilmViewModel::class.java)
        viewModelFilm.callApiFilm()
        viewModelFilm.liveDataFilm.observe(this, Observer { data ->
            if (data != null) {
                // Tampilkan data film jika ada
                binding.rvHome.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                // tambahkan parameter onItemClick saat membuat instance FilmAdapter
                binding.rvHome.adapter = FilmAdapter(data) { selectedFilm ->
                    val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                    intent.putExtra("FILM", selectedFilm)
                    startActivity(intent)
                }
            } else {
                // tampilkan pesan bahwa data kosong
                Toast.makeText(this, "Tidak ada data film yang ditemukan.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_profile -> {
                val username = sharedPreferences.getString("username", "")
                val email = sharedPreferences.getString("email", "")

                if (username != null && username != "" && email != null && email != "") {
                    // Jika email dan username tersimpan, tampilkan profil pengguna
                    val intent = Intent(this, ProfilActivity::class.java)
                    intent.putExtra("USERNAME", username)
                    intent.putExtra("EMAIL", email)
                    startActivity(intent)
                } else {
                    // Jika email dan username tidak tersimpan, arahkan ke halaman login
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}

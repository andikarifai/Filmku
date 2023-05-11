package com.and.filmku.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.and.filmku.R
import com.and.filmku.databinding.ActivityProfilBinding

class ProfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // memperoleh nilai dari intent
        val username = intent.getStringExtra("USERNAME")
        val email = intent.getStringExtra("EMAIL")
        Log.d("ProfilActivity", "Username: $username, Email: $email")

        // menampilkan nilai pada TextView
        binding.profilName.text = username
        binding.profilEmail.text = email

        binding.buttonLogout.setOnClickListener {
            // menghapus data email dan username dari SharedPreferences
            val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("email")
            editor.remove("username")
            editor.apply()

            // mengarahkan user ke halaman login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}



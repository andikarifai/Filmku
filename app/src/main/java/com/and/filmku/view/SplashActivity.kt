package com.and.filmku.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import com.and.filmku.R
import com.and.filmku.databinding.ActivitySplashBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val splashTimeOut: Long = 3000 // Waktu tampilan splash screen (dalam milidetik)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Cek apakah pengguna sudah login sebelumnya
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        val email = sharedPreferences.getString("email", "")
        val username = sharedPreferences.getString("username", "")


        Log.d("SplashActivity", "isLoggedIn: $isLoggedIn, email: $email, username: $username")

        Handler().postDelayed({
            if (isLoggedIn && !email.isNullOrEmpty() && !username.isNullOrEmpty()) {
                // Pengguna sudah login sebelumnya, pindah ke halaman Home
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("email", email)
                intent.putExtra("username", username)
                startActivity(intent)

                Log.d("SplashActivity", "isLoggedIn: $isLoggedIn, email: $email, username: $username")
            } else {
                // Pengguna belum login, pindah ke halaman Login
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

            finish() // Menutup aktivitas splash screen agar tidak dapat diakses kembali dengan menekan tombol "Kembali"
        }, splashTimeOut)
    }
}


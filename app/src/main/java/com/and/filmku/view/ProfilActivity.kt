package com.and.filmku.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.and.filmku.databinding.ActivityProfilBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("email")
            editor.remove("username")
            // Set isLoggedIn menjadi false
            editor.putBoolean("isLoggedIn", false)
            editor.apply()


            // Log untuk memeriksa penghapusan data
            val savedEmail = sharedPreferences.getString("email", null)
            val savedUsername = sharedPreferences.getString("username", null)
            val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
            Log.d("Logout", "Saved email: $savedEmail, saved username: $savedUsername, isLoggedIn: $isLoggedIn")


            // mengarahkan user ke halaman login
            val intent = Intent(this, LoginActivity::class.java)
//            membuat agar tidak langsung finish namun
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)

            finish()
        }
    }
}



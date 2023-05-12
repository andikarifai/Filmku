package com.and.filmku.view

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.and.filmku.databinding.ActivityLoginBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        binding.loginButton.setOnClickListener{
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()
            val username = sharedPreferences.getString("username", "")

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener (this) {task ->
                    if (task.isSuccessful) {
                        Log.d("LoginActivity", "email: ${email}, username: ${username}")

                        // Menyimpan email dan username pada SharedPreferences
                        val editor = sharedPreferences.edit()
                        editor.putString("email", email)
                        editor.putString("username", username)
                        editor.apply()

                        // Kirim event analitik Firebase saat berhasil login
                        val bundle = Bundle().apply {
                            putString(FirebaseAnalytics.Param.METHOD, "Email/Password")
                        }
                        FirebaseAnalytics.getInstance(this).logEvent(FirebaseAnalytics.Event.LOGIN,bundle)

                        val savedEmail = sharedPreferences.getString("email", null)
                        val savedUsername = sharedPreferences.getString("username", null)
                        Log.d("LoginActivity", "Saved email: $savedEmail, saved username: $savedUsername")

                        val intent = Intent(this, HomeActivity::class.java)
                        intent.putExtra("email", email)
                        intent.putExtra("username", username)
                        startActivity(intent)

                        Toast.makeText(this, "Berhasil Login", Toast.LENGTH_SHORT).show()
//                        val intent = Intent(this, HomeActivity::class.java)
//                        startActivity(intent)
                    } else {
                        Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
        binding.tvRegisterNow.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}

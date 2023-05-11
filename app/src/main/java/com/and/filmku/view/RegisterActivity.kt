package com.and.filmku.view

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.and.filmku.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        binding.registerButton.setOnClickListener {
            val username = binding.registerUsername.text.toString()
            val email  = binding.registerEmail.text.toString()
            val password = binding.registerPassword.text.toString()

            auth.createUserWithEmailAndPassword( email, password)
                .addOnCompleteListener (this) {task ->
                    if (task.isSuccessful)
                    {
                        Log.d("RegisterActivity", "email: ${email}, username: ${username}")
                        // Menyimpan email dan username pada SharedPreferences
                        val editor = sharedPreferences.edit()
                        editor.putString("email", email)
                        editor.putString("username", username)
                        editor.apply()

                        Toast.makeText(this, "Berhasil buat akun", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }

                }
        }
    }
}

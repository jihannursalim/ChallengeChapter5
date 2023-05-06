package com.example.challenge5

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge5.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        auth = FirebaseAuth.getInstance()

        binding!!.btnLogin.setOnClickListener {
            val email = binding!!.etEmail.text.toString()
            val password = binding!!.etPassLogin.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Login sukses
                            val intent = Intent(this, Home::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // Login gagal
                            Toast.makeText(this, "Email atau password salah", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                // Email atau password kosong
                Toast.makeText(this, "Email dan password harus diisi", Toast.LENGTH_SHORT).show()
            }
        }

        binding!!.txtRegist.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        //TestCrash
        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))

        //Localization
        // Menambahkan listener pada tombol dengan id btnChange
        findViewById<View>(R.id.btnChange).setOnClickListener {
            // Mendapatkan konfigurasi saat ini
            val config = resources.configuration
            // Mendapatkan bahasa saat ini
            val lang = config.locale.language
            // Mengecek apakah bahasa saat ini adalah bahasa Inggris
            if (lang == "en") {
                // Jika bahasa saat ini adalah bahasa Inggris, maka ganti ke bahasa Indonesia
                setLocale("in")
            } else {
                // Jika bahasa saat ini bukan bahasa Inggris, maka ganti ke bahasa Inggris
                setLocale("en")
            }
        }

    }

    private fun setLocale(lang: String) {
        // Mengatur konfigurasi bahasa baru
        val config = Configuration()
        config.locale = Locale(lang)
        resources.updateConfiguration(config, resources.displayMetrics)
        // Memulai ulang activity untuk memuat ulang tampilan dengan bahasa yang baru
        recreate()
    }
}
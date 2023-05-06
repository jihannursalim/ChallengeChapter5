package com.example.challenge5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge5.databinding.ActivityProfilBinding
import com.google.firebase.auth.FirebaseAuth

class ProfilActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfilBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // Ambil informasi user yang sudah login
        val user = auth.currentUser

        // Set nama lengkap dan username ke TextView
        binding.fullName.text = user?.displayName
        binding.userName.text = user?.email

        // mengambil nilai dari intent
        val fullName = intent.getStringExtra("fullName")
        val userName = intent.getStringExtra("userName")
        val password = intent.getStringExtra("password")
        val selectedImageUri = intent.getParcelableExtra<Uri>("selectedImageUri")


        // mengisikan nilai ke TextView
        findViewById<TextView>(R.id.fullName).text = fullName
        findViewById<TextView>(R.id.userName).text = userName
        findViewById<ImageView>(R.id.imgProfil).setImageURI(selectedImageUri)

        // set onClickListener untuk tombol "Update"
        findViewById<Button>(R.id.btnUpdate).setOnClickListener {
            // mengambil nilai dari TextView
            val fullName = findViewById<TextView>(R.id.fullName).text.toString()
            val userName = findViewById<TextView>(R.id.userName).text.toString()

            // mengirim nilai ke UpdateUser
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("fullName", fullName)
            intent.putExtra("userName", userName)
            intent.putExtra("password", password)
            intent.putExtra("selectedImageUri", selectedImageUri)
            startActivity(intent)
        }


        // tampilkan gambar di ImageView dengan id imgProfil
        selectedImageUri?.let {
            binding.imgProfil.setImageURI(selectedImageUri)
        }

        // tambahkan onClickListener ke tombol "Update"
        binding.btnUpdate.setOnClickListener {
            // mengambil nilai dari TextView
            val fullName = findViewById<TextView>(R.id.fullName).text.toString()
            val userName = findViewById<TextView>(R.id.userName).text.toString()

// mengirim nilai ke MainActivity
            val intent = Intent(this, UpdateUser::class.java)
            intent.putExtra("fullName", fullName)
            intent.putExtra("userName", userName)
            intent.putExtra("password", password)
            startActivity(intent)
        }

        binding.btnBackHome.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }
    
}
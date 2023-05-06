package com.example.challenge5

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge5.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    private lateinit var imgProfile: ImageView
    lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDataNews()

        // Inisialisasi ImageView
        imgProfile = findViewById(R.id.imgProfile)

        // Tampilkan gambar profil yang telah dipilih atau gambar default
        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val imagePath = sharedPref.getString("imagePath", "")
        if (imagePath != "") {
            val bitmap = BitmapFactory.decodeFile(imagePath)
            imgProfile.setImageBitmap(bitmap)
        }

        // Klik pada ImageView akan membuka halaman profilactivity
        imgProfile.setOnClickListener {
            val intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)
        }
    }

    fun showDataNews (){

        val viewModelNews = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModelNews.callApiNews()
        viewModelNews.liveDataNews.observe(this, {
            if (it != null){
                binding.rvNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvNews.adapter = NewsAdapter(it)
            }
        })

    }

    override fun onResume() {
        super.onResume()
        showDataNews()
    }
}
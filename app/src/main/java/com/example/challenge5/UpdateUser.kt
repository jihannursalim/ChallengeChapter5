package com.example.challenge5

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge5.databinding.ActivityUpdateUserBinding
import java.io.File


class UpdateUser : AppCompatActivity() {

    lateinit var binding : ActivityUpdateUserBinding
    private var selectedImageUri: Uri? = null

    companion object {
        private const val REQUEST_CODE_PICK_IMAGE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnChoosePhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
        }

        binding.btnDelete.setOnClickListener {
            deleteSelectedImage()
        }

        binding.btnDone.setOnClickListener {
            openProfilActivity()
            // mengambil nilai dari EditText
            val fullName = binding.etFullName.text.toString()
            val userName = binding.etUsername.text.toString()

        // mengirim nilai ke ProfilActivity
            val intent = Intent(this, ProfilActivity::class.java)
            intent.putExtra("fullName", fullName)
            intent.putExtra("userName", userName)
            intent.putExtra("selectedImageUri", selectedImageUri)
            startActivity(intent)
        }

        if (selectedImageUri != null) {
            binding.viewImage.setImageURI(selectedImageUri)
        }

        // cek apakah ada data yang dibawa dari ProfilActivity
        if (intent.hasExtra("fullName")) {
            // mengambil nilai yang dikirimkan dari ProfilActivity
            val fullName = intent.getStringExtra("fullName")
            val userName = intent.getStringExtra("userName")
            val password = intent.getStringExtra("password")

// mengisikan nilai ke EditText
            binding.etFullName.setText(fullName)
            binding.etUsername.setText(userName)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data!!
            binding.viewImage.setImageURI(selectedImageUri)
        } else if (resultCode == Activity.RESULT_CANCELED && selectedImageUri != null) {
            binding.viewImage.setImageURI(selectedImageUri)
        }
    }

    // method untuk menghapus gambar yang telah dipilih
    private fun deleteSelectedImage() {
        // hapus gambar hanya jika gambar telah dipilih
        selectedImageUri?.let {
            val file = File(it.path)
            if (file.exists()) {
                file.delete()
            }
        }

        // reset gambar ke gambar default
        binding.viewImage.setImageResource(R.drawable.binar)

        // reset selectedImageUri ke null
        selectedImageUri = null
    }

    // method untuk membuka ProfilActivity dan membawa data gambar yang dipilih
    private fun openProfilActivity() {
        val intent = Intent(this, ProfilActivity::class.java)

        // bawa data gambar ke intent
        selectedImageUri?.let {
            intent.putExtra("selectedImageUri", it)
        }

        startActivity(intent)
    }
}
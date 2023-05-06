package com.example.challenge5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge5.databinding.ActivityDetailBinding

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val getDetail = intent.getSerializableExtra("detail") as ResponseDataNewsItem
//        binding.detailjudulnews.text = getDetail.title
//        binding.detailtglnews.text = getDetail.createdAt
//        binding.newsdesc.text = getDetail.description
//        Glide.with(this).load(getDetail.image).into(binding.detailImgnews)
    }
}

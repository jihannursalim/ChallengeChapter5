package com.example.challenge5

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.networkingwithretrofit.news.ResponseDataNewsItem

class NewsAdapter(var listNews : List<ResponseDataNewsItem>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var onClick :((ResponseDataNewsItem) -> Unit)? = null
    

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var img = view.findViewById<ImageView>(R.id.imgNews)
        var judul = view.findViewById<TextView>(R.id.txtJudul)
        var tanggal = view.findViewById<TextView>(R.id.txtTanggal)
        var card = view.findViewById<CardView>(R.id.cardNews)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)

    }


    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        val id = listNews[position].id.toInt()
        holder.judul.text = listNews[position].title
        holder.tanggal.text = listNews[position].createdAt
        Glide.with(holder.itemView).load(listNews[position].image).into(holder.img)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("news_id", id)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return listNews.size
    }

}
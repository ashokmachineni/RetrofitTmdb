package com.applaunch.retrofittmdb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter(val context: Context, val popularMovies: List<Movie>):
    RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val popularMovie = popularMovies[position]
        holder.bind(popularMovie)
    }

    override fun getItemCount(): Int = popularMovies.size


    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val textView:TextView
        val imag:ImageView
        init {
            textView = itemView.findViewById(R.id.texts)
            imag = itemView.findViewById(R.id.imgView)
        }
        fun bind(popularMovie: Movie) {
            textView.text = popularMovie.title
            val imagss = "https://image.tmdb.org/t/p/w500"+popularMovie.poster_path
            Glide.with(context).load(imagss).into(imag)
        }
    }

}

package com.example.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carbon_demo.data.Movie
import com.example.carbon_demo.R
import com.example.carbon_demo.api.Routes
import com.example.carbon_demo.databinding.MovieItemBinding

/**
 *@Created by Yerimah on 10/11/2021.
 */
class MovieAdapter(private val context: Context,
                   private val movieList: List<Movie>,
                   private val callback: (Movie, ImageView) -> Unit): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = movieList.size

    inner class MovieViewHolder(private val itemBinding: MovieItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(position: Int) {
            val movie = movieList[position]
            itemBinding.nameTextView.text = movie.title
            Glide.with(context).load(Routes.POSTER_PATH_BASE_URL + movie.poster_path)
                .placeholder(ContextCompat.getDrawable(context, R.drawable.loader_gif))
                .into(itemBinding.posterImageView)
            itemBinding.root.setOnClickListener { callback.invoke(movie, itemBinding.posterImageView) }
        }
    }
}
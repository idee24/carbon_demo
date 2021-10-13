package com.example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.example.carbon_demo.MainActivity
import com.example.carbon_demo.R
import com.example.carbon_demo.api.Routes
import com.example.carbon_demo.data.Movie
import com.example.carbon_demo.databinding.FragmentMovieItemBinding
import com.google.gson.Gson

/**
 *@Created by Yerimah on 10/11/2021.
 */
class MovieItemFragment : Fragment(R.layout.fragment_movie_item) {

    private lateinit var binding: FragmentMovieItemBinding
    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        val animation = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieItemBinding.bind(view)

        val args = arguments?.getString("current_movie", "")
        val currentMovie = Gson().fromJson(args, Movie::class.java) as Movie

        Glide.with(this).load(Routes.POSTER_PATH_BASE_URL +  currentMovie.poster_path)
            .placeholder(ContextCompat.getDrawable(mainActivity, R.drawable.loader_gif))
            .into(binding.posterImage)

        binding.titleTextView.text = currentMovie.title
        binding.descriptionTextView.text = currentMovie.overview
        binding.languageTextView.text = currentMovie.original_language
        binding.ratingTextView.text = currentMovie.vote_average.toString()
        binding.releaseDateTextView.text = currentMovie.release_date
    }
}
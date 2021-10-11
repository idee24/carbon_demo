package com.example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.carbon_demo.MainActivity
import com.example.carbon_demo.R
import com.example.carbon_demo.data.Movie
import com.example.carbon_demo.databinding.FragmentMovieListBinding
import com.example.carbon_demo.utils.Status
import com.google.gson.Gson
import java.util.*

/**
 *@Created by Yerimah on 10/11/2021.
 */
class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieListBinding.bind(view)


        mainActivity.viewModel.movies.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.SUCCESS -> initRecyclerView(result.data as List<Movie>)
                Status.LOADING -> println("DDLS Loading Call")
                Status.ERROR -> println("DDLS Error Call  ==> ${result.message}")
            }
        }

    }


    private fun initRecyclerView(movieList: List<Movie>) {
        binding.movieRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.movieRecyclerView.adapter = MovieAdapter(mainActivity, movieList, ::onItemSelected)

    }

    private fun onItemSelected(movie: Movie, imageView: ImageView) {
//        val extras = FragmentNavigatorExtras(
//            imageView to "posterImageView"
//        )
        val bundle = Bundle()
        bundle.putString("current_movie", Gson().toJson(movie, Movie::class.java))
        findNavController().navigate(R.id.action_movieListFragment_to_movieItemFragment, bundle)
    }
}
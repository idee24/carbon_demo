package com.example.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.carbon_demo.api.MovieApi
import com.example.carbon_demo.data.Movie
import com.example.carbon_demo.utils.Resource
import com.example.carbon_demo.data.MovieRepository
import com.example.carbon_demo.data.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *@Created by Yerimah on 10/11/2021.
 */

@HiltViewModel
class MovieViewModel @Inject constructor(repository: MovieRepository): ViewModel() {
    val movies = repository.getMovies().asLiveData()
}
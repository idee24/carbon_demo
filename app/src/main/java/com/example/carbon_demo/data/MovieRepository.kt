package com.example.carbon_demo.data


import androidx.lifecycle.liveData
import androidx.room.withTransaction
import com.example.carbon_demo.api.MovieApi
import com.example.carbon_demo.utils.Resource
import com.example.carbon_demo.utils.networkBoundResource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 *@Created by Yerimah on 9/24/2021.
 */

class MovieRepository @Inject constructor(private val moviesApi: MovieApi, private val db: MovieDatabase) {

    private val movieDao = db.movieDao()

    fun getMovies() = networkBoundResource(
        query = { movieDao.getAllMovies() },
        fetch = { moviesApi.getMovies() },
        saveFetchResult = { movies ->
            db.withTransaction {
                movieDao.deleteAllMovies()
                movieDao.insertMovies(movies.results)
            }
        }
    )


}
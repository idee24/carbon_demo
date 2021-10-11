package com.example.carbon_demo.api

import com.example.carbon_demo.data.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *@Created by Yerimah on 10/11/2021.
 */
interface MovieApi {

    companion object {
        const val API_KEY = "a7ad6458b8de544577ddb8d21afa4426"
    }

    @GET(Routes.LIST_END_POINT)
    suspend fun getMovies(
        @Query("api_key") perPage: String = API_KEY
    ): MovieResponse
}
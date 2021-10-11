package com.example.carbon_demo.data

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *@Created by Yerimah on 11/10/2021.
 */

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: Int,
    val original_language: String,
    val overview: String,
    val poster_path: String,
    val title: String,
    val vote_average: Double,
    val release_date: String
)

data class MovieResponse(
    var results: List<Movie>
)
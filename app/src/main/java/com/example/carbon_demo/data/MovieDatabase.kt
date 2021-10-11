package com.example.carbon_demo.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 *@Created by Yerimah on 10/11/2021.
 */

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase(){

    abstract fun movieDao(): MovieDao
}
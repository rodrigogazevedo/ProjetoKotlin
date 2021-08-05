package com.example.projetomovies.models.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieModel::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun movieDao() : MovieModelDao
}
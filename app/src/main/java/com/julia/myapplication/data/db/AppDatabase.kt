package com.julia.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.julia.myapplication.data.model.FavoriteTrack

@Database(entities = [FavoriteTrack::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteTrackDao(): FavoriteTrackDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "favorite_tracks_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
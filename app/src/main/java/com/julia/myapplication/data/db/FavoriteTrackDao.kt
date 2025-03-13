package com.julia.myapplication.data.db

import androidx.room.*
import com.julia.myapplication.data.model.FavoriteTrack

@Dao
interface FavoriteTrackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(track: FavoriteTrack)

    @Delete
    suspend fun deleteFavorite(track: FavoriteTrack)

    @Query("SELECT*FROM favorite_tracks")
    suspend fun getAllFavorites(): List<FavoriteTrack>
}
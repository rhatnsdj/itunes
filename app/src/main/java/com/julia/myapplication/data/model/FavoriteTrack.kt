package com.julia.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_tracks")
data class FavoriteTrack(
    @PrimaryKey val trackId: String,
    val trackName: String,
    val artistName: String,
    val artworkUrl100: String
)

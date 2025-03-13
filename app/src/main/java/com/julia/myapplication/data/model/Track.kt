package com.julia.myapplication.data.model

data class Track(
    val trackId: String? = "",
    val trackName: String,
    val collectionName: String?,
    val artistName: String,
    val artworkUrl100: String
)

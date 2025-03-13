package com.julia.myapplication.data.remote

import com.julia.myapplication.data.model.ITunesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApiService {
    @GET("search")
    suspend fun searchTracks(
        @Query("term") term: String = "apple",
        @Query("entity") entity: String = "song",
        @Query("limit") limit: Int = 50
    ): ITunesResponse
}
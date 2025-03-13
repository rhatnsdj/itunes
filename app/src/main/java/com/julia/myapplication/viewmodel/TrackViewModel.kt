package com.julia.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julia.myapplication.data.model.Track
import com.julia.myapplication.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class TrackViewModel : ViewModel() {

    private val _tracks = MutableLiveData<List<Track>>()
    val tracks: LiveData<List<Track>> get() = _tracks

    private var selectedMediaType: String = "song"

    fun fetchTracks() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.searchTracks(entity = selectedMediaType)
                _tracks.value = response.results.map { track ->
                    track.copy(
                        trackId = track.trackId ?: "UNKNOWN_ID",
                        trackName = track.trackName ?: track.collectionName ?: "Unknown Track",
                        artistName = track.artistName ?: "Unknown Artist",
                        collectionName = track.collectionName ?: "Unknown Album",
                        artworkUrl100 = track.artworkUrl100 ?: ""
                    )
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "API call failure", e)
            }
        }
    }

    fun updateMediaType(mediaType: String) {
        selectedMediaType = mediaType
        fetchTracks()
    }
}
package com.julia.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.julia.myapplication.data.db.AppDatabase
import com.julia.myapplication.data.model.FavoriteTrack
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val favoriteDao = db.favoriteTrackDao()

    private val _favorites = MutableLiveData<List<FavoriteTrack>>()
    val favorites: LiveData<List<FavoriteTrack>> get() = _favorites

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = favoriteDao.getAllFavorites()
        }
    }

    fun addFavorite(track: FavoriteTrack) {
        viewModelScope.launch {
            favoriteDao.insertFavorite(track)
            loadFavorites()
        }
    }

    fun removeFavorite(track: FavoriteTrack) {
        viewModelScope.launch {
            favoriteDao.deleteFavorite(track)
            loadFavorites()
        }
    }

}
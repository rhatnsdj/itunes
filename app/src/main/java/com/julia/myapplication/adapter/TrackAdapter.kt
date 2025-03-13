package com.julia.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.julia.myapplication.R
import com.julia.myapplication.data.model.FavoriteTrack
import com.julia.myapplication.data.model.Track
import com.julia.myapplication.databinding.ItemTrackBinding
import com.julia.myapplication.viewmodel.FavoriteViewModel

class TrackAdapter(
    private val trackList: List<Track>,
    private val favoriteViewModel: FavoriteViewModel
) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

        class TrackViewHolder(private val binding: ItemTrackBinding) :
                RecyclerView.ViewHolder(binding.root) {
                    fun bind(track: Track, favoriteViewModel: FavoriteViewModel) {
                        binding.trackName.text = track.trackName
                        binding.artistName.text = track.artistName

                        Glide.with(binding.artwork)
                            .load(track.artworkUrl100)
                            .into(binding.artwork)

                        val isFavorite = favoriteViewModel.favorites.value?.any { it.trackId == track.trackId } == true
                        binding.favoriteButton.setImageResource(
                            if (isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_border
                        )

                        binding.favoriteButton.setOnClickListener {
                            val favoriteTrack = FavoriteTrack(
                                trackId = track.trackId ?: "UNKNOWN_ID",  // ✅ 기본값 설정
                                trackName = track.trackName ?: "Unknown Track",
                                artistName = track.artistName ?: "Unknown Artist",
                                artworkUrl100 = track.artworkUrl100 ?: ""
                            )

                            if (favoriteViewModel.favorites.value?.any { it.trackId == track.trackId } == true) {
                                favoriteViewModel.removeFavorite(favoriteTrack)
                            } else {
                                favoriteViewModel.addFavorite(favoriteTrack)
                            }

                            binding.favoriteButton.post {
                                val updatedIsFavorite = favoriteViewModel.favorites.value?.any { it.trackId == track.trackId } == true
                                binding.favoriteButton.setImageResource(
                                    if (updatedIsFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_border
                                )
                            }
                        }
                    }
                }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val binding = ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(trackList[position], favoriteViewModel)
    }

    override fun getItemCount(): Int = trackList.size


}
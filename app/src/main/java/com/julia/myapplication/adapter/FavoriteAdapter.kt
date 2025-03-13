package com.julia.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.julia.myapplication.data.model.FavoriteTrack
import com.julia.myapplication.databinding.ItemTrackBinding

class FavoriteAdapter(
    private val favoriteList: List<FavoriteTrack>
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    class FavoriteViewHolder(private val binding: ItemTrackBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(track: FavoriteTrack) {
                    binding.trackName.text = track.trackName
                    binding.artistName.text = track.artistName

                    Glide.with(binding.artwork)
                        .load(track.artworkUrl100)
                        .into(binding.artwork)
                    }
                }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favoriteList[position])
    }

    override fun getItemCount(): Int = favoriteList.size

}
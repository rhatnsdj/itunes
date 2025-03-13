package com.julia.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.julia.myapplication.adapter.FavoriteAdapter
import com.julia.myapplication.adapter.TrackAdapter
import com.julia.myapplication.data.model.Track
import com.julia.myapplication.databinding.FragmentFavoriteBinding
import com.julia.myapplication.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.favorites.observe(viewLifecycleOwner) { favoriteTracks ->
            val convertedList = favoriteTracks.map { favorite ->
                Track (
                    trackId = favorite.trackId,
                    trackName = favorite.trackName,
                    artistName = favorite.artistName,
                    collectionName = null,
                    artworkUrl100 = favorite.artworkUrl100
                        )
            }

            binding.recyclerView.adapter = TrackAdapter(convertedList, viewModel)
        }

        viewModel.loadFavorites()
    }
}
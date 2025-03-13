package com.julia.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.julia.myapplication.R
import com.julia.myapplication.adapter.TrackAdapter
import com.julia.myapplication.databinding.FragmentTrackListBinding
import com.julia.myapplication.viewmodel.FavoriteViewModel
import com.julia.myapplication.viewmodel.TrackViewModel

class TrackListFragment : Fragment() {

    private var _binding: FragmentTrackListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TrackViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrackListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.tracks.observe(viewLifecycleOwner) { trackList ->
            binding.recyclerView.adapter = TrackAdapter(trackList, favoriteViewModel)
        }

        val  spinner: Spinner = binding.spinnerMediaType
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.media_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                val selectedType = parent?.getItemAtPosition(position).toString()
                viewModel.updateMediaType(selectedType)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        viewModel.fetchTracks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
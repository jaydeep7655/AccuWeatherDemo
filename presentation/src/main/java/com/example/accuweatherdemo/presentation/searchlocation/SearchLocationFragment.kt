package com.example.accuweatherdemo.presentation.searchlocation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.accuweatherdemo.BuildConfig
import com.example.accuweatherdemo.databinding.FragmentSearchLocationBinding
import com.example.accuweatherdemo.utils.Status
import com.example.core.Constants
import com.example.domain.model.Location
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchLocationFragment : Fragment() {

    private lateinit var binding: FragmentSearchLocationBinding
    val viewModel: SearchLocationViewModel by viewModel()
    private lateinit var locationListAdapter: LocationListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEvents()
        subscribeToObservers()
        viewModel.listLocation(BuildConfig.ACCUWEATHER_API_KEY)
    }

    private fun setUpEvents() {
        binding.svSearchCity.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    Log.d(TAG, "onQueryTextSubmit : $query")
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(TAG, "onQueryTextChange : $newText")
                locationListAdapter.filter.filter(newText)
                return false
            }
        })

        locationListAdapter = LocationListAdapter { location ->
            findNavController().navigate(
                SearchLocationFragmentDirections.actionSearchLocationFragmentToWeatherDetailsFragment(
                    cityName = location.englishName!!,
                    locationId = location.key!!
                )
            )
        }
        binding.rvCities.adapter = locationListAdapter
    }

    private fun subscribeToObservers() {
        viewModel.locationListState.observe(requireActivity()) {
            when (it.status) {
                Status.LOADING -> {
                    binding.pbLoader.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.pbLoader.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    binding.pbLoader.visibility = View.GONE
                    viewModel.alLocationList = it.data as ArrayList<Location>
                    locationListAdapter.unFilteredList = it.data as ArrayList<Location>
                    locationListAdapter.submitList(it.data)
                }
            }
        }
    }

    companion object {
        val TAG = SearchLocationFragment::class.java.simpleName
    }
}
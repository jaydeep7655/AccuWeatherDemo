package com.example.accuweatherdemo.presentation.weatherDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.accuweatherdemo.BuildConfig
import com.example.accuweatherdemo.databinding.FragmentWeatherDetailsBinding
import com.example.accuweatherdemo.utils.Status
import com.example.core.Constants
import com.example.domain.model.Weather
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherDetailsFragment : Fragment() {

    private lateinit var binding: FragmentWeatherDetailsBinding
    private val args: WeatherDetailsFragmentArgs by navArgs()
    val viewModel: WeatherDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObserver()
        args.locationId?.let {
            viewModel.getWeather(BuildConfig.ACCUWEATHER_API_KEY, it)
        }
        binding.tvCityName.text = args.cityName
    }

    private fun subscribeToObserver() {
        viewModel.weatherDetailState.observe(requireActivity()) {
            when (it.status) {
                Status.LOADING -> {
                    binding.pbLoader.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.pbLoader.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.pbLoader.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) {
                        val weatherList = it.data as List<Weather>
                        weatherList.get(0)?.let {
                            binding.tvWeatherText.text = it.weatherText
                            val metric = it.temperature?.metric
                            binding.tvTemperature.text = "${metric?.value} ${metric?.unit}"
                            it.weatherIcon?.let {
                                Glide.with(requireContext())
                                    .load("https://developer.accuweather.com/sites/default/files/${it}-s.png")
                                    .into(binding.ivWeatherIcon)
                            }

                        }
                    }
                }
            }
        }
    }


}
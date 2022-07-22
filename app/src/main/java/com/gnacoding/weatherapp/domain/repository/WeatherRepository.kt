package com.gnacoding.weatherapp.domain.repository

import com.gnacoding.weatherapp.domain.util.Resource
import com.gnacoding.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}
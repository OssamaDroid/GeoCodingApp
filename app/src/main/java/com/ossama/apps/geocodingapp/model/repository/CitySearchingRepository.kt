package com.ossama.apps.geocodingapp.model.repository

import android.content.Context
import com.ossama.apps.geocodingapp.model.entity.City


interface CitySearchingRepository {
    fun getCities(context: Context, callback: CitySearchingCallback)
}

interface CitySearchingCallback {
    fun onSuccess(cities: List<City>)
    fun onFailure()
}

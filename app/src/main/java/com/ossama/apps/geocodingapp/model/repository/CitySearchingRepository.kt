package com.ossama.apps.geocodingapp.model.repository

import com.ossama.apps.geocodingapp.model.entity.City


interface CitySearchingRepository {
    fun getCities(callback: CitySearchingCallback)
}

interface CitySearchingCallback {
    fun onSuccess(schools: List<City>)
    fun onFailure()
}

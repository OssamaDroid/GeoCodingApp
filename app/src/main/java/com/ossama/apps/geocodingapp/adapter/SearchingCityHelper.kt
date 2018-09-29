package com.ossama.apps.geocodingapp.adapter

import com.ossama.apps.geocodingapp.model.entity.City


// Given a string, a filter is carried out on the list of cities and only the matching ones are returned
fun filterCitiesByName(cities: List<City>, constraint: String) = when {
    constraint.isBlank() -> cities
    else -> cities.filter {
        // The filter does not take the letters' case into consideration
        it.cityName?.toLowerCase()?.startsWith(constraint.toLowerCase()) == true
    }
}
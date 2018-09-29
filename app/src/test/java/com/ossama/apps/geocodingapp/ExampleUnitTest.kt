package com.ossama.apps.geocodingapp

import com.ossama.apps.geocodingapp.adapter.filterCitiesByName
import com.ossama.apps.geocodingapp.model.entity.City
import junit.framework.Assert
import org.junit.Assert.assertTrue
import org.junit.Test


class SearchingCityUnitTests {

    enum class Cities(cityName: String) {
        Hamburg("Hamburg"),
        Vienna("Vienna"),
        Budapest("Budapest"),
        SanFrancisco("San Francisco"),
        Barcelona("Barcelona"),
        Casablanca("Casablanca"),
        Madrid("Madrid"),
        Berlin("Berlin"),
        Hanover("Hanover"),
        Toronto("Toronto"),
    }

    private var cities = listOf(
            City(Cities.Hamburg.name),
            City(Cities.Budapest.name),
            City(Cities.Hanover.name),
            City(Cities.Barcelona.name),
            City(Cities.Vienna.name),
            City(Cities.Berlin.name),
            City(Cities.SanFrancisco.name),
            City(Cities.Casablanca.name),
            City(Cities.Madrid.name),
            City(Cities.Toronto.name)
    )

    @Test
    fun search_city_does_not_exist() {
        val filteredList = filterCitiesByName(cities, "anything")
        assertTrue(filteredList.isEmpty())
    }

    @Test
    fun search_city_with_empty_string() {
        val filteredList = filterCitiesByName(cities, "")
        assertTrue(filteredList.size == cities.size)
    }

    @Test
    fun search_city_with_custom_string_with_lower_case() {
        val filteredList = filterCitiesByName(cities, "ha")
        assertTrue(filteredList.size == 2)
        assertTrue(filteredList.contains(City(Cities.Hamburg.name)))
        assertTrue(filteredList.contains(City(Cities.Hanover.name)))
    }

    @Test
    fun search_city_with_custom_string_with_upper_case() {
        val filteredList = filterCitiesByName(cities, "B")
        assertTrue(filteredList.size == 3)
        assertTrue(filteredList.contains(City(Cities.Berlin.name)))
        assertTrue(filteredList.contains(City(Cities.Budapest.name)))
        assertTrue(filteredList.contains(City(Cities.Barcelona.name)))
    }

    @Test
    fun search_city_with_full_name() {
        val filteredList = filterCitiesByName(cities, "Toronto")
        assertTrue(filteredList.size == 1)
        assertTrue(filteredList[0] == (City(Cities.Toronto.name)))
    }
}

package com.ossama.apps.geocodingapp.model.entity


data class City(
        var cityName: String? = null,
        var latitude: String? = null,
        var longitude: String? = null,
        var countryCode: String? = null
) {
    // This constructor is added only to make creating City objects easier in Unit tests
    constructor(cityName: String) : this(cityName, "", "", "")
}
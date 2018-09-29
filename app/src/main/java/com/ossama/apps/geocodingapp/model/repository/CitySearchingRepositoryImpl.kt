package com.ossama.apps.geocodingapp.model.repository

import android.content.Context
import com.ossama.apps.geocodingapp.R
import com.ossama.apps.geocodingapp.model.entity.City
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset


class CitySearchingRepositoryImpl : CitySearchingRepository {

    companion object {
        private const val DELIMITER = ";"
    }

    override fun getCities(context: Context, callback: CitySearchingCallback) {

        val inputStream = context.resources.openRawResource(R.raw.world_cities)
        val reader = BufferedReader(InputStreamReader(inputStream, Charset.forName("UTF-8")))

        var line: String?
        val cities = ArrayList<City>()

        try {
            reader.readLine()
            line = reader.readLine()
            while (line != null) {

                val tokens = line.split(DELIMITER)

                val city = City()
                city.cityName = tokens[0]
                city.latitude = tokens[2]
                city.longitude = tokens[3]
                city.countryCode = tokens[6]

                cities.add(city)

                line = reader.readLine()
            }
            callback.onSuccess(cities)
        } catch (e: IOException) {
            callback.onFailure()
        }
    }
}
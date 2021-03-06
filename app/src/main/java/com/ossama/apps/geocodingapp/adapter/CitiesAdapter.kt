package com.ossama.apps.geocodingapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.LinearLayout
import android.widget.TextView
import com.ossama.apps.geocodingapp.R
import com.ossama.apps.geocodingapp.model.entity.City


class CitiesAdapter(private var context: Context, private var cities: List<City>) : RecyclerView.Adapter<CitiesAdapter.CitiesListViewHolder>(), Filterable {

    private lateinit var filteredCities: List<City>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CitiesListViewHolder(view)
    }

    override fun getItemCount() = filteredCities.size

    override fun onBindViewHolder(holder: CitiesListViewHolder, position: Int) {
        val city = filteredCities[position]

        // Set the city name
        holder.cityName.text = city.cityName

        // Set the city geolocation
        holder.cityGeoLocation.text = context.getString(R.string.city_geo_location, city.latitude, city.longitude)

        // Set the city's country code
        holder.cityCountryCode.text = city.countryCode
    }

    fun setCities(cities: List<City>) {
        this.cities = cities
        this.filteredCities = cities
        notifyDataSetChanged()
    }

    class CitiesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityItemContainer = itemView.findViewById<LinearLayout>(R.id.city_item_container)!!
        val cityName = itemView.findViewById<TextView>(R.id.city_name)!!
        val cityGeoLocation = itemView.findViewById<TextView>(R.id.city_geo)!!
        val cityCountryCode = itemView.findViewById<TextView>(R.id.city_country_code)!!
    }

    override fun getFilter(): Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val typedText = constraint.toString()
            filteredCities = filterCitiesByName(cities, typedText)

            val filterResults = FilterResults()
            filterResults.values = filteredCities

            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            results?.values?.let {
                filteredCities = results.values as List<City>
                notifyDataSetChanged()
            }
        }
    }
}
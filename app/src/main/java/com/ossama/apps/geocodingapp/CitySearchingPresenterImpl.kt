package com.ossama.apps.geocodingapp

import com.ossama.apps.geocodingapp.model.entity.City
import com.ossama.apps.geocodingapp.model.repository.CitySearchingCallback
import com.ossama.apps.geocodingapp.model.repository.CitySearchingRepository
import com.ossama.apps.geocodingapp.model.repository.CitySearchingRepositoryImpl


class CitySearchingPresenterImpl : ICitySearchingPresenter {

    private var citySearchingView: ICitySearchingView? = null
    private val citySearchingRepository: CitySearchingRepository by lazy {
        CitySearchingRepositoryImpl()
    }

    override fun getCities() {
        citySearchingView?.getViewContext()?.let {

            // Show the Progress Bar
            citySearchingView?.showLoadingView()

            // Fetch the cities
            citySearchingRepository.getCities(it, object: CitySearchingCallback {
                override fun onSuccess(cities: List<City>) {
                    // Success
                    when {
                        // List Not empty
                        cities.isNotEmpty() -> citySearchingView?.showCities(cities)

                        // List empty
                        else -> citySearchingView?.showEmptyState()
                    }
                }

                override fun onFailure() {
                    // Failure
                    citySearchingView?.showError()
                }
            })
        }
    }

    override fun attachView(view: ICitySearchingView) {
        citySearchingView = view
    }

    override fun detachView() {
        citySearchingView = null
    }
}
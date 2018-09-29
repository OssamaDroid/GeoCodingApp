package com.ossama.apps.geocodingapp

import android.content.Context
import com.ossama.apps.geocodingapp.base.BasePresenter
import com.ossama.apps.geocodingapp.base.BaseView
import com.ossama.apps.geocodingapp.model.entity.City


interface ICitySearchingPresenter : BasePresenter<ICitySearchingView> {
    fun getCities()
}

interface ICitySearchingView : BaseView {
    fun showLoadingView()
    fun showCities(cities: List<City>)
    fun showEmptyState()
    fun showError()
    fun getViewContext(): Context?
}
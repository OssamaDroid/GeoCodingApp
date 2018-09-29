package com.ossama.apps.geocodingapp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ossama.apps.geocodingapp.adapter.CitiesAdapter
import com.ossama.apps.geocodingapp.adapter.CustomDividerItemDecoration
import com.ossama.apps.geocodingapp.model.entity.City
import kotlinx.android.synthetic.main.fragment_list_cities.*


class CitySearchingViewImpl : Fragment(), ICitySearchingView {

    private lateinit var presenter: ICitySearchingPresenter
    private lateinit var adapter: CitiesAdapter

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment.
         *
         * @return A new instance of fragment CitySearchingViewImpl.
         */

        fun newInstance(): CitySearchingViewImpl = CitySearchingViewImpl()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = CitySearchingPresenterImpl()
        adapter = CitiesAdapter(ArrayList())

        presenter.attachView(this)
        presenter.getCities()

        activity?.let {
            list_cities.addItemDecoration(CustomDividerItemDecoration(it, DividerItemDecoration.VERTICAL, 32))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_cities, container, false)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun showLoadingView() {
        list_cities.visibility = View.GONE
        no_data_text.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }

    override fun showCities(cities: List<City>) {
        no_data_text.visibility = View.GONE
        progress_bar.visibility = View.GONE
        list_cities.visibility = View.VISIBLE

        adapter.setCities(cities)
        list_cities.adapter = adapter
    }

    override fun showEmptyState() {
        list_cities.visibility = View.GONE
        progress_bar.visibility = View.GONE
        no_data_text.visibility = View.VISIBLE
    }

    override fun showError() {
        // TODO Handle the error correctly
        showEmptyState()
    }

    override fun getViewContext(): Context? = activity
}
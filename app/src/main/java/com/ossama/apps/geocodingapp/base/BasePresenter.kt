package com.ossama.apps.geocodingapp.base


/**
 * Base interface that any class that wants to act as a Presenter in the MVP (Model View Presenter) pattern must implement.
 */

interface BasePresenter<in V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}
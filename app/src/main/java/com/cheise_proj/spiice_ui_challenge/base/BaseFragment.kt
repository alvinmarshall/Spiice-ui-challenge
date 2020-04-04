package com.cheise_proj.spiice_ui_challenge.base

import android.view.View
import com.cheise_proj.presentation.factory.ViewModelFactory
import com.cheise_proj.presentation.utils.IPreference
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {
    @Inject
    lateinit var prefs: IPreference

    @Inject
    lateinit var factory: ViewModelFactory

    fun showProgress(view: View) {
        view.visibility = View.VISIBLE
    }

    fun hideProgress(view: View) {
        view.visibility = View.GONE
    }
}
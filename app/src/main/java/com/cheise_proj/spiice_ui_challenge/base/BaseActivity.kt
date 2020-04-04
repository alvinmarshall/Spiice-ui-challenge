package com.cheise_proj.spiice_ui_challenge.base

import com.cheise_proj.presentation.utils.IPreference
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(){
    @Inject
    lateinit var prefs:IPreference
}
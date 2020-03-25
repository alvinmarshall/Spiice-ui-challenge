package com.cheise_proj.spiice_ui_challenge.onBoarding.ui.onBoard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cheise_proj.spiice_ui_challenge.onBoarding.model.OnBoardSlide

class OnBoardViewModel : ViewModel() {

    val setData = MutableLiveData<List<OnBoardSlide>>()
    val getData: LiveData<List<OnBoardSlide>> = setData


}
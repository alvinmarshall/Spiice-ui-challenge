package com.cheise_proj.spiice_ui_challenge.spiice.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cheise_proj.spiice_ui_challenge.spiice.model.Project
import com.github.mikephil.charting.data.BarEntry

class FeedViewModel : ViewModel() {

    private val _setData = MutableLiveData<List<BarEntry>>().apply {
        value = arrayListOf(
            BarEntry(1f, 0f),
            BarEntry(5f, 50f),
            BarEntry(10f, 0f),
            BarEntry(15f, 75f),
            BarEntry(20f, 0f),
            BarEntry(25f, 100f),
            BarEntry(30f, 0f),
            BarEntry(35f, 80f),
            BarEntry(40f, 0f)

        )

    }
    val getData: LiveData<List<BarEntry>> = _setData

    private val _setProject = MutableLiveData<List<Project>>().apply {
        value = arrayListOf(
            Project(
                id = "1",
                status = 1,
                header = "Wireframes",
                owner = "Francisco Fisher",
                postId = "3"
            ),
            Project(
                id = "2",
                status = 0,
                header = "Create an application",
                owner = "Arlene Mckinney",
                postId = "1"
            ),
            Project(
                id = "3",
                status = 0,
                header = "Mobile Development",
                owner = "Wade Mccoy",
                postId = "2"
            )
        )
    }
    val getProject = _setProject
}
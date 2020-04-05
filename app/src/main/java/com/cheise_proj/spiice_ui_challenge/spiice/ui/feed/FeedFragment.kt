package com.cheise_proj.spiice_ui_challenge.spiice.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.common.ItemClickListener
import com.cheise_proj.spiice_ui_challenge.spiice.model.Project
import com.cheise_proj.spiice_ui_challenge.spiice.ui.feed.adapter.ProjectAdapter
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.fragment_feed.*
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class FeedFragment : Fragment() {
    private lateinit var viewModel: FeedViewModel
    private lateinit var adapter: ProjectAdapter
    private val callBack = object : ItemClickListener<Project> {
        override fun data(t: Project) {
            val action = FeedFragmentDirections.actionFeedFragmentToCurrentProjectFragment(t)
            findNavController().navigate(action)
            Timber.i("actionFeedFragmentToCurrentProjectFragment")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        configViewModel()
    }

    private fun initRecyclerView() {
        adapter = ProjectAdapter().apply {
            setItemCallback(callBack)
        }
        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        viewModel.getData.observe(viewLifecycleOwner, Observer { entries ->
            initBarChart(entries)
        })
        viewModel.getProject.observe(viewLifecycleOwner, Observer { project ->
            adapter.submitList(project)
            recycler_view.adapter = adapter
        })
    }


    private fun initBarChart(entries: List<BarEntry>) {

        val dataSet = BarDataSet(entries, "")
        dataSet.colors = arrayListOf(
            ContextCompat.getColor(context!!, R.color.colorSecondaryColor),
            ContextCompat.getColor(context!!, R.color.colorSecondaryColor),
            ContextCompat.getColor(context!!, R.color.colorSecondaryColor),
            ContextCompat.getColor(context!!, R.color.colorSecondaryColor),
            ContextCompat.getColor(context!!, R.color.colorSecondaryColor),
            ContextCompat.getColor(context!!, R.color.colorSecondaryColor),
            ContextCompat.getColor(context!!, R.color.colorSecondaryColor),
            ContextCompat.getColor(context!!, R.color.colorPrimary),
            ContextCompat.getColor(context!!, R.color.colorSecondaryColor)
        )
        val barData = BarData(dataSet)
        barData.barWidth = 5f
        dataSet.setDrawValues(false)


        chart_bar.apply {
            data = barData
            description.isEnabled = false
            axisLeft.setDrawLabels(false)
            axisRight.setDrawLabels(false)
            xAxis.setDrawLabels(false)
            axisLeft.setDrawGridLines(false)
            axisRight.setDrawGridLines(false)
            xAxis.setDrawGridLines(false)
            legend.isEnabled = false
            xAxis.setDrawAxisLine(false)
            axisLeft.isEnabled = false
            axisRight.isEnabled = false
        }
    }

}

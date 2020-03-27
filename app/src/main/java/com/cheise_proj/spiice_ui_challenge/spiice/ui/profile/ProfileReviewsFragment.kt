package com.cheise_proj.spiice_ui_challenge.spiice.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.spiice.ui.profile.adapter.ReviewAdapter
import kotlinx.android.synthetic.main.fragment_profile_reviews.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileReviewsFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var adapter: ReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_reviews, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        configViewModel()
    }

    private fun initRecyclerView() {
        adapter = ReviewAdapter()
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = adapter
        }
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.getProfile.observe(viewLifecycleOwner, Observer { profile ->
            adapter.submitList(profile.reviews)
            recycler_view.adapter = adapter
            hideProgress()
        })

    }

    private fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

}

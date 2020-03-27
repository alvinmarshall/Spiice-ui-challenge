package com.cheise_proj.spiice_ui_challenge.spiice.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.common.GlideApp
import com.cheise_proj.spiice_ui_challenge.spiice.model.Portfolio
import com.cheise_proj.spiice_ui_challenge.spiice.ui.profile.adapter.PortfolioAdapter
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var adapter: PortfolioAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        configViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_view_all.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToProfileReviewsFragment()
            findNavController().navigate(action)
        }
    }

    private fun initRecyclerView() {
        adapter = PortfolioAdapter()
        recycler_view.apply {
            layoutManager = GridLayoutManager(context, 2)
            hasFixedSize()
            this.adapter = adapter
        }
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.getProfile.observe(viewLifecycleOwner, Observer { profile ->
            with(profile) {
                val sender = "- ${reviews.firstOrNull()?.name}"
                val reviewNumber = "${reviews.size} reviews"
                tv_name.text = name
                tv_job_title.text = jobTitle
                tv_description_body.text = description
                rb_rating.rating = reviews.firstOrNull()?.ratingNumber!!
                tv_review_title.text = reviews.firstOrNull()?.review
                tv_review_sender.text = sender
                tv_review_number.text = reviewNumber
                setProfileAvatar(avatarUrl)
                setPortfolioData(portfolio)
            }
        })
    }

    private fun setProfileAvatar(avatarUrl: String) {
        GlideApp.with(context!!).load(avatarUrl).circleCrop().into(img_avatar)
    }

    private fun setPortfolioData(portfolio: List<Portfolio>) {
        adapter.submitList(portfolio)
        recycler_view.adapter = adapter
    }


}

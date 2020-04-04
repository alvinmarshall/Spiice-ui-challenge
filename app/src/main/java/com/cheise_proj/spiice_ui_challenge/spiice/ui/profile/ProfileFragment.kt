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
import com.cheise_proj.presentation.model.Portfolio
import com.cheise_proj.presentation.viewmodel.UserViewModel
import com.cheise_proj.presentation.vo.STATUS
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.base.BaseFragment
import com.cheise_proj.spiice_ui_challenge.common.GlideApp
import com.cheise_proj.spiice_ui_challenge.spiice.ui.profile.adapter.PortfolioAdapter
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.support.v4.toast
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : BaseFragment() {
    private lateinit var adapter: PortfolioAdapter
    private lateinit var viewModel: UserViewModel

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
            isNestedScrollingEnabled = false
            this.adapter = adapter
        }
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]
        subscribeObserver()
    }

    private fun subscribeObserver() {

        viewModel.getProfile().observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                STATUS.LOADING -> Timber.i("loading...")
                STATUS.SUCCESS -> {
                    Timber.i("profile- ${resource.data}")
                    with(resource.data) {
                        val sender = "- ${this?.reviews?.firstOrNull()?.sender?.name}"
                        val reviewNumber = "${this?.reviews?.size} reviews"
                        tv_name.text = this?.user?.name
                        tv_job_title.text = this?.jobTitle
                        tv_description_body.text = this?.description
                        rb_rating.rating = this?.reviews?.firstOrNull()?.rating ?: 0f
                        tv_review_title.text = this?.reviews?.firstOrNull()?.content?:""
                        tv_review_sender.text = sender
                        tv_review_number.text = reviewNumber
                        this?.user?.avatarUrl.let { setProfileAvatar(it?:"") }
                        setPortfolioData(this?.portfolio)
                    }
                    hideProgress(progressBar)
                }
                STATUS.ERROR -> {
                    hideProgress(progressBar)
                    Timber.i("error- ${resource.message}")
                    toast("${resource.message}")
                }
            }


        })
    }

    private fun setProfileAvatar(avatarUrl: String) {
        GlideApp.with(context!!).load(avatarUrl).circleCrop().into(img_avatar)
    }

    private fun setPortfolioData(portfolio: List<Portfolio>?) {
        adapter.submitList(portfolio)
        recycler_view.adapter = adapter
    }


}

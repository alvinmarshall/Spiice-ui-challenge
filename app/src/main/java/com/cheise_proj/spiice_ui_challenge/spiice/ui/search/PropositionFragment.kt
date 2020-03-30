package com.cheise_proj.spiice_ui_challenge.spiice.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.common.GlideApp
import kotlinx.android.synthetic.main.fragment_proposition.*

/**
 * A simple [Fragment] subclass.
 */
class PropositionFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    private val args: PropositionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_proposition, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configViewModel()
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        viewModel.getPostById(args.propositionId!!).observe(viewLifecycleOwner, Observer { post ->
            with(post) {
                tv_name.text = user.name
                GlideApp.with(context!!).load(user.avatarUrl).circleCrop().into(img_avatar)
            }

        })
    }

}

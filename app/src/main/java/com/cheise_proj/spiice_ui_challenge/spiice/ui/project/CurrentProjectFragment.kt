package com.cheise_proj.spiice_ui_challenge.spiice.ui.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.common.GlideApp
import com.cheise_proj.spiice_ui_challenge.spiice.model.Post
import com.cheise_proj.spiice_ui_challenge.spiice.ui.search.SearchViewModel
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_current_project.*
import kotlinx.android.synthetic.main.fragment_post_detail.*

/**
 * A simple [Fragment] subclass.
 */
class CurrentProjectFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    private val args: CurrentProjectFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_project, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_proposition.setOnClickListener {
            val action =
                CurrentProjectFragmentDirections.actionCurrentProjectFragmentToUploadWorkFragment(
                    args.project.postId
                )
            findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configViewModel()

    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        with(args.project) {
            viewModel.getPostById(postId).observe(viewLifecycleOwner, Observer { post ->
                with(post) {
                    val deadline = "Deadline $elapseTime"
                    val propositionFormatted = "$proposition propositions"
                    setUserAvatar()
                    tv_name.text = user.name
                    tv_header.text = header
                    tv_content.text = content
                    tv_propositions.text = propositionFormatted
                    tv_timestamp.text = timestamp
                    tv_amount.text = amount
                    btn_proposition.text = getString(R.string.btn_label_send_work)
                    tv_elapse_time.text = deadline
                    setChips()

                }
            })

        }
    }

    private fun Post.setChips() {
        for (i in skills.indices) {
            val chip = Chip(context)
            chip.text = skills[i]
            chip.chipCornerRadius = 10f
            chip.isCheckable = false
            chip_group.addView(chip)
        }
    }

    private fun Post.setUserAvatar() {
        GlideApp.with(context!!).load(user.avatarUrl).circleCrop().into(img_avatar)
    }


}

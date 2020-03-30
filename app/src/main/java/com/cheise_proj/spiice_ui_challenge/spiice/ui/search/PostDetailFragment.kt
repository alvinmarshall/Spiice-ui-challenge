package com.cheise_proj.spiice_ui_challenge.spiice.ui.search

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
import com.cheise_proj.spiice_ui_challenge.spiice.model.User
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_post_detail.*

/**
 * A simple [Fragment] subclass.
 */
class PostDetailFragment : Fragment() {

    private val args: PostDetailFragmentArgs by navArgs()
    private lateinit var viewModel: SearchViewModel
    private var postId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_proposition.setOnClickListener {
            val action =
                PostDetailFragmentDirections.actionPostDetailFragmentToPropositionFragment(postId)
            findNavController().navigate(action)

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configViewModel()
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        subscribeObserver()
    }

    private fun subscribeObserver() {
        args.postId?.let { identifier ->
            viewModel.getPostById(identifier).observe(viewLifecycleOwner, Observer { post ->
                with(post) {
                    val propositionFormatted = "$proposition propositions"
                    tv_name.text = user.name
                    tv_header.text = header
                    tv_content.text = content
                    tv_propositions.text = propositionFormatted
                    tv_timestamp.text = timestamp
                    tv_amount.text = amount
                    setAvatarImage()
                    setSkillChipView()
                    setPostId(id)
                }
            })

        }

    }

    private fun setPostId(id: String) {
        postId = id
    }


    private fun Post.setSkillChipView() {
        for (i in skills.indices) {
            val chip = Chip(context)
            chip.text = skills[i]
            chip.chipCornerRadius = 10f
            chip.isCheckable = false
            chip_group.addView(chip)
        }
    }

    private fun Post.setAvatarImage() {
        GlideApp.with(context!!).load(user.avatarUrl).circleCrop().into(img_avatar)
    }

}

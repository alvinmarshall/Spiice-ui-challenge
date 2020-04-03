package com.cheise_proj.spiice_ui_challenge.spiice.ui.project

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
import com.cheise_proj.spiice_ui_challenge.spiice.ui.search.SearchViewModel
import kotlinx.android.synthetic.main.fragment_upload_work.*
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class UploadWorkFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    private val args: UploadWorkFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upload_work, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ll_item_2.visibility = View.GONE
        btn_cancel.setOnClickListener {
            ll_item_2.visibility = View.GONE
        }
        btn_upload.setOnClickListener {
            ll_item_2.visibility = View.VISIBLE
        }
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.getPostById(args.postId).observe(viewLifecycleOwner, Observer { post ->
            val filename = "wireframes.sketch"
            with(post) {
                tv_name.text = user.name
                tv_file_name.text = filename
                GlideApp.with(context!!).load(user.avatarUrl).circleCrop().into(img_avatar)
            }
        })
    }

}

package com.cheise_proj.spiice_ui_challenge.spiice.ui.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.common.DELAY_TIME
import com.cheise_proj.spiice_ui_challenge.common.ItemClickListener
import com.cheise_proj.spiice_ui_challenge.spiice.ui.search.adapter.SearchAdapter
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: SearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        configViewModel()
    }

    private fun initRecyclerView() {
        adapter = SearchAdapter().apply {
            setClickCallback(callback)
        }
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            this.adapter = adapter
            isNestedScrollingEnabled = false
        }
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ subscribeObserver() }, DELAY_TIME)
    }

    private fun subscribeObserver() {
        viewModel.getPost.observe(viewLifecycleOwner, Observer { post ->
            hideProgress(progressBar)
            adapter.submitList(post)
            recycler_view.adapter = adapter

        })

    }

    private val callback = object : ItemClickListener<String> {
        override fun data(t: String) {
            val action = SearchFragmentDirections.actionSearchFragmentToPostDetailFragment(t)
            findNavController().navigate(action)
        }

    }

    private fun hideProgress(view: View) {
        view.visibility = View.GONE
    }

}

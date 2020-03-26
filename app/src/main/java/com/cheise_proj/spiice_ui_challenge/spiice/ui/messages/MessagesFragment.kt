package com.cheise_proj.spiice_ui_challenge.spiice.ui.messages

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
import com.cheise_proj.spiice_ui_challenge.spiice.ui.messages.adapter.MessageAdapter
import kotlinx.android.synthetic.main.fragment_messages.*

/**
 * A simple [Fragment] subclass.
 */
class MessagesFragment : Fragment() {
    private lateinit var viewModel: MessagesViewModel
    private lateinit var adapter: MessageAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        configViewModel()
    }

    private fun initRecyclerView() {
        adapter = MessageAdapter()
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this)[MessagesViewModel::class.java]
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.getMessages.observe(viewLifecycleOwner, Observer { messages ->
            adapter.submitList(messages)
            recycler_view.adapter = adapter
            hideProgress()
        })
    }

    private fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

}

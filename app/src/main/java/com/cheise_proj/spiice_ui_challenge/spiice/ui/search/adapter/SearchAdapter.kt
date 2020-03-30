package com.cheise_proj.spiice_ui_challenge.spiice.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.common.GlideApp
import com.cheise_proj.spiice_ui_challenge.common.ItemClickListener
import com.cheise_proj.spiice_ui_challenge.spiice.model.Post
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.list_search_items.view.*

class SearchAdapter :
    ListAdapter<Post, SearchAdapter.SearchVh>(SearchDiff()) {
    private var itemClickListener: ItemClickListener<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchVh {
        return SearchVh(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_search_items, parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchVh, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    internal fun setClickCallback(callback: ItemClickListener<String>) {
        itemClickListener = callback
    }

    inner class SearchVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            item: Post?,
            itemClickListener: ItemClickListener<String>?
        ) {
            val proposition = "${item?.proposition} propositions"
            itemView.apply {
                tv_header.text = item?.header
                tv_sub_head.text = item?.title
                tv_timestamp.text = item?.timestamp
                tv_name.text = item?.user?.name
                tv_content.text = item?.content
                tv_propositions.text = proposition
                tv_amount.text = item?.amount

                for (i in item?.skills?.indices!!) {
                    val chip = Chip(context)
                    chip.text = item.skills[i]
                    chip.isCheckable = false
                    chip.chipCornerRadius = 15f
                    chip_group.addView(chip)
                }
                setOnClickListener {
                    itemClickListener?.data(item.id)
                }

                GlideApp.with(context).load(item.user.avatarUrl).circleCrop().into(img_avatar)
            }

        }
    }
}

class SearchDiff : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}
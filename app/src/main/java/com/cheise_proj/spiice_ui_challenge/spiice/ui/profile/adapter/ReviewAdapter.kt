package com.cheise_proj.spiice_ui_challenge.spiice.ui.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.spiice.model.Review
import kotlinx.android.synthetic.main.list_reviews.view.*

class ReviewAdapter :
    ListAdapter<Review, ReviewAdapter.ReviewVh>(ReviewDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewVh {
        return ReviewVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_reviews, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewVh, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ReviewVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Review?) {
            itemView.apply {
                tv_name.text = item?.name
                tv_content.text = item?.review
                rb_rating.rating = item?.ratingNumber ?: 0f
                tv_timestamp.text = item?.timestamp
            }
        }
    }
}

class ReviewDiff : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}
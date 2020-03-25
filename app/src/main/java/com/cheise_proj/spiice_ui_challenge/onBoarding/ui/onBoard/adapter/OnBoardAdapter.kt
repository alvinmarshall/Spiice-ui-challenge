package com.cheise_proj.spiice_ui_challenge.onBoarding.ui.onBoard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.onBoarding.model.OnBoardSlide
import kotlinx.android.synthetic.main.swipe_items.view.*

class OnBoardAdapter :
    ListAdapter<OnBoardSlide, OnBoardAdapter.OnBoardVh>(OnBoardDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardVh {
        return OnBoardVh(
            LayoutInflater.from(parent.context).inflate(R.layout.swipe_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: OnBoardVh, position: Int) {
        holder.bind(getItem(position))
    }

    inner class OnBoardVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: OnBoardSlide?) {
            itemView.apply {
                tv_header.text = item?.header
                tv_footer.text = item?.footer
                item_img.setImageResource(item?.icon!!)
            }
        }
    }
}

class OnBoardDiff : DiffUtil.ItemCallback<OnBoardSlide>() {
    override fun areItemsTheSame(oldItem: OnBoardSlide, newItem: OnBoardSlide): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OnBoardSlide, newItem: OnBoardSlide): Boolean {
        return oldItem == newItem
    }
}
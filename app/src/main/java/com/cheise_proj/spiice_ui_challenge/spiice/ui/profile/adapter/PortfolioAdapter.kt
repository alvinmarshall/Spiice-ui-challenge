package com.cheise_proj.spiice_ui_challenge.spiice.ui.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.common.GlideApp
import com.cheise_proj.spiice_ui_challenge.spiice.model.Portfolio
import kotlinx.android.synthetic.main.list_portfolio.view.*

class PortfolioAdapter :
    ListAdapter<Portfolio, PortfolioAdapter.PortfolioVh>(PortfolioDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioVh {
        return PortfolioVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_portfolio, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PortfolioVh, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PortfolioVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Portfolio?) {
            itemView.apply {
                GlideApp.with(context).load(item?.screenShotUrl).centerCrop()
                    .into(item_img)
            }
        }
    }
}

class PortfolioDiff : DiffUtil.ItemCallback<Portfolio>() {
    override fun areItemsTheSame(oldItem: Portfolio, newItem: Portfolio): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Portfolio, newItem: Portfolio): Boolean {
        return oldItem == newItem
    }
}
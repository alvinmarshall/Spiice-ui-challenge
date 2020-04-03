package com.cheise_proj.spiice_ui_challenge.spiice.ui.feed.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.common.ItemClickListener
import com.cheise_proj.spiice_ui_challenge.spiice.model.Project
import kotlinx.android.synthetic.main.list_project.view.*

class ProjectAdapter :
    ListAdapter<Project, ProjectAdapter.ProjectVh>(ProjectDiff()) {
    private var itemClickListener: ItemClickListener<Project>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectVh {
        return ProjectVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_project, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProjectVh, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    internal fun setItemCallback(callback: ItemClickListener<Project>) {
        itemClickListener = callback
    }

    inner class ProjectVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Project?, itemClickListener: ItemClickListener<Project>?) {
            val status = if (item?.status == 1) "Active" else "Pending"
            itemView.apply {
                tv_header.text = item?.header
                tv_sub_head.text = item?.owner
                tv_status.text = status
                setOnClickListener {
                    itemClickListener?.data(item!!)
                }
            }
        }
    }
}

class ProjectDiff : DiffUtil.ItemCallback<Project>() {
    override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem == newItem
    }
}
package com.cheise_proj.spiice_ui_challenge.spiice.ui.messages.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.common.GlideApp
import com.cheise_proj.spiice_ui_challenge.spiice.model.Message
import kotlinx.android.synthetic.main.list_messages.view.*

class MessageAdapter :
    ListAdapter<Message, MessageAdapter.MessageVh>(MessageDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageVh {
        return MessageVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_messages, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MessageVh, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class MessageVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            item: Message?,
            position: Int
        ) {
            itemView.apply {
                tv_name.text = item?.user?.name
                tv_message.text = item?.content
                GlideApp.with(context).load(item?.user?.avatarUrl).circleCrop().into(item_img)
            }
            setOddCardsBackground(position)

        }

        private fun setOddCardsBackground(position: Int) {
            if (position % 2 != 0) {
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.card_module_background
                    )
                )
            }
        }
    }
}

class MessageDiff : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }
}
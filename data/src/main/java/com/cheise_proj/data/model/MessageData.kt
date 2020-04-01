package com.cheise_proj.data.model

import com.cheise_proj.data.mapper.message.MessageDataEntityMapper


data class MessageData(
    val id: String,
    val content: String,
    val sender: UserData,
    val receiver: String,
    val timestamp: String
) {
    companion object {
        fun messageMapper() = MessageDataEntityMapper()
    }
}
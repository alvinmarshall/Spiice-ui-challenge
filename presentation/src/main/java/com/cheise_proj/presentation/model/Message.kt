package com.cheise_proj.presentation.model

import com.cheise_proj.presentation.mapper.message.MessageEntityMapper


data class Message(
    val id: String,
    val content: String,
    val sender: User,
    val receiver: String,
    val timestamp: String
) {
    companion object {
        fun messageMapper() = MessageEntityMapper()
    }
}
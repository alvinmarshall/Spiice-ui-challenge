package com.cheise_proj.domain.entities.message

import com.cheise_proj.domain.entities.user.UserEntity

data class MessageEntity(
    val id: String,
    val content: String,
    val sender: UserEntity,
    val receiver: String,
    val timestamp: String
)
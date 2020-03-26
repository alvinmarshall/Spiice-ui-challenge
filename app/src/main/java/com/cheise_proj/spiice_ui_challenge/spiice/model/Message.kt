package com.cheise_proj.spiice_ui_challenge.spiice.model

data class Message(
    val id: String,
    val content: String,
    val user: User,
    val timeStamp: String
)

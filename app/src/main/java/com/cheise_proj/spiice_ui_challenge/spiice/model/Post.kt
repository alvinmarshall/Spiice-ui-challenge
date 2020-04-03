package com.cheise_proj.spiice_ui_challenge.spiice.model

data class Post(
    val id: String,
    val timestamp: String,
    val header: String,
    val title: String,
    val content: String,
    val user: User,
    val skills: List<String>,
    val amount: String,
    val proposition: Int,
    val elapseTime:String
)
package com.cheise_proj.presentation.model

import com.cheise_proj.presentation.mapper.post.PostEntityMapper

data class Post(
    val id: String,
    val amount: String,
    val proposition: String,
    val header: String,
    val description: String,
    val timestamp: String,
    val user: User,
    val skills: List<String>
) {
    companion object {
        fun postMapper() = PostEntityMapper()
    }
}
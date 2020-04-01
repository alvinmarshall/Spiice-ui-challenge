package com.cheise_proj.data.model

import com.cheise_proj.data.mapper.post.PostDataEntityMapper

data class PostData(
    val id: String,
    val amount: String,
    val proposition: String,
    val header: String,
    val description: String,
    val timestamp: String,
    val user: UserData,
    val skills: List<String>
) {
    companion object {
        fun postMapper() = PostDataEntityMapper()
    }
}
package com.cheise_proj.remote.model.posts

import com.cheise_proj.remote.mapper.post.PostRemoteDataMapper
import com.cheise_proj.remote.model.user.UserRemote
import com.google.gson.annotations.SerializedName


data class PostDto(
    @SerializedName("data")
    val post: List<PostRemote>,
    val status: Int
)

data class PostRemote(
    val user: UserRemote,
    val amount: String,
    val proposition: Int,
    val header: String,
    @SerializedName("createdAt")
    val timestamp: String,
    val description: String,
    val skills: List<String>,
    val id: String
) {
    companion object {
        fun postMapper() = PostRemoteDataMapper()
    }

}
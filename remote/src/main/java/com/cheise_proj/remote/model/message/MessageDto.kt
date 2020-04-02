package com.cheise_proj.remote.model.message

import com.cheise_proj.remote.mapper.message.MessageRemoteDataMapper
import com.cheise_proj.remote.model.user.UserRemote
import com.google.gson.annotations.SerializedName


data class MessageDto(
    @SerializedName("data")
    val message: List<MessageRemote>,
    val status: Int
)

data class MessageRemote(
    val sender: UserRemote,
    val content: String,
    val receiver: String,
    @SerializedName("createdAt")
    val timestamp: String,
    val id: String
) {
    companion object {
        fun messageMapper() = MessageRemoteDataMapper()
    }
}



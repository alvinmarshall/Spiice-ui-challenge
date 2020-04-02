package com.cheise_proj.remote.model.user

import com.cheise_proj.remote.mapper.user.UserRemoteDataMapper


data class UserDto(
    val message: String,
    val user: UserRemote,
    val token: String,
    var refreshToken: String,
    val status: Int
)

data class UserRemote(
    val avatarUrl: String,
    val email: String,
    val name: String,
    val userId: String,
    var accessToken: String,
    var refreshToken: String
) {
    companion object {
        fun userMapper() = UserRemoteDataMapper()
    }
}


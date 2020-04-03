package com.cheise_proj.presentation.model

import com.cheise_proj.presentation.mapper.user.UserEntityMapper

data class User(
    val email: String,
    val avatarUrl: String,
    val userId: String,
    val name: String,
    var accessToken: String,
    var refreshToken: String
) {
   companion object{
       fun userMapper() = UserEntityMapper()
   }
}
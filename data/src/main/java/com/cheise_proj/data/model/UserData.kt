package com.cheise_proj.data.model

import com.cheise_proj.data.mapper.user.UserDataEntityMapper

data class UserData(
    val email: String,
    val avatarUrl: String,
    val userId: String,
    val name: String
) {
   companion object{
       fun userMapper(): UserDataEntityMapper {
           return UserDataEntityMapper()
       }
   }
}
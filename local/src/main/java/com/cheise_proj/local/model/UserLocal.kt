package com.cheise_proj.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cheise_proj.local.mapper.user.UserLocalDataMapper

@Entity(tableName = "users")
data class UserLocal(
    val email: String,
    val avatarUrl: String,
    @PrimaryKey(autoGenerate = false)
    val userId: String,
    val name: String,
    var accessToken: String,
    var refreshToken: String
) {
    constructor() : this("", "", "", "", "", "")

    companion object {
        fun userMapper() = UserLocalDataMapper()
    }
}
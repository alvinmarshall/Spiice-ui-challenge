package com.cheise_proj.presentation.vo

data class UserSession(
    val isLogin: Boolean,
    val email: String?,
    val avatarUrl: String?,
    val name: String?
) {
    var accessToken: String = ""
    var refreshToken: String = ""
}
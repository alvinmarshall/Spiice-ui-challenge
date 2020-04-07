package com.cheise_proj.remote.model.token


import com.google.gson.annotations.SerializedName

data class TokenDto(
    var data: Data,
    var status: Int
)

data class Data(
    @SerializedName("access_token")
    var accessToken: String,
    @SerializedName("expires_in")
    var expiresIn: String,
    @SerializedName("user_id")
    var userId: String

)
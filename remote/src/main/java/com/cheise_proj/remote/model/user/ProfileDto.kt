package com.cheise_proj.remote.model.user

import com.cheise_proj.remote.mapper.user.PortfolioRemoteDataMapper
import com.cheise_proj.remote.mapper.user.ProfileRemoteDataMapper
import com.cheise_proj.remote.mapper.user.ReviewRemoteDataMapper
import com.google.gson.annotations.SerializedName


data class ProfileDto(
    @SerializedName("data")
    val profileRemote: ProfileRemote,
    val status: Int
)

data class ReviewRemote(
    val content: String,
    val rating: Float,
    @SerializedName("createdAt")
    val timestamp: String,
    val sender: UserRemote,
    val id: String
) {
    companion object {
        fun reviewMapper() = ReviewRemoteDataMapper()
    }
}

data class ProfileRemote(
    val user: UserRemote,
    val description: String,
    val portfolio: List<PortfolioRemote>,
    val jobTitle: String,
    val reviews: List<ReviewRemote>
) {
    companion object {
        fun profileMapper() = ProfileRemoteDataMapper()
    }
}

data class PortfolioRemote(
    val screenShotUrl: String
) {
    companion object {
        fun portfolioMapper() = PortfolioRemoteDataMapper()
    }
}
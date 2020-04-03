package com.cheise_proj.data.model

import com.cheise_proj.data.mapper.user.PortfolioDataEntityMapper
import com.cheise_proj.data.mapper.user.ProfileDataEntityMapper
import com.cheise_proj.data.mapper.user.ReviewDataEntityMapper

data class ProfileData(
    val jobTitle: String,
    val description: String,
    val portfolio: List<PortfolioData>,
    var reviews: List<ReviewsData>,
    val user: UserData
) {
    companion object {
        fun profileMapper() =
            ProfileDataEntityMapper()
        fun portfolioMapper() =
            PortfolioDataEntityMapper()

        fun reviewMapper() =
            ReviewDataEntityMapper()
    }
}

data class PortfolioData(
    val screenShotUrl: String
)

data class ReviewsData(
    val id:String,
    val content: String,
    val rating: Float,
    val timestamp: String,
    val sender:UserData
)


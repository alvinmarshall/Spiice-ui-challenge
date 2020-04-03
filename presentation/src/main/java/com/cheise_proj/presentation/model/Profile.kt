package com.cheise_proj.presentation.model

import com.cheise_proj.presentation.mapper.user.PortfolioEntityMapper
import com.cheise_proj.presentation.mapper.user.ProfileEntityMapper
import com.cheise_proj.presentation.mapper.user.ReviewEntityMapper

data class Profile(
    val jobTitle: String,
    val description: String,
    val portfolio: List<Portfolio>,
    var reviews: List<Reviews>,
    val user: User
) {
    companion object {
        fun profileMapper() =
            ProfileEntityMapper()
        fun portfolioMapper() =
            PortfolioEntityMapper()

        fun reviewMapper() =
            ReviewEntityMapper()
    }
}

data class Portfolio(
    val screenShotUrl: String
)

data class Reviews(
    val id:String,
    val content: String,
    val rating: Float,
    val timestamp: String,
    val sender:User
)


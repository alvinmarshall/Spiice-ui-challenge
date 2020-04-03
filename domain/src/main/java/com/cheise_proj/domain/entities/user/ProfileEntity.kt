package com.cheise_proj.domain.entities.user

data class ProfileEntity(
    val jobTitle: String,
    val description: String,
    val portfolio: List<PortfolioEntity>,
    val reviews: List<ReviewsEntity>,
    val user: UserEntity
)

data class PortfolioEntity(
    val screenShotUrl: String
)

data class ReviewsEntity(
    val id:String,
    val content: String,
    val rating: Float,
    val timestamp: String,
    val sender:UserEntity
)


package com.cheise_proj.domain.entities.user

data class ProfileEntity(
    val jobTitle: String,
    val description: String,
    val portfolio: List<Portfolio>,
    val reviews: List<Reviews>,
    val user: UserEntity
)

data class Portfolio(
    val screenShotUrl: String
)

data class Reviews(
    val id:String,
    val content: String,
    val rating: Float,
    val timestamp: String,
    val sender:UserEntity
)


package com.cheise_proj.spiice_ui_challenge.spiice.model

data class Profile(
    val id: String,
    val name: String,
    val avatarUrl:String,
    val jobTitle: String,
    val description: String,
    val reviews: List<Review>,
    val portfolio: List<Portfolio>
)

data class Review(
    val id: String,
    val name: String,
    val review: String,
    val ratingNumber: Float
)

data class Portfolio(
    val id: String,
    val screenShotUrl: String
)
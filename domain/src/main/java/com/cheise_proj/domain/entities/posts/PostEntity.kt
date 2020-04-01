package com.cheise_proj.domain.entities.posts

import com.cheise_proj.domain.entities.user.UserEntity

data class PostEntity(
    val id: String,
    val amount: String,
    val proposition: String,
    val header: String,
    val description: String,
    val timestamp: String,
    val user: UserEntity,
    val skills: List<String>
)
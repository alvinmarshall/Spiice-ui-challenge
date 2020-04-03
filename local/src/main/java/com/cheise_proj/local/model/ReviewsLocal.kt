package com.cheise_proj.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class ReviewsLocal(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val content: String,
    val rating: Float,
    val timestamp: String,
    @Embedded
    val sender: UserLocal
)
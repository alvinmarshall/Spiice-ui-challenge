package com.cheise_proj.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.cheise_proj.local.mapper.user.PortfolioLocalDataMapper
import com.cheise_proj.local.mapper.user.ProfileLocalDataMapper
import com.cheise_proj.local.mapper.user.ReviewLocalDataMapper

@Entity(tableName = "profile")
data class ProfileLocal(

    val jobTitle: String,
    val description: String,
    @Embedded
    val user: UserLocal
) {
    constructor() : this("", "", UserLocal())

    @PrimaryKey(autoGenerate = false)
    var id: String = "1"

    var portfolio: List<PortfolioLocal> = arrayListOf()

    @Ignore
    var reviews: List<ReviewsLocal> = arrayListOf()


    companion object {
        fun profileMapper() = ProfileLocalDataMapper()
        fun portfolioMapper() = PortfolioLocalDataMapper()
        fun reviewMapper() = ReviewLocalDataMapper()
    }
}


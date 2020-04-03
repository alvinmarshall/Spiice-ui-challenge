package com.cheise_proj.local.utils

import com.cheise_proj.local.model.PortfolioLocal
import com.cheise_proj.local.model.ProfileLocal
import com.cheise_proj.local.model.ReviewsLocal
import com.cheise_proj.local.model.UserLocal

object TestUserGenerator {
    fun getUser(): UserLocal {
        return UserLocal(
            email = "test email",
            avatarUrl = "http://testavatar",
            name = "test user name",
            userId = "test uid",
            accessToken = "test access token",
            refreshToken = "test refresh token"
        )
    }

    fun getProfile(): ProfileLocal {
        return ProfileLocal(
            jobTitle = "test job title",
            description = "test description",
            user = getUser()
        ).apply {
            reviews = arrayListOf(
                ReviewsLocal(
                    content = "test content",
                    timestamp = "2020-03-31T15:45:28.627Z",
                    rating = 4f,
                    sender = getUser(),
                    id = "test uid"
                )
            )
            portfolio = arrayListOf(
                PortfolioLocal(
                    screenShotUrl = "http://testscreenshot"
                )
            )
        }
    }
}
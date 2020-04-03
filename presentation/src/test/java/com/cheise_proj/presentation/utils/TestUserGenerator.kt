package com.cheise_proj.presentation.utils


import com.cheise_proj.presentation.model.Portfolio
import com.cheise_proj.presentation.model.Profile
import com.cheise_proj.presentation.model.Reviews
import com.cheise_proj.presentation.model.User

object TestUserGenerator {
    fun getUser(): User {
        return User(
            email = "test email",
            avatarUrl = "http://testavatar",
            name = "test user name",
            userId = "test uid",
            accessToken = "test access token",
            refreshToken = "test refresh token"
        )
    }

    fun getProfile(): Profile {
        return Profile(
            jobTitle = "test job title",
            description = "test description",
            user = getUser(),
            reviews = arrayListOf(
                Reviews(
                    content = "test content",
                    timestamp = "2020-03-31T15:45:28.627Z",
                    rating = 4f,
                    sender = getUser(),
                    id = "test uid"
                )
            ),
            portfolio = arrayListOf(
                Portfolio(
                    screenShotUrl = "http://testscreenshot"
                )
            )
        )
    }
}
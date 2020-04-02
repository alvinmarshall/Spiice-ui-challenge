package utils

import com.cheise_proj.remote.model.user.*

object TestUserGenerator {
    fun getProfileDto(): ProfileDto {
        return ProfileDto(
            profileRemote = getProfile(),
            status = 200
        )
    }

    fun getUserDto(): UserDto {
        return UserDto(
            message = "login success",
            status = 200,
            refreshToken = "test refresh",
            user = getUser(),
            token = "test token"
        )
    }

    private fun getUser(): UserRemote {
        return UserRemote(
            email = "test email",
            avatarUrl = "http://testavatar",
            name = "test user name",
            userId = "test uid",
            accessToken = "test access token",
            refreshToken = "test refresh token"
        )
    }

    private fun getProfile(): ProfileRemote {
        return ProfileRemote(
            jobTitle = "test job title",
            description = "test description",
            user = getUser(),
            reviews = arrayListOf(
                ReviewRemote(
                    content = "test content",
                    timestamp = "2020-03-31T15:45:28.627Z",
                    rating = 4f,
                    sender = getUser(),
                    id = "test uid"
                )
            ),
            portfolio = arrayListOf(
                PortfolioRemote(
                    screenShotUrl = "http://testscreenshot"
                )
            )
        )
    }
}
package utils

import com.cheise_proj.domain.entities.Portfolio
import com.cheise_proj.domain.entities.ProfileEntity
import com.cheise_proj.domain.entities.Reviews
import com.cheise_proj.domain.entities.UserEntity

object TestUserGenerator {
    fun getUser(): UserEntity {
        return UserEntity(
            email = "test email",
            avatarUrl = "http://testavatar",
            name = "test user name",
            userId = "test uid"
        )
    }

    fun getProfile(): ProfileEntity {
        return ProfileEntity(
            jobTitle = "test job title",
            description = "test description",
            user = getUser(),
            reviews = arrayListOf(
                Reviews(
                    content = "test content",
                    timestamp = "2020-03-31T15:45:28.627Z",
                    rating = 4f
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
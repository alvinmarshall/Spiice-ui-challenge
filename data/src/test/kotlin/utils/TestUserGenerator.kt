package utils

import com.cheise_proj.data.model.PortfolioData
import com.cheise_proj.data.model.ProfileData
import com.cheise_proj.data.model.ReviewsData
import com.cheise_proj.data.model.UserData
import com.cheise_proj.domain.entities.user.Portfolio
import com.cheise_proj.domain.entities.user.ProfileEntity
import com.cheise_proj.domain.entities.user.Reviews
import com.cheise_proj.domain.entities.user.UserEntity

object TestUserGenerator {
    fun getUser(): UserData {
        return UserData(
            email = "test email",
            avatarUrl = "http://testavatar",
            name = "test user name",
            userId = "test uid"
        )
    }

    fun getProfile(): ProfileData {
        return ProfileData(
            jobTitle = "test job title",
            description = "test description",
            user = getUser(),
            reviews = arrayListOf(
                ReviewsData(
                    content = "test content",
                    timestamp = "2020-03-31T15:45:28.627Z",
                    rating = 4f
                )
            ),
            portfolio = arrayListOf(
                PortfolioData(
                    screenShotUrl = "http://testscreenshot"
                )
            )
        )
    }
}
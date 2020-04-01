package utils

import com.cheise_proj.domain.entities.UserEntity

object TestUserGenerator {
    fun getUser():UserEntity{
        return UserEntity(
            email = "test email",
            avatarUrl = "http://testavatar",
            name = "test user name",
            userId = "test uid"
        )
    }
}
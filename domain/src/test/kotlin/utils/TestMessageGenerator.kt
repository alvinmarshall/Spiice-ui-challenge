package utils

import com.cheise_proj.domain.entities.message.MessageEntity

object TestMessageGenerator {
    fun getUserMessages(): List<MessageEntity> {
        return arrayListOf(
            MessageEntity(
                id = "test message uid",
                timestamp = "2020-03-31T15:45:28.627Z",
                content = "test content",
                sender = TestUserGenerator.getUser(),
                receiver = "test receiver email"
            )
        )
    }

}